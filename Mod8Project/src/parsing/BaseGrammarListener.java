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
	 * Enter a parse tree produced by {@link BaseGrammarParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(@NotNull BaseGrammarParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link BaseGrammarParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(@NotNull BaseGrammarParser.FieldContext ctx);
}