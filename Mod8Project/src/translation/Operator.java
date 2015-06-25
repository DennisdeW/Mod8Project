package translation;

/**
 * A representation of Spril operators.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public enum Operator implements Operand {
	ADD, SUB, MUL, DIV, MOD, EQ("Equal"), NEQ("NEq"), GT, GTE("GtE"), LT, LTE(
			"LtE"), AND, OR, XOR, LSHIFT("LShift"), RSHIFT("RShift");

	// Instance variables
	private String name;

	/**
	 * Use default name: First character capitalized, the rest lower case.
	 */
	private Operator() {
		this.name = name().charAt(0) + name().substring(1).toLowerCase();
	}

	/**
	 * Use custom name.
	 * 
	 * @param name
	 *            The name to use.
	 */
	private Operator(String name) {
		this.name = name;
	}

	@Override
	public String toCode() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public OpType getType() {
		return OpType.OP;
	}

	/**
	 * boolOp is used to parse a certain boolean operator.
	 * 
	 * @param text
	 *            The text containing a boolean operator.
	 * @return Operator The operator that is hidden inside <code>text</code>.
	 */
	public static Operator boolOp(String text) {
		switch (text) {
		case "AND":
			return AND;
		case "OR":
			return OR;
		case "XOR":
			return XOR;
		default:
			throw new Error("Impossible BoolOp");
		}
	}

	/**
	 * Comparator is used to parse a certain compare statement.
	 * 
	 * @param text
	 *            The text that contains the comparator.
	 * @return Operator The operator hidden inside <code>text</code>.
	 */
	public static Operator comparator(String text) {
		switch (text) {
		case "==":
			return EQ;
		case ">":
			return GT;
		case ">=":
			return GTE;
		case "<":
			return LT;
		case "<=":
			return LTE;
		case "!=":
			return NEQ;
		default:
			throw new Error("Impossible Comparator");
		}
	}

	/**
	 * invert() is used invert a certain comparator.
	 * 
	 * @return Operator The Operator that is the inverted operator of the input.
	 */
	public Operator invert() {
		switch (this) {
		case EQ:
			return NEQ;
		case GT:
			return LTE;
		case GTE:
			return LT;
		case LT:
			return GTE;
		case LTE:
			return GT;
		case NEQ:
			return EQ;
		default:
			throw new RuntimeException("Can only invert comparators.");
		}
	}
}