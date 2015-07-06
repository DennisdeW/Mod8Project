package parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

import parsing.BaseGrammarParser.ArrayLiteralExprContext;
import parsing.BaseGrammarParser.ArrayValContext;
import parsing.BaseGrammarParser.AssignContext;
import parsing.BaseGrammarParser.BlockContext;
import parsing.BaseGrammarParser.BoolOpExprContext;
import parsing.BaseGrammarParser.CallContext;
import parsing.BaseGrammarParser.CallExprContext;
import parsing.BaseGrammarParser.CallStatContext;
import parsing.BaseGrammarParser.CompExprContext;
import parsing.BaseGrammarParser.ConstArrayExprContext;
import parsing.BaseGrammarParser.DeclContext;
import parsing.BaseGrammarParser.DerefExprContext;
import parsing.BaseGrammarParser.DerefIDContext;
import parsing.BaseGrammarParser.DivExprContext;
import parsing.BaseGrammarParser.EnumDeclContext;
import parsing.BaseGrammarParser.EnumExprContext;
import parsing.BaseGrammarParser.ExprArrayExprContext;
import parsing.BaseGrammarParser.ExprContext;
import parsing.BaseGrammarParser.FalseExprContext;
import parsing.BaseGrammarParser.ForStatContext;
import parsing.BaseGrammarParser.FuncContext;
import parsing.BaseGrammarParser.IdExprContext;
import parsing.BaseGrammarParser.IfStatContext;
import parsing.BaseGrammarParser.InStatContext;
import parsing.BaseGrammarParser.LockStatContext;
import parsing.BaseGrammarParser.MinExprContext;
import parsing.BaseGrammarParser.ModExprContext;
import parsing.BaseGrammarParser.MultExprContext;
import parsing.BaseGrammarParser.NegBoolExprContext;
import parsing.BaseGrammarParser.NegNumExprContext;
import parsing.BaseGrammarParser.NumExprContext;
import parsing.BaseGrammarParser.OutStatContext;
import parsing.BaseGrammarParser.ParExprContext;
import parsing.BaseGrammarParser.PlusExprContext;
import parsing.BaseGrammarParser.ProgramContext;
import parsing.BaseGrammarParser.RefExprContext;
import parsing.BaseGrammarParser.ReturnStatContext;
import parsing.BaseGrammarParser.SpidExprContext;
import parsing.BaseGrammarParser.TopLevelBlockContext;
import parsing.BaseGrammarParser.TrueExprContext;
import parsing.BaseGrammarParser.TypedparamsContext;
import parsing.BaseGrammarParser.WhileStatContext;
import parsing.Type.Array;
import parsing.Type.Enum;
import parsing.Type.Pointer;
import translation.Operator;

/**
 * Class Checker check whether the syntax of a program is correct according to
 * our programming language or not.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class Checker extends BaseGrammarBaseVisitor<Void>implements ANTLRErrorListener {

	/**
	 * Inner class CheckResult saves information of the Checker so that
	 * Generator can use it.
	 *
	 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
	 */
	public class CheckResult {

		// Instance variables
		private ParseTreeProperty<Type> types;
		private Map<ParseTree, Integer> offsets;
		private Set<FuncContext> functions;
		private List<String> errors;
		private ParseTreeProperty<Boolean> shared;
		private Map<String, Integer> locks;
		private Set<Enum> enums;

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
			this.enums = new HashSet<>();
		}

		/**
		 * Constructor CheckResult sets certain instance variables.
		 * 
		 * @param types
		 *            The new value of types.
		 * @param offsets
		 *            The new value of offsets.
		 * @param functions
		 *            The new value of functions.
		 * @param errors
		 *            The new value of errors.
		 * @param shared
		 *            The new value of shared.
		 * @param locks
		 *            The new value of locks.
		 * @param enums
		 *            The new value of enums.
		 */
		public CheckResult(ParseTreeProperty<Type> types, Map<ParseTree, Integer> offsets, Set<FuncContext> functions,
				List<String> errors, ParseTreeProperty<Boolean> shared, Map<String, Integer> locks, Set<Enum> enums) {
			super();
			this.types = types;
			this.offsets = offsets;
			this.functions = functions;
			this.errors = errors;
			this.shared = shared;
			this.locks = locks;
			this.enums = enums;
		}

		/**
		 * Getter for errors.
		 * 
		 * @return The value of errors.
		 */
		public List<String> getErrors() {
			return errors;
		}

		/**
		 * Getter for functions.
		 * 
		 * @return The value of functions.
		 */
		public Set<FuncContext> getFunctions() {
			return functions;
		}

		/**
		 * Getter for locks.
		 * 
		 * @return The value of locks.
		 */
		public Map<String, Integer> getLocks() {
			return locks;
		}

		/**
		 * Getter for offsets.
		 * 
		 * @return The value of offsets.
		 */
		public Map<ParseTree, Integer> getOffsets() {
			return offsets;
		}

		/**
		 * Getter for shared.
		 * 
		 * @return The value of shared.
		 */
		public ParseTreeProperty<Boolean> getShared() {
			return shared;
		}

		/**
		 * Getter for types.
		 * 
		 * @return The value of types.
		 */
		public ParseTreeProperty<Type> getTypes() {
			return types;
		}

		/**
		 * Setter for errors.
		 * 
		 * @param errors
		 *            The new value of errors.
		 */
		public void setErrors(List<String> errors) {
			this.errors = errors;
		}

		/**
		 * Setter for functions.
		 * 
		 * @param functions
		 *            The new value of functions.
		 */
		public void setFunctions(Set<FuncContext> functions) {
			this.functions = functions;
		}

		/**
		 * Setter for locks.
		 * 
		 * @param locks
		 *            The new value of locks.
		 */
		public void setLocks(Map<String, Integer> locks) {
			this.locks = locks;
		}

		/**
		 * Setter for offsets.
		 * 
		 * @param offsets
		 *            The new value of offsets.
		 */
		public void setOffsets(Map<ParseTree, Integer> offsets) {
			this.offsets = offsets;
		}

		/**
		 * Setter for shared.
		 * 
		 * @param shared
		 *            The new value of shared.
		 */
		public void setShared(ParseTreeProperty<Boolean> shared) {
			this.shared = shared;
		}

		/**
		 * Setter for types.
		 * 
		 * @param types
		 *            The new value of types.
		 */
		public void setTypes(ParseTreeProperty<Type> types) {
			this.types = types;
		}

		@Override
		public String toString() {
			return "CheckResult [types=" + types + ", offsets=" + offsets + ", funcAddrs=" + functions + ", errors="
					+ errors + "]";
		}

		/**
		 * buildLocks adds locks to the list with all the locks.
		 * 
		 * @param locks
		 *            The locks that need to be added to the list with locks.
		 */
		void buildLocks(Set<String> locks) {
			int addr = sharedStaticMemSize() + 1;
			this.locks.put("<$INIT_LOCK$>", addr++);
			for (String lock : locks)
				this.locks.putIfAbsent(lock, addr++);
		}

		/**
		 * Counts the amount of functions.
		 * 
		 * @return The amount of functions.
		 */
		int funcCount() {
			return this.functions.size();
		}

		/**
		 * Returns the function definition of a certain name and arguments.
		 * 
		 * @param name
		 *            The name of the function that needs to be found.
		 * @param types
		 *            The arguments of the function.
		 * @return The function definition.
		 */
		FuncContext getMatchingFunc(String name, List<Type> types) {
			return this.functions.stream()
					.filter(func -> func.typedparams().type().stream().map(t -> typeForName(func, t.getText()))
							.collect(Collectors.toList()).equals(types) && func.ID().getText().equals(name))
					.findAny().orElse(null);
		}

		/**
		 * Returns the size of the local memory.
		 * 
		 * @return The size of the local memory.
		 */
		int localStaticMemSize() {
			return maxOffset(false) + funcCount();
		}

		/**
		 * Returns the highest address which is in use.
		 * 
		 * @param shared
		 *            Whether a boolean is shared or not.
		 * @return The maximum offset.
		 */
		int maxOffset(boolean shared) {
			return this.offsets.entrySet().stream()
					.filter(e -> !(this.shared == null && shared)
							&& (this.shared.get(e.getKey()) != null && this.shared.get(e.getKey())))
					.mapToInt(i -> i.getValue()).max().orElse(0);
		}

		/**
		 * Returns the static memory size.
		 * 
		 * @return The size of the static memory.
		 */
		int sharedStaticMemSize() {
			return maxOffset(true) + locks.size();
		}

		/**
		 * Getter for enums.
		 * 
		 * @return The value of enums.
		 */
		public Set<Enum> getEnums() {
			return enums;
		}

		/**
		 * Setter for enums.
		 * 
		 * @param enums
		 *            The new value of setter.
		 */
		public void setEnums(Set<Enum> enums) {
			this.enums = enums;
		}
	}

	// Instance variables
	private static final List<String> INVALID_NAMES = Arrays.asList("int", "bool", "void", "if", "else", "while", "for",
			"return", "and", "or", "xor", "true", "false", "def", "break", "not", "string");
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
	private int arrCount;

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
		BaseGrammarParser parser = new BaseGrammarParser(new CommonTokenStream(lexer));
		parser.addErrorListener(this);
		ProgramContext prog = parser.program();
		if (dirty) {
			throw new RuntimeException("ANTLR Reported errors, see console.");
		}
		return check(prog);
	}

	/**
	 * Check returns a check program. This method assumes the standard library
	 * has been added already.
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
		arrCount = 0;
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
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, boolean arg4, BitSet arg5,
			ATNConfigSet arg6) {

	}

	/**
	 * Returns the value of dirty.
	 * 
	 * @return The value of dirty.
	 */
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4, ATNConfigSet arg5) {

	}

	@Override
	public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, int arg4, ATNConfigSet arg5) {

	}

	@Override
	public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4,
			RecognitionException arg5) {
		dirty = true;
	}

	/**
	 * Handles an array value.
	 */
	public Void visitArrayVal(ArrayValContext ctx) {
		String arrId = ctx.ID().getText();
		Type arrType = getType(ctx, arrId);
		if (!(arrType instanceof Array)) {
			error(ctx, "Variable %s is not an array (it's %s), but it is accessed like one.", arrId, arrType);
			return null;
		}

		visit(ctx.expr());
		checkType(ctx, Primitive.INT, ctx.expr());

		types.put(ctx, ((Array) arrType).getContainedType());
		result.getOffsets().put(ctx, scope.getOffset(arrId));
		shared.put(ctx, scope.isShared(arrId));

		return null;
	}

	/**
	 * Handles an assign statement.
	 */
	public Void visitAssign(AssignContext ctx) {
		if (ctx.derefID() != null) {
			String target = ctx.derefID().getText();
			TerminalNode id = getID(ctx.derefID());
			Type targetType = getType(ctx, target.replaceAll("\\*", ""));
			if (targetType == Primitive.ERR_TYPE)
				return null;
			visit(ctx.expr());
			while (target.startsWith("*")) {
				targetType = ((Pointer) targetType).getWrappedType();
				target = target.substring(1);
			}
			Type sourceType = getType(ctx.expr());
			if (!(targetType instanceof Pointer && sourceType == Primitive.INT)
					&& !(targetType == Primitive.INT && sourceType instanceof Pointer))
				checkType(ctx, targetType, sourceType);
			shared.put(id, scope.isShared(target.replaceAll("\\*", "")));
			result.getOffsets().put(id, scope.getOffset(target.replaceAll("\\*", "")));
		} else if (ctx.arrayVal() != null) {
			visit(ctx.arrayVal());
			String target = ctx.arrayVal().ID().getText();
			TerminalNode id = ctx.arrayVal().ID();
			Type targetType = ((Array) getType(ctx, target)).getContainedType();
			visit(ctx.expr());
			Type sourceType = Type.baseType(getType(ctx.expr()));
			checkType(ctx, targetType, sourceType);
			types.put(id, targetType);
			shared.put(id, scope.isShared(target));
			result.getOffsets().put(id, scope.getOffset(target));
		} else {
			visit(ctx.arrayVal());
			Array arr = (Array) types.get(ctx.arrayVal());
			Type sourceType = getType(ctx.expr());
			Type targetType = arr.getContainedType();
			if (!(targetType instanceof Pointer && sourceType == Primitive.INT)
					&& !(targetType == Primitive.INT && sourceType instanceof Pointer))
				checkType(ctx, targetType, sourceType);
			shared.put(ctx, shared.get(ctx.arrayVal()));
		}
		return null;
	}

	/**
	 * Handles a block in a program.
	 */
	public Void visitBlock(BlockContext ctx) {
		scope.openScope();
		ctx.stat().forEach(s -> visit(s));
		scope.closeScope();
		return null;
	}

	/**
	 * Handles a boolean operator expression.
	 */
	public Void visitBoolOpExpr(BoolOpExprContext ctx) {
		ExprContext fst = ctx.expr(0);
		ExprContext snd = ctx.expr(1);
		visit(fst);
		visit(snd);
		checkType(ctx, Primitive.BOOL, fst);
		checkType(ctx, Primitive.BOOL, snd);
		types.put(ctx, Primitive.BOOL);
		return null;
	}

	/**
	 * Handles a call expression.
	 */
	public Void visitCallExpr(CallExprContext ctx) {
		Func function = call(ctx.call(), null);
		types.put(ctx, function != null ? function.getReturnType() : Primitive.ERR_TYPE);
		return null;
	}

	/**
	 * Handles a call statement.
	 */
	public Void visitCallStat(CallStatContext ctx) {
		call(ctx.call(), null);
		return null;
	}

	/**
	 * Handles a compare expression.
	 */
	public Void visitCompExpr(CompExprContext ctx) {
		ExprContext fst = ctx.expr(0);
		ExprContext snd = ctx.expr(1);
		visit(fst);
		visit(snd);
		Operator comp = Operator.comparator(ctx.comp().getText());
		if (!(comp == Operator.EQ || comp == Operator.NEQ)) {
			checkType(ctx, Primitive.INT, fst);
			checkType(ctx, Primitive.INT, snd);
		}
		types.put(ctx, Primitive.BOOL);
		return null;
	}

	/**
	 * Handles an array expression with a constant value as index.
	 */
	public Void visitConstArrayExpr(ConstArrayExprContext ctx) {
		visit(ctx.ID());
		types.put(ctx, getType(ctx, ctx.ID().getText()));
		result.getOffsets().put(ctx, scope.getOffset(ctx.ID().getText()));
		shared.put(ctx, scope.isShared(ctx.ID().getText()));
		return null;
	}

	/**
	 * Handles a declaration.
	 */
	public Void visitDecl(DeclContext ctx) {
		String varId = ctx.ID().getText().replaceAll("[\\[\\]\\*]", "");
		if (!checkName(ctx, varId))
			return null;

		Type type = typeForName(ctx, ctx.type(0).getText());
		if (scope.isDeclaredLocally(varId)) {
			error(ctx, "Duplicate declaration of variable '%s'", varId);
			return null;
		}

		if (ctx.expr() instanceof ArrayLiteralExprContext) {
			makeArray((ArrayLiteralExprContext) ctx.expr(), varId, ctx.SHARED() != null);
			types.put(ctx, types.get(ctx.expr()));
			result.getOffsets().put(ctx.ID(), scope.getOffset(varId));
		} else if (ctx.type().size() > 1) {
			scope.declare(varId, type, ctx.SHARED() != null);
			types.put(ctx, type);
			result.getOffsets().put(ctx.ID(), scope.getOffset(varId));
		} else {
			visit(ctx.expr());
			scope.declare(varId, type, ctx.SHARED() != null);
			types.put(ctx, type);
			result.getOffsets().put(ctx.ID(), scope.getOffset(varId));
		}

		if (ctx.type().size() == 1)
			checkType(ctx, type, ctx.expr());
		else {
			checkType(ctx, new Array(Type.forName(ctx.type(1).getText())), type);
		}
		shared.put(ctx.ID(), ctx.SHARED() != null);

		return null;
	}

	/**
	 * Handles a array literal expression.
	 */
	public Void visitArrayLiteralExpr(ArrayLiteralExprContext ctx) {
		makeArray(ctx, "<arr_" + ctx.hashCode() + ">", false);
		return null;
	}

	/**
	 * Creates an array.
	 * 
	 * @param ctx
	 *            ArrayLiteralExprContext.
	 * @param id
	 *            The identifier (name) of the array.
	 * @param shared
	 *            Whether the array is shared or not.
	 */
	private void makeArray(ArrayLiteralExprContext ctx, String id, boolean shared) {
		ctx.expr().forEach(e -> visit(e));
		Type type = getType(ctx.expr(0));
		if (ctx.expr().stream().anyMatch(e -> !getType(e).equals(type))) {
			error(ctx, "Mixed types in array");
			return;
		}

		int count = ctx.expr().size();
		Type arr = new Array(type, count);
		types.put(ctx, arr);
		scope.declare(id, arr, shared);
		result.getOffsets().put(ctx, scope.getOffset(id));
	}

	/**
	 * Handles a dereference expression.
	 */
	public Void visitDerefExpr(DerefExprContext ctx) {
		visit(ctx.expr());
		Type ptr = types.get(ctx.expr());
		if (!(ptr instanceof Type.Pointer)) {
			error(ctx, "Tried dereferencing %s (type %s), which is not a pointer.", ctx.expr().getText(), ptr);
			types.put(ctx, Primitive.ERR_TYPE);
			return null;
		}
		types.put(ctx, ((Type.Pointer) ptr).getWrappedType());
		return null;
	}

	/**
	 * Handles a division expression.
	 */
	public Void visitDivExpr(DivExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Handles an enum declaration.
	 */
	public Void visitEnumDecl(EnumDeclContext ctx) {
		List<String> values = new ArrayList<>();
		for (int i = 1; i < ctx.TYPE().size(); i++)
			values.add(ctx.TYPE(i).getText());
		String name = ctx.TYPE(0).getText();
		if (result.getEnums().stream().anyMatch(en -> en.getName().equalsIgnoreCase(name))) {
			error(ctx, "Duplicate enum declaration (%s).", name);
		} else {
			Enum en = new Enum(name, values);
			result.getEnums().add(en);
			types.put(ctx, en);
		}
		return null;
	}

	/**
	 * Handles an enum expression.
	 */
	public Void visitEnumExpr(EnumExprContext ctx) {
		String name = ctx.TYPE(0).getText();
		Enum type = result.getEnums().stream().filter(e -> e.getName().equalsIgnoreCase(name)).findAny()
				.orElseGet(null);
		if (type == null)
			error(ctx, "Undeclared enum type: %s", name);
		else {
			String value = ctx.TYPE(1).getText();
			if (!type.getValues().contains(value)) {
				error(ctx, "Enum %s has no constant '%s'.", name, value);
			} else {
				types.put(ctx, type);
			}
		}
		return null;
	}

	/**
	 * Handles an  array expression with the value of an expression.
	 */
	public Void visitExprArrayExpr(ExprArrayExprContext ctx) {
		visit(ctx.arrayVal());
		types.put(ctx, getType(ctx.arrayVal()));
		result.getOffsets().put(ctx, result.getOffsets().get(ctx.arrayVal()));
		shared.put(ctx, scope.isShared(ctx.arrayVal().ID().getText()));
		return null;
	}

	/**
	 * Handles a false expression.
	 */
	public Void visitFalseExpr(FalseExprContext ctx) {
		types.put(ctx, Primitive.BOOL);
		return null;
	}

	/**
	 * Handles a for statement.
	 */
	public Void visitForStat(ForStatContext ctx) {
		if (!checkType(ctx, Primitive.INT, typeForName(ctx, ctx.decl().type(0).getText()))) {
			return null;
		}
		scope.openScope();
		visit(ctx.decl());
		visit(ctx.expr());
		checkType(ctx, Primitive.BOOL, ctx.expr());
		visit(ctx.assign());
		visit(ctx.block());
		scope.closeScope();
		return null;
	}

	/**
	 * Handles a function.
	 */
	public Void visitFunc(FuncContext ctx) {
		Type retType = Type.forName(ctx.type().getText());
		types.put(ctx, retType);

		String name = ctx.ID().getText();
		if (!checkName(ctx, name))
			return null;

		List<Type> argTypes = ctx.typedparams().type().stream().map(t -> typeForName(ctx, t.getText()))
				.collect(Collectors.toList());
		Func func = new Func(name, retType, argTypes);
		params.put(func, ctx.typedparams());
		if (scope.isDeclared(func)) {
			error(ctx, "Duplicate declaration of function %s.", func);
			return null;
		}
		scope.declare(func);
		functions.put(ctx, func);
		result.getFunctions().add(ctx);
		types.put(ctx, retType);

		return null;
	}

	/**
	 * Handles an id expression.
	 */
	public Void visitIdExpr(IdExprContext ctx) {
		if (!scope.isDeclared(ctx.ID().getText())) {
			error(ctx, "Variable %s was not declared in this scope.", ctx.ID().getText());
			return null;
		}
		types.put(ctx, getType(ctx, ctx.ID().getText()));
		shared.put(ctx.ID(), scope.isShared(ctx.ID().getText()));
		result.getOffsets().put(ctx.ID(), scope.getOffset(ctx.ID().getText()));
		return null;
	}

	/**
	 * Handles an if statement.
	 */
	public Void visitIfStat(IfStatContext ctx) {
		for (int i = 0; i < ctx.expr().size(); i++) {
			visit(ctx.expr(i));
			checkType(ctx, Primitive.BOOL, getType(ctx.expr(i)));
		}
		ctx.block().forEach(block -> visit(block));
		return null;
	}

	/**
	 * Handles an in statement.
	 */
	public Void visitInStat(InStatContext ctx) {
		visit(ctx.ID());
		return null;
	}

	/**
	 * Handles a lock statement.
	 */
	public Void visitLockStat(LockStatContext ctx) {
		locks.add(ctx.ID().getText());
		if (ctx.block().toString().matches("\\breturn\\b")) {
			error(ctx, "Illegal return statement in locked block %s. (Returns from locks are not allowed)",
					ctx.ID().getText());
		}
		visit(ctx.block());
		return null;
	}

	/**
	 * Handles a subtraction expression.
	 */
	public Void visitMinExpr(MinExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Handles a modulo expression.
	 */
	public Void visitModExpr(ModExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Handles an multiplication expression.
	 */
	public Void visitMultExpr(MultExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Handels the negation of a boolean.
	 */
	public Void visitNegBoolExpr(NegBoolExprContext ctx) {
		visit(ctx.expr());
		if (checkType(ctx, Primitive.BOOL, ctx.expr()))
			types.put(ctx, Primitive.BOOL);
		else
			types.put(ctx, Primitive.ERR_TYPE);
		return null;
	}

	/**
	 * Handles the negation of a number.
	 */
	public Void visitNegNumExpr(NegNumExprContext ctx) {
		visit(ctx.expr());
		if (checkType(ctx, Primitive.INT, ctx.expr()))
			types.put(ctx, Primitive.INT);
		else
			types.put(ctx, Primitive.ERR_TYPE);
		return null;
	}

	/**
	 * Handles numbers.
	 */
	public Void visitNumExpr(NumExprContext ctx) {
		types.put(ctx, Primitive.INT);
		return null;
	}

	/**
	 * Handles an out statement.
	 */
	public Void visitOutStat(OutStatContext ctx) {
		visit(ctx.expr());
		return null;
	}

	/**
	 * Handels a par expression (with brackets).
	 */
	public Void visitParExpr(ParExprContext ctx) {
		visit(ctx.expr());
		types.put(ctx, getType(ctx.expr()));
		return null;
	}

	/**
	 * Handles a addition expression.
	 */
	public Void visitPlusExpr(PlusExprContext ctx) {
		arithmeticExpr(ctx, ctx.expr(0), ctx.expr(1));
		return null;
	}

	/**
	 * Handels a program.
	 */
	public Void visitProgram(ProgramContext ctx) {

		TerminalNode sprockellCount = ctx.progdef().NUMBER();
		if (sprockellCount != null && Integer.parseInt(sprockellCount.getText()) < 1) {
			error(ctx, "Invalid Sprockell count: %d. Please supply a value >= 1.", sprockellCount);
		}

		ctx.decl().forEach(decl -> visit(decl));
		ctx.enumDecl().forEach(edecl -> visit(edecl));
		ctx.func().forEach(func -> visit(func));
		Func main = functions.values().stream().filter(f -> f.equals(Generator.MAIN_FUNC_SIG)).findAny()
				.orElseGet(() -> {
					error(ctx, "No main function was defined. Please define the main function as %s",
							Generator.MAIN_FUNC_SIG);
					return null;
				});
		/*
		 * if (main != null) { Queue<Func> funcs = new ArrayDeque<>();
		 * funcs.offer(main); Func prev = null; while (!funcs.isEmpty()) { Func
		 * current = funcs.poll(); if (current.equals(prev)) continue;
		 * FuncContext fctx = functions.entrySet().stream() .filter(e ->
		 * e.getValue().equals(current)).findAny() .map(e -> e.getKey()).get();
		 * processFunction(fctx, current); List<Func> callees; if ((callees =
		 * callTree.get(current)) != null) callees.forEach(callee ->
		 * funcs.offer(callee)); prev = current; } }
		 */
		functions.forEach((fctx, func) -> processFunction(fctx, func));
		return null;
	}

	/**
	 * Handles a reference expression.
	 */
	public Void visitRefExpr(RefExprContext ctx) {
		visit(ctx.expr());
		types.put(ctx, new Type.Pointer(types.get(ctx.expr())));
		return null;
	}

	/**
	 * Handles a return statement.
	 */
	public Void visitReturnStat(ReturnStatContext ctx) {
		Type expected = currentFunc.getReturnType();
		if (ctx.expr() != null) {
			visit(ctx.expr());
			Type exprType = getType(ctx.expr());
			if (exprType == Primitive.ERR_TYPE)
				error(ctx, "<Unable to check function return type because the expression cannot be evaluated>");
			else if (!exprType.equals(expected)) {
				error(ctx, "Return expression is of type '%s', but function '%s' should return '%s'.", exprType,
						currentFunc.getName(), expected);
			}
		} else {
			if (expected != Primitive.VOID) {
				error(ctx, "Returning void in function '%s' with non-void return type '%s'.", currentFunc.getName(),
						expected);
			}
		}
		types.put(ctx, expected);
		return null;
	}

	/**
	 * Handles a Sprockell ID expression.
	 */
	public Void visitSpidExpr(SpidExprContext ctx) {
		types.put(ctx, Primitive.INT);
		return null;
	}

	/**
	 * Handles a top level block.
	 */
	public Void visitTopLevelBlock(TopLevelBlockContext ctx) {
		scope.openScope();
		visit(params.get(currentFunc));
		ctx.block().stat().forEach(s -> visit(s));
		scope.closeScope();
		return null;
	}

	/**
	 * Handles a true expression.
	 */
	public Void visitTrueExpr(TrueExprContext ctx) {
		types.put(ctx, Primitive.BOOL);
		return null;
	}

	/**
	 * Handles typed parameters.
	 */
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

	/**
	 * Handles a while statement.
	 */
	public Void visitWhileStat(WhileStatContext ctx) {
		visit(ctx.expr());
		checkType(ctx, Primitive.BOOL, getType(ctx.expr()));
		visit(ctx.block());
		return null;
	}

	/**
	 * Handles a arithmetic expression.
	 * 
	 * @param ctx
	 *            The operator.
	 * @param fst
	 *            The left hand side of an arithmetic expression.
	 * @param snd
	 *            The right hand side of an arithmetic expression.
	 */
	private void arithmeticExpr(ExprContext ctx, ExprContext fst, ExprContext snd) {
		visit(fst);
		visit(snd);
		checkType(ctx, Primitive.INT, fst);
		checkType(ctx, Primitive.INT, snd);
		types.put(ctx, Primitive.INT);
	}

	/**
	 * Handles the call of a function.
	 * 
	 * @param ctx
	 *            The content of the call.
	 * @param expectedReturn
	 *            The Type of the expected return.
	 * @return The function.
	 */
	private Func call(CallContext ctx, Type expectedReturn) {
		List<Type> args = new ArrayList<>();
		ctx.params().expr().stream().forEachOrdered(val -> {
			visit(val);
			args.add(getType(val));
			if (val instanceof IdExprContext) {
				shared.put(val, scope.isShared(((IdExprContext) val).ID().getText()));
			}
		});
		if (args.stream().anyMatch(type -> type == Primitive.ERR_TYPE))
			return null;
		Func func = null;
		if (expectedReturn != null) {
			func = new Func(ctx.ID().getText(), expectedReturn, args);
			if (!scope.isDeclared(func)) {
				error(ctx, "Function '%s' with types '%s' does not exist.", ctx.ID().getText(), args.toString());
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
			List<Func> matches = scope.getFunctions().stream()
					.filter(f -> f.getName().equals(ctx.ID().getText()) && f.getArgs().equals(args))
					.collect(Collectors.toList());

			if (matches.isEmpty()) {
				error(ctx, "Function '%s' with types '%s' does not exist.", ctx.ID().getText(), args.toString());
				return null;
			} else if (matches.size() > 1) {
				error(ctx, "Ambigous function call %s%s, all of the following definitions match: %s",
						ctx.ID().getText(), args, matches);
				return null;
			} else {
				res = matches.get(0);
			}

			if (res == null) {
				error(ctx, "Function '%s' with types '%s' does not exist.", ctx.ID().getText(), args.toString());
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
	 * Checks whether a name is a reserved keyword or not.
	 * 
	 * @param tree
	 *            The ParseTree on which this method is run.
	 * @param name
	 *            The word that needs to be checked.
	 * @return Whether a name is reserverd or not.
	 */
	private boolean checkName(ParseTree tree, String name) {
		if (INVALID_NAMES.stream().anyMatch(inv -> name.equalsIgnoreCase(inv))) {
			error(tree, "Invalid name: '%s' is a reserved word.", name);
			return false;
		}
		return true;
	}

	/**
	 * Check the type of rule.
	 * 
	 * @param tree
	 *            The parse tree on which this function is run.
	 * @param expected
	 *            The expected type.
	 * @param ctx
	 *            The context.
	 * @return Whether the type is correct or not.
	 */
	private boolean checkType(ParseTree tree, Type expected, ParserRuleContext ctx) {
		return checkType(tree, expected, getType(ctx));
	}

	/**
	 * Check whether two types are the same.
	 * 
	 * @param tree
	 *            The parse tree on which this function is run.
	 * @param expected
	 *            The expected type.
	 * @param actual
	 *            The actual type.
	 * @return Whether the expected and actual type are the same.
	 */
	private boolean checkType(ParseTree tree, Type expected, Type actual) {
		if (!expected.equals(actual)) {
			if (actual == Primitive.ERR_TYPE)
				error(tree, "<Caused by earlier error>");
			else
				error(tree, "Type mismatch. Expected '%s', but saw '%s'.", expected.toString(), actual.toString());
			return false;
		}
		return true;
	}

	/**
	 * Saves errors
	 * 
	 * @param tree
	 *            The parse tree on which this function is run.
	 * @param format
	 *            Contains variables.
	 * @param args
	 *            Contains values.
	 */
	private void error(ParseTree tree, String format, Object... args) {
		String res = "Parse Error ";
		if (tree instanceof ParserRuleContext) {
			ParserRuleContext ctx = (ParserRuleContext) tree;
			res += ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine();
		} else {
			TerminalNode node = (TerminalNode) tree;
			res += node.getSymbol().getLine() + ":" + node.getSymbol().getCharPositionInLine();
		}
		res += "(" + tree.getClass().getSimpleName() + ")";
		res += " - " + String.format(format, args);
		errors.add(res);
		result.getErrors().add(res);
	}

	/**
	 * Returns a TerminalNode.
	 * 
	 * @param ctx
	 *            The dereference id context.
	 * @return A TerminalNode.
	 */
	private TerminalNode getID(DerefIDContext ctx) {
		if (ctx.ID() != null)
			return ctx.ID();
		else
			return getID(ctx.derefID());
	}

	/**
	 * The type of a ParserRuleContext.
	 * 
	 * @param ctx
	 *            The ParserRuleContext.
	 * @return The type of a ParserRuleContext.
	 */
	private Type getType(ParserRuleContext ctx) {
		if (ctx instanceof NumExprContext)
			return Primitive.INT;
		else if (ctx instanceof TrueExprContext || ctx instanceof FalseExprContext)
			return Primitive.BOOL;
		Type type = types.get(ctx);
		if (type == null)
			return Primitive.ERR_TYPE;
		return type;
	}

	/**
	 * Returns the type of a variable in a ParseTree.
	 * 
	 * @param tree
	 *            The ParseTree.
	 * @param varId
	 *            The name of the variable.
	 * @return The type of a variable.
	 */
	private Type getType(ParseTree tree, String varId) {
		if (varId.equalsIgnoreCase("true") || varId.equalsIgnoreCase("false"))
			return Primitive.BOOL;
		if (varId.codePoints().allMatch(i -> Character.isDigit(i)))
			return Primitive.INT;
		if (scope.isDeclared(varId))
			return scope.getType(varId);
		error(tree, "Variable '%s' was not declared in this scope.", varId);
		return Primitive.ERR_TYPE;
	}

	/**
	 * Handles a function
	 * 
	 * @param ctx
	 *            The content of the function
	 * @param func
	 *            The function.
	 */
	private void processFunction(FuncContext ctx, Func func) {
		currentFunc = func;
		visit(ctx.topLevelBlock());
		boolean foundReturn = false;
		if (ctx.topLevelBlock().block().children.stream().noneMatch(tree -> tree instanceof ReturnStatContext)) {
			error(ctx, "No return from function %s.", func);
		}
		currentFunc = null;
	}

	/**
	 * Returns the type of a variable with a certain name.
	 * 
	 * @param tree
	 *            The ParseTree on which this function is run.
	 * @param typeName
	 *            The name of the variable.
	 * @return The type of the variable.
	 */
	private Type typeForName(ParseTree tree, String typeName) {
		try {
			Type t = Type.forName(typeName);
			if (t == Enum.DUMMY) {
				String baseName = typeName.replaceAll("[\\[\\]\\*]", "");
				Type type = result.getEnums().stream().filter(e -> e.getName().equalsIgnoreCase(baseName)).findAny()
						.orElseThrow(RuntimeException::new);
				if (typeName.endsWith("[]")) {
					type = new Array(type);
					typeName = typeName.replaceAll("\\[\\]", "");
				}
				while (typeName.endsWith("*")) {
					type = new Pointer(type);
					typeName = typeName.replaceFirst("\\*", "");
				}
				return type;
			}
			return t;
		} catch (IllegalArgumentException e) {
			error(tree, e.getMessage());
		}
		return Primitive.ERR_TYPE;
	}
}