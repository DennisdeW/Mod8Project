package translation;

/**
 * A representation of Spril operators.
 * @author Dennis
 *
 */
public enum Operator implements Operand {
	ADD, SUB, MUL, DIV, MOD, EQUAL, NEQ("NEq"), GT, GTE("GtE"), LT, LTE("LtE"), AND, OR, XOR, LSHIFT(
			"LShift"), RSHIFT("RShift");

	private String name;

	/**
	 * Use default name: First character capitalized, the rest lower case.
	 */
	private Operator() {
		this.name = name().charAt(0) + name().substring(1).toLowerCase();
	}
	
	/**
	 * Use custom name.
	 * @param name The name to use.
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

}
