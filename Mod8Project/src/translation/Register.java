package translation;

/**
 * The registers of a Sprockell
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public enum Register implements Operand {
	ZERO("Zero"), PC("PC"), SP("SP"), SPID("SPID"), A("RegA"), B("RegB"), C(
			"RegC"), D("RegD"), E("RegE");

	// Instance variables
	private final String name;

	/**
	 * Constructor Register sets the name of the Register
	 * @param name, the name that needs to be set
	 */
	private Register(String name) {
		this.name = name;
	}

	/**
	 * getName() returns the name of the register
	 * @return name, the name of the register
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toCode() {
		return name;
	}

	@Override
	public OpType getType() {
		return OpType.REG;
	}

}
