package translation;

/**
 * A wrapper for an int.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class Int implements Operand {

	/**
	 * The value of this Int.
	 */
	private final int value;

	/**
	 * Construct an Int with the given value.
	 * 
	 * @param value
	 *            The value of this Int
	 */
	public Int(int value) {
		this.value = value;
	}

	/**
	 * @return The value wrapped by this Int
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toCode() {
		return value >= 0 ? value + "" : "(0-" + -value + ")";
	}

	@Override
	public OpType getType() {
		return OpType.INT;
	}

}
