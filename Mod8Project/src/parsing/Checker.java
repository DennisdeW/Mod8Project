package parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import parsing.Type.Func;
import parsing.BaseGrammarParser.*;

public class Checker extends BaseGrammarBaseVisitor<Void> {

	private static final List<String> INVALID_NAMES = Arrays.asList("int",
			"bool", "void", "if", "else", "while", "for", "return", "and",
			"or", "xor", "true", "false", "def", "break", "not", "string");

	private Scope scope;
	private List<String> errors;
	private Map<FuncContext, Func> functions;
	private ParseTreeProperty<Type> types;
	private Func currentFunc;

	public Checker() {
		scope = new Scope();
		errors = new ArrayList<>();
		functions = new HashMap<>();
		types = new ParseTreeProperty<>();
		currentFunc = null;
	}

	public Void visitProgram(ProgramContext ctx) {
		ctx.decl().forEach(decl -> visit(decl));
		ctx.func().forEach(func -> visit(func));
		functions.forEach((fctx, func) -> processFunction(fctx, func));
		return null;
	}

	private void processFunction(FuncContext ctx, Func func) {
		currentFunc = func;
		visit(ctx.block());
		currentFunc = null;
	}

	public Void visitFunc(FuncContext ctx) {
		Type retType = Type.forName(ctx.type().getText());
		types.put(ctx, retType);

		String name = ctx.ID().getText();
		if (!checkName(ctx, name))
			return null;

		List<Type> argTypes = ctx.typedparams().type().stream()
				.map(t -> typeForName(ctx, t.getText()))
				.collect(Collectors.toList());
		Func func = new Func(name, retType, argTypes);
		scope.declare(func);
		return null;
	}

	public Void visitBlock(BlockContext ctx) {
		scope.openScope();
		ctx.stat().forEach(s -> visit(s));
		scope.closeScope();
		return null;
	}

	public Void visitDecl(DeclContext ctx) {
		String varId = ctx.ID().getText();
		Type type = typeForName(ctx, ctx.type().getText());
		if (scope.isDeclaredLocally(varId)) {
			error(ctx, "Duplicate declaration of variable '%s'", varId);
			return null;
		}
		scope.declare(varId, type);
		return null;
	}

	public Void visitAssign(AssignContext ctx) {
		String target = ctx.ID().getText();
		Type targetType = getType(ctx, target);
		if (targetType == Type.ERR_TYPE)
			return null;
		visit(ctx.expr());
		Type sourceType = getType(ctx.expr());
		checkType(ctx, targetType, sourceType);
		return null;
	}

	public Void visitReturnStat(ReturnStatContext ctx) {
		visit(ctx.expr());
		Type exprType = getType(ctx.expr());
		Type expected = currentFunc.getReturnType();
		if (exprType != expected) {
			error(ctx,
					"Return expression is of type '%s', but function '%s' should return '%s'.",
					exprType, currentFunc.getName(), expected);
		}
		return null;
	}
	
	public Void visitCallStat(CallStatContext ctx) {
		call(ctx.call(), null);
		return null;
	}

	public Void visitNegNumExpr(NegNumExprContext ctx) {
		visit(ctx.expr());
		if (checkType(ctx, Type.INT, ctx.expr()))
			types.put(ctx, Type.INT);
		else
			types.put(ctx, Type.ERR_TYPE);
		return null;
	}

	public Void visitNegBoolExpr(NegBoolExprContext ctx) {
		visit(ctx.expr());
		if (checkType(ctx, Type.BOOL, ctx.expr()))
			types.put(ctx, Type.BOOL);
		else
			types.put(ctx, Type.ERR_TYPE);
		return null;
	}

	public Void visitMultExpr(MultExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	public Void visitDivExpr(DivExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	public Void visitPlusExpr(PlusExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	public Void visitMinExpr(MinExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	public Void visitCompExpr(CompExprContext ctx) {
		ExprContext fst = ctx.expr(0);
		ExprContext snd = ctx.expr(1);
		visit(fst);
		visit(snd);
		checkType(ctx, Type.INT, fst);
		checkType(ctx, Type.INT, snd);
		types.put(ctx, Type.BOOL);
		return null;
	}

	public Void visitBoolOpExpr(BoolOpExprContext ctx) {
		ExprContext fst = ctx.expr(0);
		ExprContext snd = ctx.expr(1);
		visit(fst);
		visit(snd);
		checkType(ctx, Type.BOOL, fst);
		checkType(ctx, Type.BOOL, snd);
		types.put(ctx, Type.BOOL);
		return null;
	}

	public Void visitParExpr(ParExprContext ctx) {
		visit(ctx.expr());
		types.put(ctx, getType(ctx.expr()));
		return null;
	}

	public Void visitCallExpr(CallExprContext ctx) {
		Func function = call(ctx.call(), null);
		types.put(ctx, function.getReturnType());
		return null;
	}
	
	public Void visitIdExpr(IdExprContext ctx) {
		return null;
	}

	private Func call(CallContext ctx, Type expectedReturn) {
		List<Type> args = ctx.params().ID().stream()
				.map(t -> getType(t, t.getText())).collect(Collectors.toList());
		if (args.stream().anyMatch(type -> type == Type.ERR_TYPE))
			return null;
		Func func;
		if (expectedReturn != null) {
			func = new Func(ctx.ID().getText(), expectedReturn, args);
			if (!scope.isDeclared(func)) {
				error(ctx, "Function '%s' with types '%s' does not exist.", ctx
						.ID().getText(), args.toString());
				return null;
			}
			return func;
		} else {
			Func res = null;
			for (Type type : Type.values()) {
				func = new Func(ctx.ID().getText(), type, args);
				if (scope.isDeclared(func)) {
					if (res != null) {
						error(ctx, "Ambiguous function call: both '%s' and '%'s match.", func, res);
						return null;
					}
					res = func;
				}
			}
			if (res == null) {
				error(ctx, "Function '%s' with types '%s' does not exist.", ctx
						.ID().getText(), args.toString());
				return null;
			}
			return res;
		}
	}

	private void arithmeticExpr(ExprContext ctx, ExprContext fst,
			ExprContext snd) {
		visit(fst);
		visit(snd);
		checkType(ctx, Type.INT, fst);
		checkType(ctx, Type.INT, snd);
		types.put(ctx, Type.INT);
	}

	private boolean checkName(ParseTree tree, String name) {
		if (INVALID_NAMES.stream().anyMatch(inv -> name.equalsIgnoreCase(inv))) {
			error(tree, "Invalid name: '%s' is a reserved word.", name);
			return false;
		}
		return true;
	}

	private boolean checkType(ParseTree tree, Type expected, Type actual) {
		if (expected != actual) {
			if (actual == Type.ERR_TYPE)
				error(tree, "<Caused by earlier error>");
			else
				error(tree, "Type mismatch. Expected '%s', but saw '%s'.",
						expected.toString(), actual.toString());
			return false;
		}
		return true;
	}

	private boolean checkType(ParseTree tree, Type expected, String varId) {
		return checkType(tree, expected, scope.getType(varId));
	}

	private boolean checkType(ParseTree tree, Type expected,
			ParserRuleContext ctx) {
		return checkType(tree, expected, getType(ctx));
	}

	private Type getType(ParseTree tree, String varId) {
		if (scope.isDeclared(varId))
			return scope.getType(varId);
		error(tree, "Variable '%s' was not declared in this scope.", varId);
		return Type.ERR_TYPE;
	}

	private Type getType(ParserRuleContext ctx) {
		Type type = types.get(ctx);
		if (type == null)
			throw new IllegalArgumentException(String.format(
					"Node '%s' has no type.", ctx.getText()));
		return type;
	}

	private Type typeForName(ParseTree tree, String typeName) {
		try {
			return Type.forName(typeName);
		} catch (IllegalArgumentException e) {
			error(tree, e.getMessage());
		}
		return Type.ERR_TYPE;
	}

	private void error(ParseTree tree, String format, Object... args) {
		String res = "Parse Error ";
		if (tree instanceof ParserRuleContext) {
			ParserRuleContext ctx = (ParserRuleContext) tree;
			res += ctx.start.getLine() + ":"
					+ ctx.start.getCharPositionInLine();
		} else {
			TerminalNode node = (TerminalNode) tree;
			res += node.getSymbol().getLine() + ":"
					+ node.getSymbol().getCharPositionInLine();
		}
		res += " - " + String.format(format, args);
		errors.add(res);
	}
}
