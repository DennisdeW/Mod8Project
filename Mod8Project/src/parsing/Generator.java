package parsing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import parsing.BaseGrammarParser.*;
import parsing.BaseGrammarParser.BlockStatContext;
import parsing.Checker.CheckResult;
import translation.Int;
import translation.MemAddr;
import translation.OpCode;
import translation.Operator;
import translation.Program;
import translation.Register;
import translation.Spril;
import translation.Target;
import write.ProgramRunner;

public class Generator extends BaseGrammarBaseVisitor<List<Spril>> {

	private CheckResult cres;
	private Program prog;
	private Map<FuncContext, List<Spril>> functions;

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
		result.add(new Spril(OpCode.COMPUTE, op, Register.A, Register.B,
				Register.A));
		result.add(new Spril(OpCode.PUSH, Register.A));
		return result;
	}

	public Program compile(ProgramContext ctx, CheckResult cres) {
		this.cres = cres;
		this.prog = new Program();
		this.functions = new HashMap<>();

		List<Spril> instrs = new ArrayList<>();

		ctx.decl().stream().map(decl -> visit(decl))
				.forEach(decl -> decl.forEach(ins -> instrs.add(ins)));
		ctx.func().stream().map(func -> visit(func))
				.forEach(func -> func.forEach(ins -> instrs.add(ins)));

		instrs.add(new Spril(OpCode.END_PROG));

		instrs.forEach(ins -> prog.addInstruction(ins));
		return prog;
	}

	public List<Spril> visitBoolOpExpr(BoolOpExprContext ctx) {
		return binOp(ctx.expr(0), ctx.expr(1),
				Operator.boolOp(ctx.boolOp().getText()));
	}

	public List<Spril> visitCallExpr(CallExprContext ctx) {
		List<Spril> result = new ArrayList<>();
		// TODO: do call
		result.add(new Spril(OpCode.NOP));
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

		for (TerminalNode param : ctx.typedparams().ID())
			result.add(new Spril(OpCode.STORE, Register.ZERO, MemAddr
					.direct(cres.getOffsets().get(param))));
		result.addAll(visit(ctx.block()));
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
		result.add(new Spril(OpCode.STORE, Register.A, MemAddr.direct(offset)));
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
				+ 2 * exprSizes.size() + 1;

		int totalBlockSize = blockSizes.values().stream().mapToInt(i -> i)
				.sum()
				+ blockSizes.size() - 1;

		for (int i = 0; i < ctx.expr().size(); i++) {
			totalExprSize -= 2 + exprSizes.get(ctx.expr(i));
			result.addAll(exprs.get(i));
			if (result.get(result.size() - 1).equals(
					new Spril(OpCode.PUSH, Register.A)))
				result.remove(result.size() - 1);
			else
				result.add(new Spril(OpCode.POP, Register.A));
			int jumpDistance = totalExprSize + 1;
			for (int j = 0; j < i; j++) {
				jumpDistance += blockSizes.get(ctx.block().get(i)) + 1;
			}
			result.add(new Spril(OpCode.BRANCH, Register.A, Target
					.relative(jumpDistance)));
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
		result.add(new Spril(OpCode.LOAD, MemAddr.direct(offset), Register.A));
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
		body.add(new Spril(OpCode.NOP));

		result.addAll(cond);
		if (result.get(result.size() - 1).equals(
				new Spril(OpCode.PUSH, Register.A)))
			result.remove(result.size() - 1);
		else
			result.add(new Spril(OpCode.POP, Register.A));
		result.add(new Spril(OpCode.CONST, new Int(-1), Register.B));
		result.add(new Spril(OpCode.COMPUTE, Operator.XOR, Register.A,
				Register.B, Register.A));
		result.add(new Spril(OpCode.BRANCH, Register.A, Target.relative(body
				.size() + 2)));
		result.addAll(body);
		result.add(new Spril(OpCode.JUMP, Target.relative(-(body.size()
				+ cond.size() + 3))));
		return result;
	}

	public List<Spril> visitForStat(ForStatContext ctx) {
		List<Spril> result = new ArrayList<>(visit(ctx.decl()));

		List<Spril> cond = visit(ctx.expr());
		List<Spril> assign = visit(ctx.assign());
		List<Spril> body = visit(ctx.block());

		result.addAll(cond);
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
		return result;
	}

	public static void main(String[] args) {
		String prog = "int arg = 3; def int print() { if (arg == 1) { return 10; } else if (arg == 3) { return 5; } else if (arg == 2) {return 7;} else { return 42; }}";
		ProgramContext ctx = new BaseGrammarParser(new CommonTokenStream(
				new BaseGrammarLexer(new ANTLRInputStream(prog)))).program();
		CheckResult cres = new Checker().check(ctx);
		Generator gen = new Generator();
		Program list = gen.compile(ctx, cres);
		System.out.println(list.getInstructions().stream()
				.map(s -> s.toString()).collect(Collectors.joining("\n")));
		ProgramRunner.runAndRemove(list);
	}
}
