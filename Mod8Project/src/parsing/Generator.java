package parsing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import parsing.BaseGrammarParser.*;
import parsing.BaseGrammarParser.ArrayLiteralExprContext;
import parsing.BaseGrammarParser.SpidValContext;
import parsing.Checker.CheckResult;
import parsing.Type.Array;
import parsing.Type.Pointer;
import translation.Int;
import translation.MemAddr;
import translation.OpCode;
import translation.Operand;
import translation.Operator;
import translation.Program;
import translation.Register;
import translation.Spril;
import translation.Target;
import write.ProgramRunner;

public class Generator extends BaseGrammarBaseVisitor<List<Spril>> {

	public static final Func	MAIN_FUNC_SIG	= new Func("main",
														Primitive.INT);

	public static void main(String[] args) {
		String prog = "program sharedTest(50);shared int val = 0; shared int addc = 0; shared int subc = 0;def int main() {int tmp = 1;LOCK(val_lock){for (int i = 0; i < 3; i = i + 1) {add();}}tmp = 3;LOCK(val_lock){for (int j = 0; j < 3; j = j + 1) {sub();}}return val;}def void add() {val = val + 1; addc = addc+1;return;}def void sub() {val = val - 1;subc = subc + 1;return;}";
		ProgramContext ctx = new BaseGrammarParser(new CommonTokenStream(
				new BaseGrammarLexer(new ANTLRInputStream(prog)))).program();
		CheckResult cres = new Checker().check(ctx);
		if (!cres.getErrors().isEmpty()) {
			System.out.println(cres.getErrors());
			System.err.println("<parse error(s)>");
			return;
		}
		Generator gen = new Generator();
		Program list = gen.compile(ctx, cres);
		System.out.println(list.getInstructions().stream()
				.map(s -> s.toString()).collect(Collectors.joining("\n")));
		ProgramRunner.runAndRemove(list);
	}

	private CheckResult						cres;
	private Program							prog;
	private Map<FuncContext, List<Spril>>	functions;
	private Map<FuncContext, Integer>		functionAddrs;
	private int								heapPtr, sharedPtr;

	private Map<FuncContext, List<Spril>>	calls;

	public Program compile(ProgramContext ctx, CheckResult cres) {
		this.cres = cres;
		this.prog = new Program(ctx.progdef().ID().getText(), 1);
		this.functions = new HashMap<>();
		this.functionAddrs = new HashMap<>();
		this.calls = new HashMap<>();
		this.heapPtr = cres
				.getOffsets()
				.entrySet()
				.stream()
				.filter(e -> cres.getShared().get(e.getKey()) == null
						|| !cres.getShared().get(e.getKey()))
				.mapToInt(e -> e.getValue()).max().getAsInt();
		this.sharedPtr = cres
				.getOffsets()
				.entrySet()
				.stream()
				.filter(e -> cres.getShared().get(e.getKey()) != null
						&& cres.getShared().get(e.getKey()))
				.mapToInt(e -> e.getValue()).max().orElse(0);
		if (ctx.progdef().NUMBER() != null)
			prog.setCoreCount(Integer
					.parseInt(ctx.progdef().NUMBER().getText()));

		List<Spril> instrs = new ArrayList<>();

		instrs.add(new Spril(OpCode.CONST, "Initial Return Addr", new Int(-1),
				Register.A));
		instrs.add(new Spril(OpCode.PUSH, Register.A));
		instrs.add(new Spril(OpCode.CONST, "Initial Result Addr", new Int(-1),
				Register.A));
		instrs.add(new Spril(OpCode.PUSH, Register.A));

		ctx.decl().stream().map(decl -> visit(decl)).forEach(decl -> {
			decl.forEach(ins -> instrs.add(ins));
		});
		ctx.func().forEach(func -> functions.put(func, visit(func)));

		MemAddr initLockAddr = MemAddr.direct(cres.getLocks().get(
				"<$INIT_LOCK$>"));

		instrs.add(new Spril(OpCode.BRANCH, Register.SPID, Target.relative(4)));
		instrs.add(new Spril(OpCode.TEST_AND_SET, initLockAddr));
		instrs.add(new Spril(OpCode.RECEIVE, Register.ZERO)); // don't need this
		instrs.add(new Spril(OpCode.JUMP, Target.relative(5)));
		instrs.add(new Spril(OpCode.READ, initLockAddr));
		instrs.add(new Spril(OpCode.RECEIVE, Register.A));
		instrs.add(new Spril(OpCode.BRANCH, Register.A, Target.relative(2)));
		instrs.add(new Spril(OpCode.JUMP, Target.relative(-3)));

		FuncContext mainFuncCtx = cres.getMatchingFunc(MAIN_FUNC_SIG.getName(),
				MAIN_FUNC_SIG.getArgs());
		if (Primitive.forName(mainFuncCtx.type().getText()) != MAIN_FUNC_SIG
				.getReturnType())
			System.err.println("WARNING: Main function return type should be "
					+ MAIN_FUNC_SIG.getReturnType());

		/*
		 * cres.getLocks() .entrySet() .forEach( lock -> instrs.add(new
		 * Spril(OpCode.WRITE, "Initialisation of lock " + lock.getKey(),
		 * Register.ZERO, MemAddr.direct(lock.getValue()))));
		 */

		Spril jumpToMain = new Spril(OpCode.JUMP, "Jump To Main Function",
				Target.absolute(-1));
		instrs.add(jumpToMain);

		// addr = 5 + ctx.decl().size() * 2;// + cres.getLocks().size();
		int addr = instrs.size();
		for (FuncContext func : ctx.func()) {
			functionAddrs.put(func, addr);
			if (calls.get(func) != null)
				for (Spril call : calls.get(func)) {
					if (call.getOpCode() != OpCode.JUMP)
						throw new RuntimeException(
								"Call should be a JUMP but is "
										+ call.getOpCode());
					call.getOperands()[0] = Target.absolute(addr);
				}
			instrs.addAll(functions.get(func));
			addr += functions.get(func).size();
		}

		calls.forEach((func, calls) -> {
			calls.forEach(call -> {
				int callSite = instrs.indexOf(call);
				instrs.get(callSite - 4).getOperands()[0] = new Int(
						callSite + 1);
			});
		});

		for (int i = 0; i < 10; i++)
			instrs.add(new Spril(OpCode.NOP));

		instrs.add(new Spril(OpCode.END_PROG));

		instrs.get(0).getOperands()[0] = new Int(instrs.size() - 11);
		jumpToMain.getOperands()[0] = Target.absolute(functionAddrs
				.get(mainFuncCtx));

		instrs.forEach(ins -> prog.addInstruction(ins));
		return prog;
	}

	/*
	 * TODO: public List<Spril> visitArrayLiteralExpr(ArrayLiteralExprContext
	 * ctx) { List<Spril> result = new ArrayList<>();
	 * 
	 * Type valType = getType(ctx.expr(0)); Primitive baseType =
	 * Type.baseType(valType);
	 * 
	 * int base = cres.getOffsets().get()
	 * 
	 * switch (baseType) { case INT: ctx.val().forEach(v -> { int val =
	 * Integer.parseInt(v.getText());
	 * 
	 * }); break; case BOOL: break; default: throw new Error(); }
	 * 
	 * return result; }
	 */
	public List<Spril> visitAssign(AssignContext ctx) {
		List<Spril> result = assign(ctx.derefID() == null ? ctx.arrayVal()
				: ctx.derefID(), ctx.expr());
		result.get(0).addComment(
				(ctx.derefID() == null ? ctx.arrayVal().getText() : ctx
						.derefID().getText()) + " = " + ctx.expr().getText());
		return result;
	}

	public List<Spril> visitAssignStat(AssignStatContext ctx) {
		return visit(ctx.assign());
	}

	public List<Spril> visitExprArrayExpr(ExprArrayExprContext ctx) {
		List<Spril> result = new ArrayList<>();
		int baseOffset = cres.getOffsets().get(ctx);
		result.add(new Spril(OpCode.CONST, new Int(baseOffset), Register.E));

		result.addAll(visit(ctx.arrayVal().expr()));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));

		boolean shared = cres.getShared().get(ctx) != null
				&& cres.getShared().get(ctx);

		if (shared) {
			result.add(new Spril(OpCode.READ, MemAddr.deref(Register.A)));
			result.add(new Spril(OpCode.RECEIVE, Register.D));
		} else {
			result.add(new Spril(OpCode.LOAD, MemAddr.deref(Register.A),
					Register.D));
		}
		result.add(new Spril(OpCode.COMPUTE, Operator.ADD, Register.E,
				Register.D, Register.D));
		if (shared) {
			result.add(new Spril(OpCode.READ, MemAddr.deref(Register.D)));
			result.add(new Spril(OpCode.RECEIVE, Register.A));
		} else {
			result.add(new Spril(OpCode.LOAD, MemAddr.deref(Register.D),
					Register.A));
		}
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitBlock(BlockContext ctx) {
		List<Spril> result = new ArrayList<>();
		for (StatContext stat : ctx.stat())
			result.addAll(visit(stat));
		return result;
	}

	public List<Spril> visitBlockStat(BlockStatContext ctx) {
		return visit(ctx.block());
	}

	public List<Spril> visitBoolOpExpr(BoolOpExprContext ctx) {
		return binaryOperation(ctx.expr(0), ctx.expr(1),
				Operator.boolOp(ctx.boolOp().getText()));
	}

	public List<Spril> visitCallExpr(CallExprContext ctx) {
		return call(ctx.call(), true);
	}

	public List<Spril> visitCallStat(CallStatContext ctx) {
		return call(ctx.call(), false);
	}

	public List<Spril> visitCompExpr(CompExprContext ctx) {
		return binaryOperation(ctx.expr(0), ctx.expr(1),
				Operator.comparator(ctx.comp().getText()));
	}

	public List<Spril> visitConstArrayExpr(ConstArrayExprContext ctx) {
		List<Spril> result = new ArrayList<>();
		int base = cres.getOffsets().get(ctx);
		int idx = Integer.parseInt(ctx.NUMBER().getText());
		Type type = cres.getTypes().get(ctx);
		int typeSize = type.getSize();
		boolean shared = cres.getShared().get(ctx);
		int addr = idx * typeSize;
		if (shared) {
			result.add(new Spril(OpCode.READ, MemAddr.direct(base)));
			result.add(new Spril(OpCode.RECEIVE, Register.D));
		} else {
			result.add(new Spril(OpCode.LOAD, ctx.getText(), MemAddr
					.direct(base), Register.D));
		}
		result.add(new Spril(OpCode.CONST, new Int(addr), Register.A));
		result.add(new Spril(OpCode.COMPUTE, Operator.ADD, Register.A,
				Register.D, Register.D));
		if (shared) {
			result.add(new Spril(OpCode.READ, MemAddr.deref(Register.D)));
			result.add(new Spril(OpCode.RECEIVE, Register.A));
		} else {
			result.add(new Spril(OpCode.LOAD, ctx.getText(), MemAddr
					.deref(Register.D), Register.A));
		}
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitDecl(DeclContext ctx) {
		if (getType(ctx) instanceof Array
				&& ctx.expr() instanceof ArrayLiteralExprContext) {
			List<Spril> result = new ArrayList<>();
			ArrayLiteralExprContext arrCtx = (ArrayLiteralExprContext) ctx
					.expr();
			Type valType = getType(arrCtx.expr(0));
			boolean shared = ctx.SHARED() != null;
			int ptrOffset = cres.getOffsets().get(ctx.ID());
			int arrBaseAddr = (shared ? sharedPtr : heapPtr) + 1;
			int valSize = valType.getSize();
			if (shared)
				sharedPtr += valSize * arrCtx.expr().size();
			else
				heapPtr += valSize * arrCtx.expr().size();

			result.add(new Spril(OpCode.CONST, "Base addr for array "
					+ ctx.ID().getText(), new Int(arrBaseAddr), Register.A));
			if (shared)
				result.add(new Spril(OpCode.WRITE, Register.A, MemAddr
						.direct(ptrOffset)));
			else
				result.add(new Spril(OpCode.STORE, Register.A, MemAddr
						.direct(ptrOffset)));

			for (int i = 0; i < arrCtx.expr().size(); i++) {
				result.addAll(visit(arrCtx.expr(i)));
				if (result.get(result.size() - 1).equals(
						new Spril(OpCode.PUSH, Register.A)))
					result.remove(result.size() - 1);
				else
					result.add(new Spril(OpCode.POP, Register.A));

				if (shared) {
					result.add(new Spril(OpCode.WRITE, Register.A, MemAddr
							.direct(arrBaseAddr + (i * valSize))));
				} else {
					result.add(new Spril(OpCode.STORE, Register.A, MemAddr
							.direct(arrBaseAddr + (i * valSize))));
				}

			}
			/*
			 * for (int i = 0; i < arrCtx.expr().size(); i++) { if (valType ==
			 * Primitive.INT || valType instanceof Pointer || valType instanceof
			 * Array) { int val; if (valType == Primitive.INT &&
			 * cres.getOffsets().get(arrCtx.val(i).ID()) == null) { val =
			 * Integer.parseInt(arrCtx.val(i).getText()); result.add(new
			 * Spril(OpCode.CONST, new Int(val), Register.A)); } else { int
			 * offset = cres.getOffsets().get(arrCtx.val(i).ID()); if (valType
			 * instanceof Pointer || valType instanceof Array) { result.add(new
			 * Spril(OpCode.LOAD, MemAddr .direct(offset), Register.D));
			 * result.add(new Spril(OpCode.LOAD, MemAddr .deref(Register.D),
			 * Register.A)); } else { result.add(new Spril(OpCode.LOAD, MemAddr
			 * .direct(offset), Register.A)); } } result.add(new
			 * Spril(OpCode.STORE, ctx.ID().getText() + "[" + i + "]",
			 * Register.A, MemAddr.direct(arrBaseAddr + (i * valSize)))); } else
			 * if (valType == Primitive.BOOL) { if
			 * (cres.getOffsets().get(arrCtx.val(i).ID()) == null) { String val
			 * = arrCtx.val(i).getText(); if (val.equalsIgnoreCase("true")) {
			 * result.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
			 * result.add(new Spril(OpCode.STORE, ctx.ID() .getText() + "[" + i
			 * + "]", Register.A, MemAddr.direct(arrBaseAddr + (i * valSize))));
			 * } else { result.add(new Spril(OpCode.STORE, ctx.ID() .getText() +
			 * "[" + i + "]", Register.ZERO, MemAddr.direct(arrBaseAddr + (i *
			 * valSize)))); } } else { int offset =
			 * cres.getOffsets().get(arrCtx.val(i).ID()); result.add(new
			 * Spril(OpCode.LOAD, MemAddr .direct(offset), Register.A));
			 * result.add(new Spril(OpCode.STORE, ctx.ID().getText() + "[" + i +
			 * "]", Register.A, MemAddr .direct(arrBaseAddr + (i * valSize))));
			 * } } }
			 */
			return result;
		} else if (getType(ctx) instanceof Array && ctx.type().size() > 1) {
			List<Spril> result = new ArrayList<>();
			Type type = Type.forName(ctx.type(1).getText());
			int unitSize = type.getSize();
			int count = Integer.parseInt(ctx.NUMBER().getText());
			int totalSize = unitSize * count;
			boolean shared = ctx.SHARED() != null;
			int baseAddr = !shared ? heapPtr + 1 : sharedPtr + 1;
			int ptrOffset = cres.getOffsets().get(ctx.ID());

			result.add(new Spril(OpCode.CONST, "Base addr for array "
					+ ctx.ID().getText(), new Int(baseAddr), Register.A));
			if (shared)
				result.add(new Spril(OpCode.WRITE, Register.A, MemAddr
						.direct(ptrOffset)));
			else
				result.add(new Spril(OpCode.STORE, Register.A, MemAddr
						.direct(ptrOffset)));

			if (shared) {
				heapPtr += totalSize;
			} else {
				sharedPtr += totalSize;
			}

			OpCode op = shared ? OpCode.WRITE : OpCode.STORE;

			for (int i = 0; i < count; i++) {
				result.add(new Spril(op, Register.ZERO, MemAddr.direct(baseAddr
						+ (i * unitSize))));
			}

			return result;
		} else {
			List<Spril> result = new ArrayList<>();
			List<Spril> assign = assign(ctx.ID(), ctx.expr());
			result.add(new Spril(OpCode.BRANCH, Register.SPID, Target
					.relative(assign.size() + 1)));
			result.addAll(assign);
			result.get(0).addComment(
					"Declaration of " + ctx.ID().getText() + "(="
							+ ctx.expr().getText() + ")");
			return result;
		}
	}

	public List<Spril> visitDeclStat(DeclStatContext ctx) {
		return visit(ctx.decl());
	}

	public List<Spril> visitDerefExpr(DerefExprContext ctx) {
		List<Spril> result = new ArrayList<>(visit(ctx.expr()));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		if (ctx.expr() instanceof IdExprContext
				&& cres.getShared().get(((IdExprContext) ctx.expr()).ID())) {
			result.add(new Spril(OpCode.READ, MemAddr.deref(Register.A)));
			result.add(new Spril(OpCode.RECEIVE, Register.A));
		} else {
			result.add(new Spril(OpCode.LOAD, "Dereference pointer "
					+ ctx.expr().getText(), MemAddr.deref(Register.A),
					Register.A));
		}
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitDivExpr(DivExprContext ctx) {
		return binaryOperation(ctx.expr(0), ctx.expr(1), Operator.DIV);
	}

	public List<Spril> visitFalseExpr(FalseExprContext ctx) {
		// push Zero
		List<Spril> result = new ArrayList<>();
		result.add(new Spril(OpCode.PUSH, Register.ZERO));
		return result;
	}

	public List<Spril> visitForStat(ForStatContext ctx) {
		List<Spril> result = new ArrayList<>(visit(ctx.decl()));

		List<Spril> cond = visit(ctx.expr());
		List<Spril> assign = visit(ctx.assign());
		List<Spril> body = visit(ctx.block());
		result.get(0).addComment("For loop declaration");
		cond.get(0).addComment("For loop condition");
		assign.get(0).addComment("For loop assignment");
		body.get(0).addComment("For loop body");

		result.addAll(cond);
		invertComputation(result.get(result.size() - 2));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		// result.add(new Spril(OpCode.CONST, new Int(-1), Register.B));
		// result.add(new Spril(OpCode.COMPUTE, Operator.XOR, Register.A,
		// Register.B, Register.A));
		result.add(new Spril(OpCode.BRANCH, "Break from for loop", Register.A,
				Target.relative(body.size() + assign.size() + 2)));
		result.addAll(body);
		result.addAll(assign);
		result.add(new Spril(OpCode.JUMP, "Back to for loop", Target
				.relative(-(body.size() + cond.size() + assign.size()))));

		return result;
	}

	public List<Spril> visitFunc(FuncContext ctx) {
		List<Spril> result = new ArrayList<>();
		result.addAll(visit(ctx.topLevelBlock().block()));
		result.get(0).addComment("Function " + ctx.ID().getText());
		return result;

	}

	public List<Spril> visitIdExpr(IdExprContext ctx) {
		// get offset from cres, load and push value
		List<Spril> result = new ArrayList<>();
		int offset = cres.getOffsets().get(ctx.ID());
		boolean shared = cres.getShared().get(ctx.ID());
		if (shared) {
			result.add(new Spril(OpCode.READ, MemAddr.direct(offset)));
			result.add(new Spril(OpCode.RECEIVE, Register.A));
		} else
			result.add(new Spril(OpCode.LOAD, MemAddr.direct(offset),
					Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitIfStat(IfStatContext ctx) {
		List<Spril> result = new ArrayList<>();

		Map<ExprContext, Integer> exprSizes = new HashMap<>();
		List<List<Spril>> exprs = new ArrayList<>();
		ctx.expr().forEach(e -> {
			List<Spril> expr = visit(e);
			exprSizes.put(e, expr.size());
			exprs.add(expr);
		});

		Map<BlockContext, Integer> blockSizes = new HashMap<>();
		List<List<Spril>> blocks = new ArrayList<>();
		ctx.block().forEach(b -> {
			List<Spril> block = visit(b);
			blockSizes.put(b, block.size());
			blocks.add(block);
		});

		// totalExprSize = total size of expressions + 2 per expression (pop and
		// branch) + 1 (unconditional jump)
		int totalExprSize = exprSizes.values().stream().mapToInt(i -> i).sum()
				+ 1 * exprSizes.size() - 1;

		int totalBlockSize = blockSizes.values().stream().mapToInt(i -> i)
				.sum()
				+ blockSizes.size() - 1;

		for (int i = 0; i < ctx.expr().size(); i++) {
			totalExprSize -= exprSizes.get(ctx.expr(i));
			result.addAll(exprs.get(i));
			if (result.get(result.size() - 1).equals(
					new Spril(OpCode.PUSH, Register.A)))
				result.remove(result.size() - 1);
			else
				result.add(new Spril(OpCode.POP, Register.A));
			int jumpDistance = totalExprSize;
			for (int j = 0; j < i; j++) {
				jumpDistance += blockSizes.get(ctx.block().get(i)) + 1;
			}
			result.add(new Spril(OpCode.BRANCH, Register.A, Target
					.relative(jumpDistance == 0 ? 2 : jumpDistance)));
		}

		if (ctx.IF().size() == ctx.ELSE().size()) {
			result.add(new Spril(OpCode.JUMP, Target.relative(totalBlockSize
					+ 1 - blockSizes.get(ctx.block().get(blocks.size() - 1)))));
		} else {
			result.add(new Spril(OpCode.JUMP, Target
					.relative(totalBlockSize + 1)));
		}

		for (int i = 0; i < ctx.block().size(); i++) {
			totalBlockSize -= 1 + blockSizes.get(ctx.block().get(i));
			result.addAll(blocks.get(i));
			if (i < ctx.block().size() - 1)
				result.add(new Spril(OpCode.JUMP, Target
						.relative(totalBlockSize + 1)));
		}

		return result;
	}

	public List<Spril> visitLockStat(LockStatContext ctx) {
		List<Spril> result = new ArrayList<>();
		String lockId = ctx.ID().getText();
		int lockAddr = cres.getLocks().get(lockId);
		result.add(new Spril(OpCode.TEST_AND_SET, "Locking " + lockId, MemAddr
				.direct(lockAddr)));
		result.add(new Spril(OpCode.RECEIVE, Register.E));
		result.add(new Spril(OpCode.BRANCH, Register.E, Target.relative(2)));
		result.add(new Spril(OpCode.JUMP, Target.relative(-3)));
		result.addAll(visit(ctx.block()));
		result.add(new Spril(OpCode.WRITE, "Unlocking " + lockId,
				Register.ZERO, MemAddr.direct(lockAddr)));
		return result;
	}

	public List<Spril> visitMinExpr(MinExprContext ctx) {
		return binaryOperation(ctx.expr(0), ctx.expr(1), Operator.SUB);
	}

	public List<Spril> visitModExpr(ModExprContext ctx) {
		return binaryOperation(ctx.expr(0), ctx.expr(1), Operator.MOD);
	}

	public List<Spril> visitMultExpr(MultExprContext ctx) {
		return binaryOperation(ctx.expr(0), ctx.expr(1), Operator.MUL);
	}

	public List<Spril> visitNegBoolExpr(NegBoolExprContext ctx) {
		// Pop subexpression, xor with -1, push result
		List<Spril> result = new ArrayList<>(visit(ctx.expr()));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		result.add(new Spril(OpCode.CONST, new Int(-1), Register.B));
		result.add(new Spril(OpCode.COMPUTE, Operator.XOR, Register.B,
				Register.A, Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitNegNumExpr(NegNumExprContext ctx) {
		// pop subexpression, push 0 - subexpr
		List<Spril> result = new ArrayList<>(visit(ctx.expr()));
		result.add(new Spril(OpCode.POP, Register.A));
		result.add(new Spril(OpCode.COMPUTE, Operator.SUB, Register.ZERO,
				Register.A, Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitNumExpr(NumExprContext ctx) {
		// load and push constant
		List<Spril> result = new ArrayList<>();
		int num = Integer.parseInt(ctx.NUMBER().getText());
		result.add(new Spril(OpCode.CONST, new Int(num), Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitOutStat(OutStatContext ctx) {
		List<Spril> result = new ArrayList<>(visit(ctx.expr()));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		result.add(new Spril(OpCode.OUT, Register.A));
		return result;
	}

	public List<Spril> visitParExpr(ParExprContext ctx) {
		return visit(ctx.expr());
	}

	public List<Spril> visitPlusExpr(PlusExprContext ctx) {
		return binaryOperation(ctx.expr(0), ctx.expr(1), Operator.ADD);
	}

	public List<Spril> visitRefExpr(RefExprContext ctx) {
		List<Spril> result = new ArrayList<>(visit(ctx.expr()));
		int addr;
		if (ctx.expr() instanceof IdExprContext) {
			if (result.get(result.size() - 1).equals(
					new Spril(OpCode.PUSH, Register.A)))
				result.remove(result.size() - 1);
			else
				result.add(new Spril(OpCode.POP, Register.A));
			addr = cres.getOffsets().get(((IdExprContext) ctx.expr()).ID());
		} else {
			Type type = cres.getTypes().get(ctx.expr());
			addr = heapPtr;
			heapPtr += type.getSize();
			if (result.get(result.size() - 1).equals(
					new Spril(OpCode.PUSH, Register.A)))
				result.remove(result.size() - 1);
			else
				result.add(new Spril(OpCode.POP, Register.A));
			result.add(new Spril(OpCode.STORE, "Store pointer's target value",
					Register.A, MemAddr.direct(addr)));
		}
		result.add(new Spril(OpCode.CONST, "Create pointer to "
				+ ctx.expr().getText(), new Int(addr), Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitReturnStat(ReturnStatContext ctx) {
		List<Spril> result = new ArrayList<>();
		// TODO
		if (ctx.expr() != null) {
			result.addAll(visit(ctx.expr()));
			if (result.get(result.size() - 1).equals(
					new Spril(OpCode.PUSH, Register.A)))
				result.remove(result.size() - 1);
			else
				result.add(new Spril(OpCode.POP, Register.A));
		}
		result.add(new Spril(OpCode.POP, "Get Result addr", Register.B));
		result.add(new Spril(OpCode.POP, "Get Return addr", Register.C));

		result.add(new Spril(OpCode.COMPUTE, "Is Result addr valid?",
				Operator.LT, Register.B, Register.ZERO, Register.D));
		result.add(new Spril(OpCode.BRANCH, Register.D, Target.relative(2)));

		if (ctx.expr() != null)
			result.add(new Spril(OpCode.STORE, "Store Result", Register.A,
					MemAddr.deref(Register.B)));

		result.add(new Spril(OpCode.JUMP, "return", Target.indirect(Register.C)));
		return result;
	}

	public List<Spril> visitTrueExpr(TrueExprContext ctx) {
		// load and push -1
		List<Spril> result = new ArrayList<>();
		result.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitNumVal(NumValContext ctx) {
		List<Spril> result = new ArrayList<>();
		result.add(new Spril(OpCode.CONST, new Int(Integer.parseInt(ctx
				.NUMBER().getText())), Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitTrueVal(TrueValContext ctx) {
		List<Spril> result = new ArrayList<>();
		result.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitFalseVal(FalseValContext ctx) {
		List<Spril> result = new ArrayList<>();
		result.add(new Spril(OpCode.PUSH, Register.ZERO));
		return result;
	}

	public List<Spril> visitIdVal(IdValContext ctx) {
		List<Spril> result = new ArrayList<>();
		DerefIDContext deref = ctx.derefID();
		Type currentType = cres.getTypes().get(deref);
		result.add(new Spril(OpCode.CONST,
				new Int(cres.getOffsets().get(deref)), Register.D));
		while (currentType instanceof Pointer) {
			Pointer ptr = (Pointer) currentType;
			currentType = ptr.getWrappedType();
			if (currentType instanceof Pointer)
				deref = deref.derefID();
			else {
			}
			if (cres.getShared().get(ctx)) {
				result.add(new Spril(OpCode.READ, MemAddr.deref(Register.D)));
				result.add(new Spril(OpCode.RECEIVE, Register.D));
			} else {
				result.add(new Spril(OpCode.LOAD, MemAddr.deref(Register.D),
						Register.D));
			}
		}

		result.add(new Spril(OpCode.PUSH, Register.D));

		return result;
	}

	public List<Spril> visitSpidVal(SpidValContext ctx) {
		List<Spril> result = new ArrayList<>();
		result.add(new Spril(OpCode.PUSH, Register.SPID));
		return result;
	}

	public List<Spril> visitArrVal(ArrValContext ctx) {
		List<Spril> result = new ArrayList<>();
		int baseOffset = cres.getOffsets().get(ctx);
		result.add(new Spril(OpCode.CONST, new Int(baseOffset), Register.E));

		result.addAll(visit(ctx.arrayVal().expr()));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));

		if (cres.getShared().get(ctx)) {
			result.add(new Spril(OpCode.READ, MemAddr.deref(Register.D)));
			result.add(new Spril(OpCode.RECEIVE, Register.D));
		} else {
			result.add(new Spril(OpCode.LOAD, MemAddr.deref(Register.D),
					Register.D));
		}
		result.add(new Spril(OpCode.COMPUTE, Operator.ADD, Register.E,
				Register.D, Register.D));
		if (cres.getShared().get(ctx)) {
			result.add(new Spril(OpCode.READ, MemAddr.deref(Register.D)));
			result.add(new Spril(OpCode.RECEIVE, Register.A));
		} else {
			result.add(new Spril(OpCode.LOAD, MemAddr.deref(Register.D),
					Register.A));
		}
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public List<Spril> visitWhileStat(WhileStatContext ctx) {
		List<Spril> result = new ArrayList<>();

		List<Spril> cond = visit(ctx.expr());
		List<Spril> body = visit(ctx.block());
		// body.add(new Spril(OpCode.NOP));

		result.addAll(cond);
		invertComputation(result.get(result.size() - 2));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		result.add(new Spril(OpCode.BRANCH, Register.A, Target.relative(body
				.size() + 2)));
		result.addAll(body);
		result.add(new Spril(OpCode.JUMP, Target.relative(-(body.size() + cond
				.size()))));
		return result;
	}

	private List<Spril> assign(ParseTree tree, ExprContext expr) {
		List<Spril> result = new ArrayList<>();
		int offset;
		TerminalNode id;

		if (tree instanceof DerefIDContext) {
			DerefIDContext ctx = (DerefIDContext) tree;
			id = getID(ctx);
			boolean shared = cres.getShared().get(id);

			offset = cres.getOffsets().get(id);
			result.add(new Spril(OpCode.CONST, new Int(offset), Register.D));
			DerefIDContext current = ctx;
			while (current.derefID() != null) {
				current = current.derefID();
				if (shared) {
					result.add(new Spril(OpCode.READ, MemAddr.deref(Register.D)));
					result.add(new Spril(OpCode.RECEIVE, Register.D));
				} else {
					result.add(new Spril(OpCode.LOAD,
							MemAddr.deref(Register.D), Register.D));
				}
			}
		} else if (tree instanceof ArrayValContext) {
			ArrayValContext ctx = (ArrayValContext) tree;
			id = ctx.ID();
			// TODO: shared arrays
			boolean shared = cres.getShared().get(ctx) != null
					&& cres.getShared().get(ctx);
			offset = cres.getOffsets().get(id);
			result.add(new Spril(OpCode.CONST, new Int(offset), Register.D));
			result.add(new Spril(shared ? OpCode.READ : OpCode.LOAD,
					"Get base addr for array " + id.getText(), MemAddr
							.deref(Register.D), Register.D));
			if (shared)
				result.add(new Spril(OpCode.RECEIVE, Register.D));
			result.addAll(visit(ctx.expr()));
			if (result.get(result.size() - 1).equals(
					new Spril(OpCode.PUSH, Register.A)))
				result.remove(result.size() - 1);
			else
				result.add(new Spril(OpCode.POP, Register.A));
			Type valType = cres.getTypes().get(id);
			if (valType.getSize() != 1) {
				result.add(new Spril(OpCode.CONST, "Size of array values",
						new Int(valType.getSize()), Register.D));
				result.add(new Spril(OpCode.COMPUTE, "Corrected index",
						Operator.MUL, Register.A, Register.B, Register.A));
			}
			result.add(new Spril(OpCode.COMPUTE, "Compute target addr",
					Operator.ADD, Register.A, Register.D, Register.D));
		} else {
			id = (TerminalNode) tree;
			offset = cres.getOffsets().get(id);
			result.add(new Spril(OpCode.CONST, new Int(offset), Register.D));
		}
		result.add(new Spril(OpCode.PUSH, Register.D));
		result.addAll(visit(expr));

		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		result.add(new Spril(OpCode.POP, Register.D));
		if (cres.getShared().get(id)) {
			result.add(new Spril(OpCode.WRITE, Register.A, MemAddr
					.deref(Register.D)));
		} else {
			result.add(new Spril(OpCode.STORE, Register.A, MemAddr
					.deref(Register.D)));
		}
		return result;
	}

	private List<Spril> binaryOperation(ExprContext fst, ExprContext snd,
			Operator op) {
		// compute subexpressions, push sub1 <op> sub2
		List<Spril> result = new ArrayList<>(visit(fst));
		result.addAll(visit(snd));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		result.add(new Spril(OpCode.POP, Register.B));
		result.add(new Spril(OpCode.COMPUTE, fst.getText() + " " + op.toCode()
				+ " " + snd.getText(), op, Register.B, Register.A, Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	private List<Spril> call(CallContext ctx, boolean wantResult) {
		List<Spril> result = new ArrayList<>();

		List<Type> types = ctx.params().expr().stream()
				.map(v -> cres.getTypes().get(v)).collect(Collectors.toList());
		FuncContext func = cres.getMatchingFunc(ctx.ID().getText(), types);
		if (func == null) {
			throw new RuntimeException("Could not find matching function: "
					+ ctx.ID().getText() + types.toString());
		}

		Spril jump = new Spril(OpCode.JUMP, "Jump to function "
				+ func.ID().getText(), Target.absolute(-1)); // invalid, will be
																// replaced

		calls.putIfAbsent(func, new ArrayList<>());
		calls.get(func).add(jump);

		List<ExprContext> exprs = new ArrayList<>(ctx.params().expr());
		for (int i = 0; i < exprs.size(); i++) {
			int offset = cres.getOffsets().get(func.typedparams().ID(i));
			ExprContext expr = exprs.get(i);

			result.addAll(visit(expr));
			if (result.get(result.size() - 1).equals(
					new Spril(OpCode.PUSH, Register.A)))
				result.remove(result.size() - 1);
			else
				result.add(new Spril(OpCode.POP, Register.A));
			if (cres.getShared().get(expr) != null
					&& cres.getShared().get(expr)) {
				result.add(new Spril(OpCode.WRITE, Register.A, MemAddr
						.direct(offset)));
			} else {
				result.add(new Spril(OpCode.STORE, Register.A, MemAddr
						.direct(offset)));
			}
		}

		int callResultOffset = cres.getOffsets().get(ctx);

		result.add(new Spril(OpCode.CONST, "Return addr", new Int(-1),
				Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		result.add(new Spril(OpCode.CONST, "Result addr", new Int(
				callResultOffset), Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		result.add(jump);

		if (wantResult) {
			result.add(new Spril(OpCode.LOAD, MemAddr.direct(callResultOffset),
					Register.A));
			result.add(new Spril(OpCode.PUSH, "Store Result", Register.A));
		}
		return result;
	}

	private TerminalNode getID(DerefIDContext ctx) {
		if (ctx.ID() != null)
			return ctx.ID();
		else
			return getID(ctx.derefID());
	}

	private Type getType(ParseTree ctx) {
		if (ctx instanceof NumExprContext)
			return Primitive.INT;
		else if (ctx instanceof TrueExprContext
				|| ctx instanceof FalseExprContext)
			return Primitive.BOOL;
		Type type = cres.getTypes().get(ctx);
		if (type == null)
			throw new IllegalArgumentException(String.format(
					"Node '%s' has no type.", ctx.getText()));
		return type;
	}

	private void invertComputation(Spril comp) {
		if (comp.getOpCode() != OpCode.COMPUTE)
			throw new IllegalArgumentException();
		Operand[] ops = comp.getOperands();
		ops[0] = ((Operator) ops[0]).invert();
	}

}