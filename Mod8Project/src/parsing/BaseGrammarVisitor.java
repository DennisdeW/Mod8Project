// Generated from BaseGrammar.g4 by ANTLR 4.4
package parsing;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BaseGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BaseGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(@NotNull BaseGrammarParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinExpr(@NotNull BaseGrammarParser.MinExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModExpr(@NotNull BaseGrammarParser.ModExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(@NotNull BaseGrammarParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueExpr(@NotNull BaseGrammarParser.TrueExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code outStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutStat(@NotNull BaseGrammarParser.OutStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefix(@NotNull BaseGrammarParser.PrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#typedparams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedparams(@NotNull BaseGrammarParser.TypedparamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negBoolExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegBoolExpr(@NotNull BaseGrammarParser.NegBoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclStat(@NotNull BaseGrammarParser.DeclStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull BaseGrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull BaseGrammarParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprArrayExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprArrayExpr(@NotNull BaseGrammarParser.ExprArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpr(@NotNull BaseGrammarParser.ParExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lockStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockStat(@NotNull BaseGrammarParser.LockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(@NotNull BaseGrammarParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(@NotNull BaseGrammarParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull BaseGrammarParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivExpr(@NotNull BaseGrammarParser.DivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(@NotNull BaseGrammarParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseExpr(@NotNull BaseGrammarParser.FalseExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#topLevelBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopLevelBlock(@NotNull BaseGrammarParser.TopLevelBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(@NotNull BaseGrammarParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(@NotNull BaseGrammarParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVal(@NotNull BaseGrammarParser.ValContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#boolOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOp(@NotNull BaseGrammarParser.BoolOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constArrayExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstArrayExpr(@NotNull BaseGrammarParser.ConstArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp(@NotNull BaseGrammarParser.CompContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#progdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgdef(@NotNull BaseGrammarParser.ProgdefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolOpExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOpExpr(@NotNull BaseGrammarParser.BoolOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStat(@NotNull BaseGrammarParser.CallStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(@NotNull BaseGrammarParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(@NotNull BaseGrammarParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumExpr(@NotNull BaseGrammarParser.NumExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusExpr(@NotNull BaseGrammarParser.PlusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStat(@NotNull BaseGrammarParser.ForStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(@NotNull BaseGrammarParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#derefID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDerefID(@NotNull BaseGrammarParser.DerefIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(@NotNull BaseGrammarParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code refExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefExpr(@NotNull BaseGrammarParser.RefExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code derefExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDerefExpr(@NotNull BaseGrammarParser.DerefExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInStat(@NotNull BaseGrammarParser.InStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLiteralExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteralExpr(@NotNull BaseGrammarParser.ArrayLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(@NotNull BaseGrammarParser.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negNumExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegNumExpr(@NotNull BaseGrammarParser.NegNumExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(@NotNull BaseGrammarParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BaseGrammarParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(@NotNull BaseGrammarParser.AssignContext ctx);
}