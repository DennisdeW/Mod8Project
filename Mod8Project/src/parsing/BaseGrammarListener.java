// Generated from BaseGrammar.g4 by ANTLR 4.4
package parsing;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BaseGrammarParser}.
 */
public interface BaseGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(@NotNull BaseGrammarParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(@NotNull BaseGrammarParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void enterArrVal(@NotNull BaseGrammarParser.ArrValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void exitArrVal(@NotNull BaseGrammarParser.ArrValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMinExpr(@NotNull BaseGrammarParser.MinExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMinExpr(@NotNull BaseGrammarParser.MinExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterModExpr(@NotNull BaseGrammarParser.ModExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitModExpr(@NotNull BaseGrammarParser.ModExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(@NotNull BaseGrammarParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(@NotNull BaseGrammarParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#prefix}.
	 * @param ctx the parse tree
	 */
	void enterPrefix(@NotNull BaseGrammarParser.PrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#prefix}.
	 * @param ctx the parse tree
	 */
	void exitPrefix(@NotNull BaseGrammarParser.PrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negBoolExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegBoolExpr(@NotNull BaseGrammarParser.NegBoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negBoolExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegBoolExpr(@NotNull BaseGrammarParser.NegBoolExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull BaseGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull BaseGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull BaseGrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull BaseGrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(@NotNull BaseGrammarParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(@NotNull BaseGrammarParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(@NotNull BaseGrammarParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(@NotNull BaseGrammarParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(@NotNull BaseGrammarParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(@NotNull BaseGrammarParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull BaseGrammarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull BaseGrammarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDivExpr(@NotNull BaseGrammarParser.DivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDivExpr(@NotNull BaseGrammarParser.DivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalseExpr(@NotNull BaseGrammarParser.FalseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalseExpr(@NotNull BaseGrammarParser.FalseExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#topLevelBlock}.
	 * @param ctx the parse tree
	 */
	void enterTopLevelBlock(@NotNull BaseGrammarParser.TopLevelBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#topLevelBlock}.
	 * @param ctx the parse tree
	 */
	void exitTopLevelBlock(@NotNull BaseGrammarParser.TopLevelBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(@NotNull BaseGrammarParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(@NotNull BaseGrammarParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constArrayExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstArrayExpr(@NotNull BaseGrammarParser.ConstArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constArrayExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstArrayExpr(@NotNull BaseGrammarParser.ConstArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#comp}.
	 * @param ctx the parse tree
	 */
	void enterComp(@NotNull BaseGrammarParser.CompContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#comp}.
	 * @param ctx the parse tree
	 */
	void exitComp(@NotNull BaseGrammarParser.CompContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolOpExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolOpExpr(@NotNull BaseGrammarParser.BoolOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolOpExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolOpExpr(@NotNull BaseGrammarParser.BoolOpExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(@NotNull BaseGrammarParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(@NotNull BaseGrammarParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(@NotNull BaseGrammarParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(@NotNull BaseGrammarParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumExpr(@NotNull BaseGrammarParser.NumExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumExpr(@NotNull BaseGrammarParser.NumExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterForStat(@NotNull BaseGrammarParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitForStat(@NotNull BaseGrammarParser.ForStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#derefID}.
	 * @param ctx the parse tree
	 */
	void enterDerefID(@NotNull BaseGrammarParser.DerefIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#derefID}.
	 * @param ctx the parse tree
	 */
	void exitDerefID(@NotNull BaseGrammarParser.DerefIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code refExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRefExpr(@NotNull BaseGrammarParser.RefExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code refExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRefExpr(@NotNull BaseGrammarParser.RefExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code derefExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDerefExpr(@NotNull BaseGrammarParser.DerefExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code derefExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDerefExpr(@NotNull BaseGrammarParser.DerefExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void enterFalseVal(@NotNull BaseGrammarParser.FalseValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void exitFalseVal(@NotNull BaseGrammarParser.FalseValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrueExpr(@NotNull BaseGrammarParser.TrueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrueExpr(@NotNull BaseGrammarParser.TrueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code outStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterOutStat(@NotNull BaseGrammarParser.OutStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code outStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitOutStat(@NotNull BaseGrammarParser.OutStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#typedparams}.
	 * @param ctx the parse tree
	 */
	void enterTypedparams(@NotNull BaseGrammarParser.TypedparamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#typedparams}.
	 * @param ctx the parse tree
	 */
	void exitTypedparams(@NotNull BaseGrammarParser.TypedparamsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void enterTrueVal(@NotNull BaseGrammarParser.TrueValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void exitTrueVal(@NotNull BaseGrammarParser.TrueValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code spidVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void enterSpidVal(@NotNull BaseGrammarParser.SpidValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code spidVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void exitSpidVal(@NotNull BaseGrammarParser.SpidValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDeclStat(@NotNull BaseGrammarParser.DeclStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDeclStat(@NotNull BaseGrammarParser.DeclStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprArrayExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprArrayExpr(@NotNull BaseGrammarParser.ExprArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprArrayExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprArrayExpr(@NotNull BaseGrammarParser.ExprArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lockStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterLockStat(@NotNull BaseGrammarParser.LockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lockStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitLockStat(@NotNull BaseGrammarParser.LockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(@NotNull BaseGrammarParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(@NotNull BaseGrammarParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(@NotNull BaseGrammarParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(@NotNull BaseGrammarParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(@NotNull BaseGrammarParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(@NotNull BaseGrammarParser.BoolOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void enterNumVal(@NotNull BaseGrammarParser.NumValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void exitNumVal(@NotNull BaseGrammarParser.NumValContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#progdef}.
	 * @param ctx the parse tree
	 */
	void enterProgdef(@NotNull BaseGrammarParser.ProgdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#progdef}.
	 * @param ctx the parse tree
	 */
	void exitProgdef(@NotNull BaseGrammarParser.ProgdefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterCallStat(@NotNull BaseGrammarParser.CallStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitCallStat(@NotNull BaseGrammarParser.CallStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPlusExpr(@NotNull BaseGrammarParser.PlusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPlusExpr(@NotNull BaseGrammarParser.PlusExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(@NotNull BaseGrammarParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(@NotNull BaseGrammarParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void enterIdVal(@NotNull BaseGrammarParser.IdValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idVal}
	 * labeled alternative in {@link BaseGrammarParser#val}.
	 * @param ctx the parse tree
	 */
	void exitIdVal(@NotNull BaseGrammarParser.IdValContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(@NotNull BaseGrammarParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(@NotNull BaseGrammarParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#arrayVal}.
	 * @param ctx the parse tree
	 */
	void enterArrayVal(@NotNull BaseGrammarParser.ArrayValContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#arrayVal}.
	 * @param ctx the parse tree
	 */
	void exitArrayVal(@NotNull BaseGrammarParser.ArrayValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterInStat(@NotNull BaseGrammarParser.InStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitInStat(@NotNull BaseGrammarParser.InStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayLiteralExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteralExpr(@NotNull BaseGrammarParser.ArrayLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayLiteralExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteralExpr(@NotNull BaseGrammarParser.ArrayLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(@NotNull BaseGrammarParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link BaseGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(@NotNull BaseGrammarParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code spidExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSpidExpr(@NotNull BaseGrammarParser.SpidExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code spidExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSpidExpr(@NotNull BaseGrammarParser.SpidExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negNumExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegNumExpr(@NotNull BaseGrammarParser.NegNumExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negNumExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegNumExpr(@NotNull BaseGrammarParser.NegNumExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(@NotNull BaseGrammarParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link BaseGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(@NotNull BaseGrammarParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BaseGrammarParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(@NotNull BaseGrammarParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(@NotNull BaseGrammarParser.AssignContext ctx);
}