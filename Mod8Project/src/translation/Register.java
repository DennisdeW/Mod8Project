package translation;

/**
 * The registers of a Sprockell
 * @author Dennis
 *
 */
public enum Register implements Operand {

	ZERO("Zero"), PC("PC"), SP("SP"), SPID("SPID"), A("RegA"), B("RegB"), C(
			"RegC"), D("RegD"), E("RegE");

	private final String name;

	private Register(String name) {
		this.name = name;
	}

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
