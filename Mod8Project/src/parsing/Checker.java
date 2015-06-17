package parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import parsing.BaseGrammarParser.FuncContext;
import parsing.Type.Func;
import parsing.BaseGrammarParser.*;

public class Checker extends BaseGrammarBaseVisitor<Void> implements
		ANTLRErrorListener {

	private static final List<String>	INVALID_NAMES	= Arrays.asList("int",
																"bool", "void",
																"if", "else",
																"while", "for",
																"return",
																"and", "or",
																"xor", "true",
																"false", "def",
																"break", "not",
																"string");

	private Scope						scope;
	private List<String>				errors;
	private Map<FuncContext, Func>		functions;
	private ParseTreeProperty<Type>		types;
	private Func						currentFunc;
	private CheckResult					result;
	private boolean						dirty;

	public CheckResult check(ANTLRInputStream stream) {
		scope = new Scope();
		errors = new ArrayList<>();
		functions = new HashMap<>();
		types = new ParseTreeProperty<>();
		result = new CheckResult();
		currentFunc = null;
		dirty = false;
		BaseGrammarLexer lexer = new BaseGrammarLexer(stream);
		lexer.addErrorListener(this);
		BaseGrammarParser parser = new BaseGrammarParser(new CommonTokenStream(
				lexer));
		parser.addErrorListener(this);
		ProgramContext prog = parser.program();
		if (dirty) {
			throw new RuntimeException("ANTLR Reported errors, see console.");
		}
		visit(prog);
		return result;
	}

	public boolean hasErrors() {
		return errors.size() != 0;
	}

	public List<String> getErrors() {
		return new ArrayList<>(errors);
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
		visit(ctx.typedparams());
		Func func = new Func(name, retType, argTypes);
		scope.declare(func);
		functions.put(ctx, func);
		result.getTypes().put(ctx, retType);
		return null;
	}

	public Void visitTypedparams(TypedparamsContext ctx) {
		for (int i = 0; i < ctx.type().size(); i++) {
			String name = ctx.ID(i).getText();
			Type type = typeForName(ctx, ctx.type(i).getText());
			if (scope.isDeclaredLocally(name)) {
				error(ctx, "Duplicate parameter '%s'.", name);
			} else {
				scope.declare(name, type);
				//Will be placed on stack
			}
		}
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
		if (!checkName(ctx, varId))
			return null;

		Type type = typeForName(ctx, ctx.type().getText());
		if (scope.isDeclaredLocally(varId)) {
			error(ctx, "Duplicate declaration of variable '%s'", varId);
			return null;
		}
		scope.declare(varId, type);
		result.getTypes().put(ctx, type);
		result.getOffsets().put(ctx, scope.getOffset(varId));
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
		Type expected = currentFunc.getReturnType();
		if (ctx.expr() != null) {
			visit(ctx.expr());
			Type exprType = getType(ctx.expr());
			if (exprType == Type.ERR_TYPE)
				error(ctx,
						"<Unable to check function return type because the expression cannot be evaluated>");
			else if (exprType != expected) {
				error(ctx,
						"Return expression is of type '%s', but function '%s' should return '%s'.",
						exprType, currentFunc.getName(), expected);
			}
		} else {
			if (expected != Type.VOID) {
				error(ctx,
						"Returning void in function '%s' with non-void return type '%s'.",
						currentFunc.getName(), expected);
			}
		}
		return null;
	}

	public Void visitCallStat(CallStatContext ctx) {
		call(ctx.call(), null);
		return null;
	}

	public Void visitIfStat(IfStatContext ctx) {
		for (int i = 0; i < ctx.expr().size(); i++) {
			visit(ctx.expr(i));
			checkType(ctx, Type.BOOL, getType(ctx.expr(i)));
		}
		ctx.block().forEach(block -> visit(block));
		return null;
	}

	public Void visitWhileStat(WhileStatContext ctx) {
		visit(ctx.expr());
		checkType(ctx, Type.BOOL, getType(ctx.expr()));
		visit(ctx.block());
		return null;
	}

	public Void visitForStat(ForStatContext ctx) {
		if (!checkType(ctx, Type.INT,
				typeForName(ctx, ctx.decl().type().getText()))) {
			return null;
		}
		visit(ctx.decl());
		visit(ctx.expr());
		visit(ctx.assign());
		visit(ctx.block());
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
		types.put(ctx, function != null ? function.getReturnType()
				: Type.ERR_TYPE);
		return null;
	}

	public Void visitIdExpr(IdExprContext ctx) {
		types.put(ctx, getType(ctx, ctx.ID().getText()));
		return null;
	}

	public Void visitNumExpr(NumExprContext ctx) {
		types.put(ctx, Type.INT);
		return null;
	}

	public Void visitTrueExpr(TrueExprContext ctx) {
		types.put(ctx, Type.BOOL);
		return null;
	}

	public Void visitFalseExpr(FalseExprContext ctx) {
		types.put(ctx, Type.BOOL);
		return null;
	}

	public Void visitVal(ValContext ctx) {
		if (ctx.TRUE() != null || ctx.FALSE() != null)
			types.put(ctx, Type.BOOL);
		else if (ctx.NUMBER() != null)
			types.put(ctx, Type.INT);
		else
			types.put(ctx, getType(ctx, ctx.ID().getText()));
		return null;
	}

	private Func call(CallContext ctx, Type expectedReturn) {
		List<Type> args = new ArrayList<>();
		ctx.params().val().stream().forEachOrdered(val -> {
			visit(val);
			args.add(getType(val));
		});
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
						error(ctx,
								"Ambiguous function call: both '%s' and '%s' match.",
								func, res);
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

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
			boolean arg4, BitSet arg5, ATNConfigSet arg6) {
		dirty = true;

	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2,
			int arg3, BitSet arg4, ATNConfigSet arg5) {
		dirty = true;

	}

	@Override
	public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
			int arg3, int arg4, ATNConfigSet arg5) {
		dirty = true;

	}

	@Override
	public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
			int arg3, String arg4, RecognitionException arg5) {
		dirty = true;
	}

	public static class CheckResult {
		private ParseTreeProperty<Type>		types;
		private ParseTreeProperty<Integer>	offsets;
		private Map<FuncContext, Integer>		funcAddrs;
		private List<String>			errors;
		
		CheckResult(ParseTreeProperty<Type> types,
				ParseTreeProperty<Integer> offsets,
				Map<FuncContext, Integer> funcAddrs, List<String> errors) {
			this.types = types;
			this.offsets = offsets;
			this.funcAddrs = funcAddrs;
			this.errors = errors;
		}

		CheckResult() {
			this.types = new ParseTreeProperty<>();
			this.offsets = new ParseTreeProperty<>();
			this.funcAddrs = new HashMap<>();
			this.errors = new ArrayList<>();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((errors == null) ? 0 : errors.hashCode());
			result = prime * result
					+ ((funcAddrs == null) ? 0 : funcAddrs.hashCode());
			result = prime * result
					+ ((offsets == null) ? 0 : offsets.hashCode());
			result = prime * result + ((types == null) ? 0 : types.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CheckResult other = (CheckResult) obj;
			if (errors == null) {
				if (other.errors != null)
					return false;
			} else if (!errors.equals(other.errors))
				return false;
			if (funcAddrs == null) {
				if (other.funcAddrs != null)
					return false;
			} else if (!funcAddrs.equals(other.funcAddrs))
				return false;
			if (offsets == null) {
				if (other.offsets != null)
					return false;
			} else if (!offsets.equals(other.offsets))
				return false;
			if (types == null) {
				if (other.types != null)
					return false;
			} else if (!types.equals(other.types))
				return false;
			return true;
		}
		
		ParseTreeProperty<Type> getTypes() {
			return types;
		}

		void setTypes(ParseTreeProperty<Type> types) {
			this.types = types;
		}

		ParseTreeProperty<Integer> getOffsets() {
			return offsets;
		}

		void setOffsets(ParseTreeProperty<Integer> offsets) {
			this.offsets = offsets;
		}

		Map<FuncContext, Integer> getFuncAddrs() {
			return funcAddrs;
		}

		void setFuncAddrs(Map<FuncContext, Integer> funcAddrs) {
			this.funcAddrs = funcAddrs;
		}

		List<String> getErrors() {
			return errors;
		}

		void setErrors(List<String> errors) {
			this.errors = errors;
		}

		@Override
		public String toString() {
			return "CheckResult [types=" + types + ", offsets=" + offsets
					+ ", funcAddrs=" + funcAddrs + ", errors=" + errors + "]";
		}
	}
}
