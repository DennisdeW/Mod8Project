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

import parsing.BaseGrammarParser.DerefIDContext;
import parsing.BaseGrammarParser.FuncContext;
import parsing.BaseGrammarParser.TypedparamsContext;
import parsing.Type.Func;
import parsing.Type.Pointer;
import parsing.BaseGrammarParser.*;

/**
 * Class Checker is used to parse a program
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public class Checker extends BaseGrammarBaseVisitor<Void> implements
		ANTLRErrorListener {

	public class CheckResult {
		private ParseTreeProperty<IType> types;
		private Map<ParseTree, Integer> offsets;
		private Set<FuncContext> functions;
		private List<String> errors;
		private ParseTreeProperty<Boolean> shared;
		private Map<String, Integer> locks;

		public CheckResult() {
			this.types = new ParseTreeProperty<>();
			this.offsets = new HashMap<>();
			this.functions = new HashSet<>();
			this.errors = new ArrayList<>();
			this.shared = new ParseTreeProperty<>();
			this.locks = new HashMap<>();
		}

		public CheckResult(ParseTreeProperty<IType> types,
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

		public List<String> getErrors() {
			return errors;
		}

		public Set<FuncContext> getFunctions() {
			return functions;
		}

		public Map<String, Integer> getLocks() {
			return locks;
		}

		public Map<ParseTree, Integer> getOffsets() {
			return offsets;
		}

		public ParseTreeProperty<Boolean> getShared() {
			return shared;
		}

		public ParseTreeProperty<IType> getTypes() {
			return types;
		}

		public void setErrors(List<String> errors) {
			this.errors = errors;
		}

		public void setFunctions(Set<FuncContext> functions) {
			this.functions = functions;
		}

		public void setLocks(Map<String, Integer> locks) {
			this.locks = locks;
		}

		public void setOffsets(Map<ParseTree, Integer> offsets) {
			this.offsets = offsets;
		}

		public void setShared(ParseTreeProperty<Boolean> shared) {
			this.shared = shared;
		}

		public void setTypes(ParseTreeProperty<IType> types) {
			this.types = types;
		}

		@Override
		public String toString() {
			return "CheckResult [types=" + types + ", offsets=" + offsets
					+ ", funcAddrs=" + functions + ", errors=" + errors + "]";
		}

		void buildLocks(Set<String> locks) {
			int addr = sharedStaticMemSize() + 1;
			for (String lock : locks)
				this.locks.put(lock, addr++);
		}

		int funcCount() {
			return this.functions.size();
		}

		FuncContext getMatchingFunc(String name, List<IType> types) {
			return this.functions
					.stream()
					.filter(func -> func.typedparams().type().stream()
							.map(t -> typeForName(func, t.getText()))
							.collect(Collectors.toList()).equals(types)
							&& func.ID().getText().equals(name)).findAny()
					.orElse(null);
		}

		int localStaticMemSize() {
			return maxOffset(false) + funcCount();
		}

		int maxOffset(boolean shared) {
			return this.offsets
					.entrySet()
					.stream()
					.filter(e -> (this.shared == null) != shared
							|| (this.shared.get(e.getKey()) != null && this.shared.get(e.getKey())))
					.mapToInt(i -> i.getValue()).max().orElse(0);
		}

		int sharedStaticMemSize() {
			return maxOffset(true) + locks.size();
		}

		IType valType(ValContext ctx) {
			if (ctx.NUMBER() != null || ctx.SPID() != null)
				return Type.INT;
			else if (ctx.TRUE() != null || ctx.FALSE() != null)
				return Type.BOOL;
			IType res = types.get(ctx);
			if (res == null)
				throw new RuntimeException("Could not get type of " + ctx.ID());
			return res;
		}
	}

	private static final List<String> INVALID_NAMES = Arrays.asList("int",
			"bool", "void", "if", "else", "while", "for", "return", "and",
			"or", "xor", "true", "false", "def", "break", "not", "string");
	private Scope scope;
	private List<String> errors;
	private Map<FuncContext, Func> functions;
	private Set<String> locks;
	private ParseTreeProperty<IType> types;
	private ParseTreeProperty<Boolean> shared;
	private Func currentFunc;
	private Map<Func, TypedparamsContext> params;
	private Map<Func, List<Func>> callTree;
	private CheckResult result;

	private boolean dirty;

	/**
	 * Check returns a checked program
	 * 
	 * @param stream
	 *            The stream that needs to be processed
	 * @return CheckedResult
	 */
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

	/**
	 * Check returns a check program
	 * 
	 * @param prog
	 *            The program that needs to be processed
	 * @return CheckedResult
	 */
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

	/**
	 * The list with errors that occurred while processing the program
	 * 
	 * @return List<String> errors
	 */
	public List<String> getErrors() {
		return new ArrayList<>(errors);
	}

	/**
	 * Returns whether any errors occurred while processing a program
	 * 
	 * @return Whether an error has occurred
	 */
	public boolean hasErrors() {
		return errors.size() != 0;
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

	public Void visitAssign(AssignContext ctx) {
		String target = ctx.derefID().getText();
		TerminalNode id = getID(ctx.derefID());
		IType targetType = getType(ctx, target.replaceAll("\\*", ""));
		if (targetType == Type.ERR_TYPE)
			return null;
		visit(ctx.expr());
		while (target.startsWith("*")) {
			targetType = new Pointer(targetType);
			target = target.substring(1);
		}
		IType sourceType = getType(ctx.expr());
		checkType(ctx, targetType, sourceType);
		shared.put(id, scope.isShared(target.replaceAll("\\*", "")));
		result.getOffsets().put(id, scope.getOffset(target.replaceAll("\\*", "")));
		return null;
	}

	public Void visitBlock(BlockContext ctx) {
		scope.openScope();
		ctx.stat().forEach(s -> visit(s));
		scope.closeScope();
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

	public Void visitCallExpr(CallExprContext ctx) {
		Func function = call(ctx.call(), null);
		types.put(ctx, function != null ? function.getReturnType()
				: Type.ERR_TYPE);
		return null;
	}

	public Void visitCallStat(CallStatContext ctx) {
		call(ctx.call(), null);
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

	public Void visitDecl(DeclContext ctx) {
		String varId = ctx.ID().getText();
		if (!checkName(ctx, varId))
			return null;

		IType type = typeForName(ctx, ctx.type().getText());
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
	
	public Void visitDerefExpr(DerefExprContext ctx) {
		visit(ctx.expr());
		IType ptr = types.get(ctx.expr());
		if (!(ptr instanceof Pointer)) {
			error(ctx, "Tried dereferencing %s (type %s), which is not a pointer.", ctx.expr().getText(), ptr);
			types.put(ctx, Type.ERR_TYPE);
			return null;
		}
		types.put(ctx, ((Pointer) ptr).getWrappedType());
		return null;
	}

	public Void visitDivExpr(DivExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	public Void visitFalseExpr(FalseExprContext ctx) {
		types.put(ctx, Type.BOOL);
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

	public Void visitFunc(FuncContext ctx) {
		IType retType = IType.forName(ctx.type().getText());
		types.put(ctx, retType);

		String name = ctx.ID().getText();
		if (!checkName(ctx, name))
			return null;

		List<IType> argTypes = ctx.typedparams().type().stream()
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

	public Void visitIdExpr(IdExprContext ctx) {
		types.put(ctx, getType(ctx, ctx.ID().getText()));
		shared.put(ctx.ID(), scope.isShared(ctx.ID().getText()));
		result.getOffsets().put(ctx.ID(), scope.getOffset(ctx.ID().getText()));
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

	public Void visitInStat(InStatContext ctx) {
		visit(ctx.ID());
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

	public Void visitMinExpr(MinExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	public Void visitModExpr(ModExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	public Void visitMultExpr(MultExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
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

	public Void visitNegNumExpr(NegNumExprContext ctx) {
		visit(ctx.expr());
		if (checkType(ctx, Type.INT, ctx.expr()))
			types.put(ctx, Type.INT);
		else
			types.put(ctx, Type.ERR_TYPE);
		return null;
	}

	public Void visitNumExpr(NumExprContext ctx) {
		types.put(ctx, Type.INT);
		return null;
	}

	public Void visitOutStat(OutStatContext ctx) {
		visit(ctx.expr());
		return null;
	}

	public Void visitParExpr(ParExprContext ctx) {
		visit(ctx.expr());
		types.put(ctx, getType(ctx.expr()));
		return null;
	}

	public Void visitPlusExpr(PlusExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
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
	
	public Void visitRefExpr(RefExprContext ctx) {
		visit(ctx.expr());
		types.put(ctx, new Pointer(types.get(ctx.expr())));
		return null;
	}

	public Void visitReturnStat(ReturnStatContext ctx) {
		IType expected = currentFunc.getReturnType();
		if (ctx.expr() != null) {
			visit(ctx.expr());
			IType exprType = getType(ctx.expr());
			if (exprType == Type.ERR_TYPE)
				error(ctx,
						"<Unable to check function return type because the expression cannot be evaluated>");
			else if (!exprType.equals(expected)) {
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

	public Void visitTopLevelBlock(TopLevelBlockContext ctx) {
		scope.openScope();
		visit(params.get(currentFunc));
		ctx.block().stat().forEach(s -> visit(s));
		scope.closeScope();
		return null;
	}

	public Void visitTrueExpr(TrueExprContext ctx) {
		types.put(ctx, Type.BOOL);
		return null;
	}

	public Void visitTypedparams(TypedparamsContext ctx) {
		for (int i = 0; i < ctx.type().size(); i++) {
			String name = ctx.ID(i).getText();
			IType type = typeForName(ctx, ctx.type(i).getText());
			if (scope.isDeclaredLocally(name)) {
				error(ctx, "Duplicate parameter '%s'.", name);
			} else {
				scope.declare(name, type);
				result.getOffsets().put(ctx.ID(i), scope.getOffset(name));
			}
		}
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

	public Void visitWhileStat(WhileStatContext ctx) {
		visit(ctx.expr());
		checkType(ctx, Type.BOOL, getType(ctx.expr()));
		visit(ctx.block());
		return null;
	}

	private void arithmeticExpr(ExprContext ctx, ExprContext fst,
			ExprContext snd) {
		visit(fst);
		visit(snd);
		checkType(ctx, Type.INT, fst);
		checkType(ctx, Type.INT, snd);
		types.put(ctx, Type.INT);
	}

	private Func call(CallContext ctx, IType expectedReturn) {
		List<IType> args = new ArrayList<>();
		ctx.params().val().stream().forEachOrdered(val -> {
			visit(val);
			args.add(getType(val));
		});
		if (args.stream().anyMatch(type -> type == Type.ERR_TYPE))
			return null;
		Func func = null;
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
			/*
			 * for (IType type : IType.values()) { func = new
			 * Func(ctx.ID().getText(), type, args); if (scope.isDeclared(func))
			 * { if (res != null) { error(ctx,
			 * "Ambiguous function call: both '%s' and '%s' match.", func, res);
			 * return null; } res = func; } }
			 */
			List<Func> matches = scope
					.getFunctions()
					.stream()
					.filter(f -> f.getName().equals(ctx.ID().getText())
							&& f.getArgs().equals(args))
					.collect(Collectors.toList());

			if (matches.isEmpty()) {
				error(ctx, "Function '%s' with types '%s' does not exist.", ctx
						.ID().getText(), args.toString());
				return null;
			} else if (matches.size() > 1) {
				error(ctx,
						"Ambigous function call %s%s, all of the following definitions match: %s",
						ctx.ID().getText(), args, matches);
				return null;
			} else {
				res = matches.get(0);
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

	private boolean checkName(ParseTree tree, String name) {
		if (INVALID_NAMES.stream().anyMatch(inv -> name.equalsIgnoreCase(inv))) {
			error(tree, "Invalid name: '%s' is a reserved word.", name);
			return false;
		}
		return true;
	}

	private boolean checkType(ParseTree tree, IType expected, IType actual) {
		if (!expected.equals(actual)
				&& !(expected instanceof Pointer && actual == Type.INT)
				&& !(expected == Type.INT && actual instanceof Pointer)) {
			if (actual == Type.ERR_TYPE)
				error(tree, "<Caused by earlier error>");
			else
				error(tree, "Type mismatch. Expected '%s', but saw '%s'.",
						expected.toString(), actual.toString());
			return false;
		}
		return true;
	}

	private boolean checkType(ParseTree tree, IType expected,
			ParserRuleContext ctx) {
		return checkType(tree, expected, getType(ctx));
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

	private TerminalNode getID(DerefIDContext ctx) {
		if (ctx.ID() != null)
			return ctx.ID();
		else
			return getID(ctx.derefID());
	}
	
	private IType getType(ParserRuleContext ctx) {
		IType type = types.get(ctx);
		if (type == null)
			throw new IllegalArgumentException(String.format(
					"Node '%s' has no type.", ctx.getText()));
		return type;
	}

	private IType getType(ParseTree tree, String varId) {
		if (scope.isDeclared(varId))
			return scope.getType(varId);
		error(tree, "Variable '%s' was not declared in this scope.", varId);
		return Type.ERR_TYPE;
	}

	private void processFunction(FuncContext ctx, Func func) {
		currentFunc = func;
		visit(ctx.topLevelBlock());
		currentFunc = null;
	}

	private IType typeForName(ParseTree tree, String typeName) {
		try {
			return IType.forName(typeName);
		} catch (IllegalArgumentException e) {
			error(tree, e.getMessage());
		}
		return Type.ERR_TYPE;
	}
}