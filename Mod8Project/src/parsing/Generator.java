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
import org.antlr.v4.runtime.tree.TerminalNode;

import parsing.BaseGrammarParser.*;
import parsing.BaseGrammarParser.BlockStatContext;
import parsing.BaseGrammarParser.FuncContext;
import parsing.Checker.CheckResult;
import parsing.Type.Func;
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

	public static final Func				MAIN_FUNC_SIG	= new Func("main",
																	Type.INT);

	private CheckResult						cres;
	private Program							prog;
	private Map<FuncContext, List<Spril>>	functions;
	private Map<FuncContext, Integer>		functionAddrs;
	private Map<FuncContext, List<Spril>>	calls;

	private List<Spril> binOp(ExprContext fst, ExprContext snd, Operator op) {
		// compute subexpressions, push sub1 <op> sub2
		List<Spril> result = new ArrayList<>(visit(fst));
		result.addAll(visit(snd));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		result.add(new Spril(OpCode.POP, Register.B));
		result.add(new Spril(OpCode.COMPUTE, op, Register.B, Register.A,
				Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public Program compile(ProgramContext ctx, CheckResult cres) {
		this.cres = cres;
		this.prog = new Program();
		this.functions = new HashMap<>();
		this.functionAddrs = new HashMap<>();
		this.calls = new HashMap<>();

		List<Spril> instrs = new ArrayList<>();

		instrs.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
		instrs.add(new Spril(OpCode.PUSH, Register.A));
		instrs.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
		instrs.add(new Spril(OpCode.PUSH, Register.A));

		ctx.decl().stream().map(decl -> visit(decl))
				.forEach(decl -> decl.forEach(ins -> instrs.add(ins)));
		ctx.func().forEach(func -> functions.put(func, visit(func)));

		FuncContext mainFuncCtx = cres.getMatchingFunc(MAIN_FUNC_SIG.getName(),
				MAIN_FUNC_SIG.getArgs());
		if (Type.forName(mainFuncCtx.type().getText()) != MAIN_FUNC_SIG
				.getReturnType())
			System.err.println("WARNING: Main function return type should be "
					+ MAIN_FUNC_SIG.getReturnType());

		Spril jumpToMain = new Spril(OpCode.JUMP, Target.absolute(-1));
		instrs.add(jumpToMain);

		int addr = 5 + ctx.decl().size() * 2;
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

		instrs.add(new Spril(OpCode.END_PROG));

		instrs.get(0).getOperands()[0] = new Int(instrs.size() - 1);
		jumpToMain.getOperands()[0] = Target.absolute(functionAddrs
				.get(mainFuncCtx));

		instrs.forEach(ins -> prog.addInstruction(ins));
		return prog;
	}

	private void invertComputation(Spril comp) {
		if (comp.getOpCode() != OpCode.COMPUTE)
			throw new IllegalArgumentException();
		Operand[] ops = comp.getOperands();
		ops[0] = ((Operator) ops[0]).invert();
	}

	public List<Spril> visitBoolOpExpr(BoolOpExprContext ctx) {
		return binOp(ctx.expr(0), ctx.expr(1),
				Operator.boolOp(ctx.boolOp().getText()));
	}

	public List<Spril> visitCallExpr(CallExprContext ctx) {
		return call(ctx.call(), true);
	}

	private List<Spril> call(CallContext ctx, boolean wantResult) {
		List<Spril> result = new ArrayList<>();

		List<Type> types = ctx.params().val().stream()
				.map(v -> cres.valType(v)).collect(Collectors.toList());
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

		List<ValContext> vals = new ArrayList<>(ctx.params().val());
		// Collections.reverse(vals);
		/*
		 * vals.forEach(val -> { if (val.NUMBER() != null) { int num =
		 * Integer.parseInt(val.NUMBER().getText()); result.add(new
		 * Spril(OpCode.CONST, new Int(num), Register.A)); result.add(new
		 * Spril(OpCode.PUSH, Register.A)); } else if (val.TRUE() != null) {
		 * result.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
		 * result.add(new Spril(OpCode.PUSH, Register.A)); } else if
		 * (val.FALSE() != null) { result.add(new Spril(OpCode.PUSH,
		 * Register.ZERO)); } else { int offset =
		 * cres.getOffsets().get(val.ID()); result.add(new Spril(OpCode.LOAD,
		 * MemAddr.direct(offset), Register.A)); result.add(new
		 * Spril(OpCode.PUSH, Register.A)); } });
		 */
		for (int i = 0; i < vals.size(); i++) {
			int offset = cres.getOffsets().get(func.typedparams().ID(i));
			ValContext val = vals.get(i);
			if (val.NUMBER() != null) {
				int num = Integer.parseInt(val.NUMBER().getText());
				result.add(new Spril(OpCode.CONST, new Int(num), Register.A));
				result.add(new Spril(OpCode.STORE, Register.A, MemAddr
						.direct(offset)));
			} else if (val.TRUE() != null) {
				result.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
				result.add(new Spril(OpCode.STORE, Register.A, MemAddr
						.direct(offset)));
			} else if (val.FALSE() != null) {
				result.add(new Spril(OpCode.STORE, Register.ZERO, MemAddr
						.direct(offset)));
			} else {
				int callerOffset = cres.getOffsets().get(val.ID());
				boolean resultIsShared = cres.getShared().get(val.ID());
			//	result.add(new Spril(OpCode.LOAD, MemAddr.direct(callerOffset),
			//			Register.A));
			//	result.add(new Spril(OpCode.STORE, Register.A, MemAddr
			//			.direct(offset)));
			}
		}

		int callResultOffset = cres.getOffsets().get(ctx);

		result.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		result.add(new Spril(OpCode.CONST, new Int(callResultOffset),
				Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		result.add(jump);

		if (wantResult) {
			result.add(new Spril(OpCode.LOAD, MemAddr.direct(callResultOffset),
					Register.A));
			result.add(new Spril(OpCode.PUSH, Register.A));
		}
		return result;
	}

	public List<Spril> visitCompExpr(CompExprContext ctx) {
		return binOp(ctx.expr(0), ctx.expr(1),
				Operator.comparator(ctx.comp().getText()));
	}

	public List<Spril> visitDecl(DeclContext ctx) {
		return assign(ctx.ID(), ctx.expr());
	}

	public List<Spril> visitDeclStat(DeclStatContext ctx) {
		return visit(ctx.decl());
	}

	public List<Spril> visitAssign(AssignContext ctx) {
		return assign(ctx.ID(), ctx.expr());
	}

	public List<Spril> visitAssignStat(AssignStatContext ctx) {
		return visit(ctx.assign());
	}

	public List<Spril> visitFunc(FuncContext ctx) {
		List<Spril> result = new ArrayList<>();
		result.addAll(visit(ctx.topLevelBlock().block()));
		result.get(0).setComment("Function " + ctx.ID().getText());
		return result;

	}

	private List<Spril> assign(TerminalNode id, ExprContext expr) {
		int offset = cres.getOffsets().get(id);
		List<Spril> result = new ArrayList<>(visit(expr));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		if (cres.getShared().get(id)) {
			result.add(new Spril(OpCode.WRITE, Register.A, MemAddr
					.direct(offset)));
		} else {
			result.add(new Spril(OpCode.STORE, Register.A, MemAddr
					.direct(offset)));
		}
		return result;
	}

	public List<Spril> visitBlockStat(BlockStatContext ctx) {
		return visit(ctx.block());
	}

	public List<Spril> visitBlock(BlockContext ctx) {
		List<Spril> result = new ArrayList<>();
		for (StatContext stat : ctx.stat())
			result.addAll(visit(stat));
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
					.relative(jumpDistance == 0 ? 1 : jumpDistance)));
		}

		if (ctx.IF().size() == ctx.ELSE().size()) {
			result.add(new Spril(OpCode.JUMP, Target.relative(totalBlockSize
					+ 1 - blockSizes.get(ctx.block().get(blocks.size() - 1)))));
		} else {
			result.add(new Spril(OpCode.JUMP, Target
					.relative(totalBlockSize + 2)));
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

	public List<Spril> visitDivExpr(DivExprContext ctx) {
		return binOp(ctx.expr(0), ctx.expr(1), Operator.DIV);
	}

	public List<Spril> visitFalseExpr(FalseExprContext ctx) {
		// push Zero
		List<Spril> result = new ArrayList<>();
		result.add(new Spril(OpCode.PUSH, Register.ZERO));
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

	public List<Spril> visitMinExpr(MinExprContext ctx) {
		return binOp(ctx.expr(0), ctx.expr(1), Operator.SUB);
	}

	public List<Spril> visitMultExpr(MultExprContext ctx) {
		return binOp(ctx.expr(0), ctx.expr(1), Operator.MUL);
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
		result.add(new Spril(OpCode.PUSH));
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

	public List<Spril> visitParExpr(ParExprContext ctx) {
		return visit(ctx.expr());
	}

	public List<Spril> visitPlusExpr(PlusExprContext ctx) {
		return binOp(ctx.expr(0), ctx.expr(1), Operator.ADD);
	}

	public List<Spril> visitModExpr(ModExprContext ctx) {
		return binOp(ctx.expr(0), ctx.expr(1), Operator.MOD);
	}

	public List<Spril> visitTrueExpr(TrueExprContext ctx) {
		// load and push -1
		List<Spril> result = new ArrayList<>();
		result.add(new Spril(OpCode.CONST, new Int(-1), Register.A));
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

	public List<Spril> visitForStat(ForStatContext ctx) {
		List<Spril> result = new ArrayList<>(visit(ctx.decl()));

		List<Spril> cond = visit(ctx.expr());
		List<Spril> assign = visit(ctx.assign());
		List<Spril> body = visit(ctx.block());

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
		result.add(new Spril(OpCode.BRANCH, Register.A, Target.relative(body
				.size() + assign.size() + 2)));
		result.addAll(body);
		result.addAll(assign);
		result.add(new Spril(OpCode.JUMP, Target.relative(-(body.size()
				+ cond.size() + assign.size() + 1))));

		return result;
	}

	public List<Spril> visitReturnStat(ReturnStatContext ctx) {
		List<Spril> result = new ArrayList<>();
		// TODO
		result.addAll(visit(ctx.expr()));
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));

		result.add(new Spril(OpCode.POP, Register.B));
		result.add(new Spril(OpCode.POP, Register.C));

		result.add(new Spril(OpCode.COMPUTE, Operator.LT, Register.B,
				Register.ZERO, Register.D));
		result.add(new Spril(OpCode.BRANCH, Register.D, Target.relative(1)));

		result.add(new Spril(OpCode.STORE, Register.A, MemAddr
				.deref(Register.B)));

		result.add(new Spril(OpCode.JUMP, Target.indirect(Register.C)));
		return result;
	}

	public static void main(String[] args) {
		String prog = "def int main() { if (multipleOf(3,9)) { return 5; } else { return 1; }}"
				+ " def bool multipleOf(int x, int y) { shared int base = x; while (x < y) { x = x + base; } return x == y; }";
		ProgramContext ctx = new BaseGrammarParser(new CommonTokenStream(
				new BaseGrammarLexer(new ANTLRInputStream(prog)))).program();
		CheckResult cres = new Checker().check(ctx);
		System.out.println(cres.getErrors());
		Generator gen = new Generator();
		Program list = gen.compile(ctx, cres);
		System.out.println(list.getInstructions().stream()
				.map(s -> s.toString()).collect(Collectors.joining("\n")));
		ProgramRunner.runAndRemove(list);
	}
}
