package translation;

/**
 * Operands for use in Spril instructions.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public interface Operand {
	/**
	 * @return The Haskell statement corresponding to the operand.
	 */
	String toCode();

	/**
	 * @return The type of this operand.
	 */
	OpType getType();

	@Override
	boolean equals(Object obj);

	@Override
	int hashCode();
}