package translation;

/**
 * A representation of Spril operators.
 * 
 * @author Dennis
 *
 */
public enum Operator implements Operand {
	ADD, SUB, MUL, DIV, MOD, EQ("Equal"), NEQ("NEq"), GT, GTE("GtE"), LT, LTE("LtE"), AND, OR, XOR, LSHIFT(
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
	
	public Operator invert() {
		switch(this) {
		case EQ:
			return EQ;
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
