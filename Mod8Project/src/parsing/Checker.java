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
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class Checker extends BaseGrammarBaseVisitor<Void> implements
		ANTLRErrorListener {

	/**
	 * Class CheckResult ?.
	 * 
	 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
	 */
	public class CheckResult {

		// Instance variables
		private ParseTreeProperty<IType> types;
		private Map<ParseTree, Integer> offsets;
		private Set<FuncContext> functions;
		private List<String> errors;
		private ParseTreeProperty<Boolean> shared;
		private Map<String, Integer> locks;

		/**
		 * Constructor CheckResult sets certain instance variables.
		 */
		public CheckResult() {
			this.types = new ParseTreeProperty<>();
			this.offsets = new HashMap<>();
			this.functions = new HashSet<>();
			this.errors = new ArrayList<>();
			this.shared = new ParseTreeProperty<>();
			this.locks = new HashMap<>();
		}

		/**
		 * Constructor CheckResult sets certain instance variables.
		 * 
		 * @param types
		 *            The value that needs to be put in instance variable
		 *            <code>types</code>.
		 * @param offsets
		 *            The value that needs to be put in instance variable
		 *            <code>offsets</code>.
		 * @param functions
		 *            The value that needs to be put in instance variable
		 *            <code>functions</code>.
		 * @param errors
		 *            The value that needs to be put in instance variable
		 *            <code>errors</code>.
		 * @param shared
		 *            The value that needs to be put in instance variable
		 *            <code>shared</code>.
		 * @param locks
		 *            The value that needs to be put in instance variable
		 *            <code>locks</code>.
		 */
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

		/**
		 * Getter for the List with errors.
		 * 
		 * @return A List with errors.
		 */
		public List<String> getErrors() {
			return errors;
		}

		/**
		 * Getter for the Set with functions.
		 * 
		 * @return A Set with functions.
		 */
		public Set<FuncContext> getFunctions() {
			return functions;
		}

		/**
		 * Getter for the Map with locks.
		 * 
		 * @return A Map with locks.
		 */
		public Map<String, Integer> getLocks() {
			return locks;
		}

		/**
		 * Getter for the Map with errors.
		 * 
		 * @return A Map with offsets.
		 */
		public Map<ParseTree, Integer> getOffsets() {
			return offsets;
		}

		/**
		 * Getter for the ParseTreeProperty with booleans.
		 * 
		 * @return A ParseTreeProperty with booleans.
		 */
		public ParseTreeProperty<Boolean> getShared() {
			return shared;
		}

		/**
		 * Getter for the ParseTreeProperty with ITypes.
		 * 
		 * @return A ParseTreeProperty with ITypes.
		 */
		public ParseTreeProperty<IType> getTypes() {
			return types;
		}

		/**
		 * Setter for the List with errors.
		 * 
		 * @param errors
		 *            The List that needs to become errors.
		 */
		public void setErrors(List<String> errors) {
			this.errors = errors;
		}

		/**
		 * Setter for the Set with functions.
		 * 
		 * @param errors
		 *            The Set that needs to become functions.
		 */
		public void setFunctions(Set<FuncContext> functions) {
			this.functions = functions;
		}

		/**
		 * Setter for the Map with locks.
		 * 
		 * @param errors
		 *            The Map that needs to become locks.
		 */
		public void setLocks(Map<String, Integer> locks) {
			this.locks = locks;
		}

		/**
		 * Setter for the Map with offsets.
		 * 
		 * @param errors
		 *            The Map that needs to become offsets.
		 */
		public void setOffsets(Map<ParseTree, Integer> offsets) {
			this.offsets = offsets;
		}

		/**
		 * Setter for the ParseTreeProperty with booleans.
		 * 
		 * @param errors
		 *            The ParseTreeProperty that needs to become booleans.
		 */
		public void setShared(ParseTreeProperty<Boolean> shared) {
			this.shared = shared;
		}

		/**
		 * Setter for the ParseTreeProperty with types.
		 * 
		 * @param errors
		 *            The ParseTreeProperty that needs to become types.
		 */
		public void setTypes(ParseTreeProperty<IType> types) {
			this.types = types;
		}

		@Override
		public String toString() {
			return "CheckResult [types=" + types + ", offsets=" + offsets
					+ ", funcAddrs=" + functions + ", errors=" + errors + "]";
		}

		/**
		 * Adds locks to the instance variable containing locks.
		 * 
		 * @param locks
		 *            The Set with locks that need to be added to the instance
		 *            variable containing locks.
		 */
		void buildLocks(Set<String> locks) {
			int addr = sharedStaticMemSize() + 1;
			for (String lock : locks)
				this.locks.put(lock, addr++);
		}

		/**
		 * Returns the amount of functions that are in the corresponding
		 * instance variable.
		 * 
		 * @return The amount of functions.
		 */
		int funcCount() {
			return this.functions.size();
		}

		/**
		 * Returns ?
		 * 
		 * @param name
		 * @param types
		 * @return
		 */
		FuncContext getMatchingFunc(String name, List<IType> types) {
			return this.functions
					.stream()
					.filter(func -> func.typedparams().type().stream()
							.map(t -> typeForName(func, t.getText()))
							.collect(Collectors.toList()).equals(types)
							&& func.ID().getText().equals(name)).findAny()
					.orElse(null);
		}

		/**
		 * ?
		 * 
		 * @return
		 */
		int localStaticMemSize() {
			return maxOffset(false) + funcCount();
		}

		/**
		 * Returns the maximal offset ?
		 * 
		 * @param shared
		 *            Whether the variable is global or not.
		 * @return
		 */
		int maxOffset(boolean shared) {
			return this.offsets
					.entrySet()
					.stream()
					.filter(e -> (this.shared == null) != shared
							|| (this.shared.get(e.getKey()) != null && this.shared
									.get(e.getKey())))
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

	/**
	 * Instance variables.
	 */
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
	 * Check returns a checked program.
	 * 
	 * @param stream
	 *            The stream that needs to be processed.
	 * @return CheckedResult.
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
	 * Check returns a check program.
	 * 
	 * @param prog
	 *            The program that needs to be processed.
	 * @return CheckedResult.
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
	 * The list with errors that occurred while processing the program.
	 * 
	 * @return List<String> errors.
	 */
	public List<String> getErrors() {
		return new ArrayList<>(errors);
	}

	/**
	 * Returns whether any errors occurred while processing a program.
	 * 
	 * @return Whether an error has occurred.
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

	/**
	 * Called when an assign statement is recognized.
	 */
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
		result.getOffsets().put(id,
				scope.getOffset(target.replaceAll("\\*", "")));
		return null;
	}

	/**
	 * Called when a block is recognized.
	 */
	public Void visitBlock(BlockContext ctx) {
		scope.openScope();
		ctx.stat().forEach(s -> visit(s));
		scope.closeScope();
		return null;
	}

	/**
	 * Called when a boolean operator expression is recognized.
	 */
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

	/**
	 * Called when a call expression is recognized.
	 */
	public Void visitCallExpr(CallExprContext ctx) {
		Func function = call(ctx.call(), null);
		types.put(ctx, function != null ? function.getReturnType()
				: Type.ERR_TYPE);
		return null;
	}

	/**
	 * Called when a call statement is recognized.
	 */
	public Void visitCallStat(CallStatContext ctx) {
		call(ctx.call(), null);
		return null;
	}

	/**
	 * Called when a comparator expression is recognized.
	 */
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

	/**
	 * Called when a declaration is recognized.
	 */
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

	/**
	 * Called when a times expression is recognized.
	 */
	public Void visitDerefExpr(DerefExprContext ctx) {
		visit(ctx.expr());
		IType ptr = types.get(ctx.expr());
		if (!(ptr instanceof Pointer)) {
			error(ctx,
					"Tried dereferencing %s (type %s), which is not a pointer.",
					ctx.expr().getText(), ptr);
			types.put(ctx, Type.ERR_TYPE);
			return null;
		}
		types.put(ctx, ((Pointer) ptr).getWrappedType());
		return null;
	}

	/**
	 * Called when a division expression is recognized.
	 */
	public Void visitDivExpr(DivExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Called when a false expression is recognized.
	 */
	public Void visitFalseExpr(FalseExprContext ctx) {
		types.put(ctx, Type.BOOL);
		return null;
	}

	/**
	 * Called when a for statement is recognized.
	 */
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

	/**
	 * Called when a function is recognized.
	 */
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

	/**
	 * Called when an id expression is recognized.
	 */
	public Void visitIdExpr(IdExprContext ctx) {
		types.put(ctx, getType(ctx, ctx.ID().getText()));
		shared.put(ctx.ID(), scope.isShared(ctx.ID().getText()));
		result.getOffsets().put(ctx.ID(), scope.getOffset(ctx.ID().getText()));
		return null;
	}

	/**
	 * Called when an if statement is recognized.
	 */
	public Void visitIfStat(IfStatContext ctx) {
		for (int i = 0; i < ctx.expr().size(); i++) {
			visit(ctx.expr(i));
			checkType(ctx, Type.BOOL, getType(ctx.expr(i)));
		}
		ctx.block().forEach(block -> visit(block));
		return null;
	}

	/**
	 * Called when an in statement is recognized.
	 */
	public Void visitInStat(InStatContext ctx) {
		visit(ctx.ID());
		return null;
	}

	/**
	 * Called when a lock statement is recognized.
	 */
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

	/**
	 * Called when a minus expression is recognized.
	 */
	public Void visitMinExpr(MinExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Called when a mod expression is recognized.
	 */
	public Void visitModExpr(ModExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Called when a multiplication expression is recognized.
	 */
	public Void visitMultExpr(MultExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Called when a negate boolean expression is recognized.
	 */
	public Void visitNegBoolExpr(NegBoolExprContext ctx) {
		visit(ctx.expr());
		if (checkType(ctx, Type.BOOL, ctx.expr()))
			types.put(ctx, Type.BOOL);
		else
			types.put(ctx, Type.ERR_TYPE);
		return null;
	}

	/**
	 * Called when a negate number expression is recognized.
	 */
	public Void visitNegNumExpr(NegNumExprContext ctx) {
		visit(ctx.expr());
		if (checkType(ctx, Type.INT, ctx.expr()))
			types.put(ctx, Type.INT);
		else
			types.put(ctx, Type.ERR_TYPE);
		return null;
	}

	/**
	 * Called when a number expression is recognized.
	 */
	public Void visitNumExpr(NumExprContext ctx) {
		types.put(ctx, Type.INT);
		return null;
	}

	/**
	 * Called when a out statement is recognized.
	 */
	public Void visitOutStat(OutStatContext ctx) {
		visit(ctx.expr());
		return null;
	}

	/**
	 * Called when a par expression is recognized.
	 */
	public Void visitParExpr(ParExprContext ctx) {
		visit(ctx.expr());
		types.put(ctx, getType(ctx.expr()));
		return null;
	}

	/**
	 * Called when a plus expression is recognized.
	 */
	public Void visitPlusExpr(PlusExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Called when a program expression is recognized.
	 */
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

	/**
	 * Called when a ?
	 */
	public Void visitRefExpr(RefExprContext ctx) {
		visit(ctx.expr());
		types.put(ctx, new Pointer(types.get(ctx.expr())));
		return null;
	}

	/**
	 * Called when a return statement is recognized.
	 */
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

	/**
	 * Called when a top level block is recognized.
	 */
	public Void visitTopLevelBlock(TopLevelBlockContext ctx) {
		scope.openScope();
		visit(params.get(currentFunc));
		ctx.block().stat().forEach(s -> visit(s));
		scope.closeScope();
		return null;
	}

	/**
	 * Called when a true expression is recognized.
	 */
	public Void visitTrueExpr(TrueExprContext ctx) {
		types.put(ctx, Type.BOOL);
		return null;
	}

	/**
	 * Called when a typed parameter is recognized.
	 */
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

	/**
	 * Called when a value is recognized.
	 */
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

	/**
	 * Called when a while statement is recognized.
	 */
	public Void visitWhileStat(WhileStatContext ctx) {
		visit(ctx.expr());
		checkType(ctx, Type.BOOL, getType(ctx.expr()));
		visit(ctx.block());
		return null;
	}

	/**
	 * Arithmetic expression.
	 * 
	 * @param ctx
	 *            The complete expression.
	 * @param fst
	 *            The first expression.
	 * @param snd
	 *            The second expression.
	 */
	private void arithmeticExpr(ExprContext ctx, ExprContext fst,
			ExprContext snd) {
		visit(fst);
		visit(snd);
		checkType(ctx, Type.INT, fst);
		checkType(ctx, Type.INT, snd);
		types.put(ctx, Type.INT);
	}

	/**
	 * ?
	 * 
	 * @param ctx
	 * @param expectedReturn
	 * @return
	 */
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

	/**
	 * Checks whether a name is valid or not.
	 * 
	 * @param tree
	 *            The ParseTree on which this function is called.
	 * @param name
	 *            The name that needs to be checked.
	 * @return Whether a name is valid or not.
	 */
	private boolean checkName(ParseTree tree, String name) {
		if (INVALID_NAMES.stream().anyMatch(inv -> name.equalsIgnoreCase(inv))) {
			error(tree, "Invalid name: '%s' is a reserved word.", name);
			return false;
		}
		return true;
	}

	/**
	 * Checks the Type of a parameter.
	 * 
	 * @param tree
	 *            The ParseTree on which this tree is called.
	 * @param expected
	 *            The expected type of a variable.
	 * @param actual
	 *            The actual type of a variable.
	 * @return Whether a type of a variable is of a certain type or not.
	 */
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

	/**
	 * Checks the type of a variable.
	 * 
	 * @param tree
	 *            The ParseTree on which this function is called.
	 * @param expected
	 *            The expected type of a parameter.
	 * @param ctx
	 *            The ParserRuleContext on which this function is called.
	 * @return Whether a variable is of a certain type or not.
	 */
	private boolean checkType(ParseTree tree, IType expected,
			ParserRuleContext ctx) {
		return checkType(tree, expected, getType(ctx));
	}

	/**
	 * ?
	 * 
	 * @param tree
	 * @param format
	 * @param args
	 */
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

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	private TerminalNode getID(DerefIDContext ctx) {
		if (ctx.ID() != null)
			return ctx.ID();
		else
			return getID(ctx.derefID());
	}

	/**
	 * Returns the type of a ParserRuleContext.
	 * 
	 * @param ctx
	 *            The ParserRuleContext on which this function is called.
	 * @return The type of a ParserRuleContext.
	 */
	private IType getType(ParserRuleContext ctx) {
		IType type = types.get(ctx);
		if (type == null)
			throw new IllegalArgumentException(String.format(
					"Node '%s' has no type.", ctx.getText()));
		return type;
	}

	/**
	 * Returns the type of a certain id in a ParseTree.
	 * 
	 * @param tree
	 *            The tree of which this function is called.
	 * @param varId
	 *            The name of the variable on which this function is called.
	 * @return The type of a certain variable.
	 */
	private IType getType(ParseTree tree, String varId) {
		if (scope.isDeclared(varId))
			return scope.getType(varId);
		error(tree, "Variable '%s' was not declared in this scope.", varId);
		return Type.ERR_TYPE;
	}

	/**
	 * ?
	 * @param ctx
	 * @param func
	 */
	private void processFunction(FuncContext ctx, Func func) {
		currentFunc = func;
		visit(ctx.topLevelBlock());
		currentFunc = null;
	}

	/**
	 * ?
	 * @param tree
	 * @param typeName
	 * @return
	 */
	private IType typeForName(ParseTree tree, String typeName) {
		try {
			return IType.forName(typeName);
		} catch (IllegalArgumentException e) {
			error(tree, e.getMessage());
		}
		return Type.ERR_TYPE;
	}
}