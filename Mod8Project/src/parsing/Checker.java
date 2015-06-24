package parsing;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
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
import parsing.BaseGrammarParser.TypedparamsContext;
import parsing.Type.Func;
import parsing.BaseGrammarParser.*;

public class Checker extends BaseGrammarBaseVisitor<Void> implements
		ANTLRErrorListener {

	private static final List<String> INVALID_NAMES = Arrays.asList("int",
			"bool", "void", "if", "else", "while", "for", "return", "and",
			"or", "xor", "true", "false", "def", "break", "not", "string");

	private Scope scope;
	private List<String> errors;
	private Map<FuncContext, Func> functions;
	private Set<String> locks;
	private ParseTreeProperty<Type> types;
	private ParseTreeProperty<Boolean> shared;
	private Func currentFunc;
	private Map<Func, TypedparamsContext> params;
	private Map<Func, List<Func>> callTree;
	private CheckResult result;
	private boolean dirty;

	public CheckResult check(ANTLRInputStream stream) {
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
		return check(prog);
	}

	public CheckResult check(ProgramContext prog) {
		scope = new Scope();
		errors = new ArrayList<>();
		functions = new HashMap<>();
		params = new HashMap<>();
		types = new ParseTreeProperty<>();
		shared = new ParseTreeProperty<>();
		callTree = new HashMap<>();
		result = new CheckResult();
		locks = new HashSet<>();
		currentFunc = null;
		visit(prog);
		result.setTypes(types);
		result.setShared(shared);
		result.buildLocks(locks);
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
		Func main = functions
				.values()
				.stream()
				.filter(f -> f.equals(Generator.MAIN_FUNC_SIG))
				.findAny()
				.orElseGet(
						() -> {
							error(ctx,
									"No main function was defined. Please define the main function as %s",
									Generator.MAIN_FUNC_SIG);
							return null;
						});
		if (main != null) {
			Queue<Func> funcs = new ArrayDeque<>();
			funcs.offer(main);
			while (!funcs.isEmpty()) {
				Func current = funcs.poll();
				FuncContext fctx = functions.entrySet().stream()
						.filter(e -> e.getValue().equals(current)).findAny()
						.map(e -> e.getKey()).get();
				processFunction(fctx, current);
				List<Func> callees;
				if ((callees = callTree.get(current)) != null)
					callees.forEach(callee -> funcs.offer(callee));
			}
		}
		// functions.forEach((fctx, func) -> processFunction(fctx, func));
		return null;
	}

	private void processFunction(FuncContext ctx, Func func) {
		currentFunc = func;
		visit(ctx.topLevelBlock());
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
		params.put(func, ctx.typedparams());
		scope.declare(func);
		functions.put(ctx, func);
		result.getFunctions().add(ctx);
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
				result.getOffsets().put(ctx.ID(i), scope.getOffset(name));
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

	public Void visitTopLevelBlock(TopLevelBlockContext ctx) {
		scope.openScope();
		visit(params.get(currentFunc));
		ctx.block().stat().forEach(s -> visit(s));
		scope.closeScope();
		return null;
	}

	public Void visitLockStat(LockStatContext ctx) {
		locks.add(ctx.ID().getText());
		if (ctx.block().toString().matches("\\breturn\\b")) {
			error(ctx,
					"Illegal return statement in locked block %s. (Returns from locks are not allowed)",
					ctx.ID().getText());
		}
		visit(ctx.block());
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
		scope.declare(varId, type, ctx.SHARED() != null);
		types.put(ctx, type);
		shared.put(ctx.ID(), ctx.SHARED() != null);
		result.getOffsets().put(ctx.ID(), scope.getOffset(varId));

		visit(ctx.expr());
		checkType(ctx, type, ctx.expr());

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
		shared.put(ctx.ID(), scope.isShared(target));
		result.getOffsets().put(ctx.ID(), scope.getOffset(target));
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

	public Void visitModExpr(ModExprContext ctx) {
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
		shared.put(ctx.ID(), scope.isShared(ctx.ID().getText()));
		result.getOffsets().put(ctx.ID(), scope.getOffset(ctx.ID().getText()));
		return null;
	}

	public Void visitNumExpr(NumExprContext ctx) {
		types.put(ctx, Type.INT);
		return null;
	}

	public Void visitOutStat(OutStatContext ctx) {
		visit(ctx.val());
		return null;
	}
	
	public Void visitInStat(InStatContext ctx) {
		visit(ctx.ID());
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
		else if (ctx.ID() != null) {
			types.put(ctx, getType(ctx, ctx.ID().getText()));
			result.getOffsets().put(ctx.ID(),
					scope.getOffset(ctx.ID().getText()));
			shared.put(ctx.ID(), scope.isShared(ctx.ID().getText()));
			return null;
		} else {
			types.put(ctx, Type.INT);
		}
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

			callTree.putIfAbsent(currentFunc, new ArrayList<>());
			callTree.get(currentFunc).add(res);

			scope.declare(res);
			scope.declare(res.getReturnName(), res.getReturnType());
			result.getOffsets().put(ctx, scope.getOffset(res.getReturnName()));
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
		result.getErrors().add(res);
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

	public class CheckResult {
		private ParseTreeProperty<Type> types;
		private Map<ParseTree, Integer> offsets;
		private Set<FuncContext> functions;
		private List<String> errors;
		private ParseTreeProperty<Boolean> shared;
		private Map<String, Integer> locks;

		public CheckResult(ParseTreeProperty<Type> types,
				Map<ParseTree, Integer> offsets, Set<FuncContext> functions,
				List<String> errors, ParseTreeProperty<Boolean> shared,
				Map<String, Integer> locks) {
			super();
			this.types = types;
			this.offsets = offsets;
			this.functions = functions;
			this.errors = errors;
			this.shared = shared;
			this.locks = locks;
		}

		public CheckResult() {
			this.types = new ParseTreeProperty<>();
			this.offsets = new HashMap<>();
			this.functions = new HashSet<>();
			this.errors = new ArrayList<>();
			this.shared = new ParseTreeProperty<>();
			this.locks = new HashMap<>();
		}

		public ParseTreeProperty<Type> getTypes() {
			return types;
		}

		public void setTypes(ParseTreeProperty<Type> types) {
			this.types = types;
		}

		public Map<ParseTree, Integer> getOffsets() {
			return offsets;
		}

		public void setOffsets(Map<ParseTree, Integer> offsets) {
			this.offsets = offsets;
		}

		public Set<FuncContext> getFunctions() {
			return functions;
		}

		public void setFunctions(Set<FuncContext> functions) {
			this.functions = functions;
		}

		public List<String> getErrors() {
			return errors;
		}

		public void setErrors(List<String> errors) {
			this.errors = errors;
		}

		public ParseTreeProperty<Boolean> getShared() {
			return shared;
		}

		public void setShared(ParseTreeProperty<Boolean> shared) {
			this.shared = shared;
		}

		public Map<String, Integer> getLocks() {
			return locks;
		}

		public void setLocks(Map<String, Integer> locks) {
			this.locks = locks;
		}

		int maxOffset(boolean shared) {
			return this.offsets
					.entrySet()
					.stream()
					.filter(e -> (this.shared == null) != shared
							|| this.shared.get(e.getKey()))
					.mapToInt(i -> i.getValue()).max().orElse(0);
		}

		int funcCount() {
			return this.functions.size();
		}

		int localStaticMemSize() {
			return maxOffset(false) + funcCount();
		}

		int sharedStaticMemSize() {
			return maxOffset(true) + locks.size();
		}

		FuncContext getMatchingFunc(String name, List<Type> types) {
			return this.functions
					.stream()
					.filter(func -> func.typedparams().type().stream()
							.map(t -> typeForName(func, t.getText()))
							.collect(Collectors.toList()).equals(types)
							&& func.ID().getText().equals(name)).findAny()
					.orElse(null);
		}

		Type valType(ValContext ctx) {
			if (ctx.NUMBER() != null || ctx.SPID() != null)
				return Type.INT;
			else if (ctx.TRUE() != null || ctx.FALSE() != null)
				return Type.BOOL;
			Type res = types.get(ctx);
			if (res == null)
				throw new RuntimeException("Could not get type of " + ctx.ID());
			return res;
		}

		void buildLocks(Set<String> locks) {
			int addr = sharedStaticMemSize() + 1;
			for (String lock : locks)
				this.locks.put(lock, addr++);
		}

		@Override
		public String toString() {
			return "CheckResult [types=" + types + ", offsets=" + offsets
					+ ", funcAddrs=" + functions + ", errors=" + errors + "]";
		}
	}
}
