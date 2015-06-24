package translation;

/**
 * A jump target. It may be absolute, relative or indirect (using a register).
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public class Target implements Operand {

	// Instance variables
	private int addr;
	private Register reg;
	private boolean isReg, isAbs;

	/**
	 * Constructor Target sets certain instance variables
	 * 
	 * @param reg
	 *            the register that needs to be set
	 */
	private Target(Register reg) {
		this.reg = reg;
		this.isReg = true;
	}

	/**
	 * Constructor Target sets certain instance variables
	 * 
	 * @param addr
	 *            the address that needs to be set
	 * @param isAbs
	 *            the value for isAbs that needs to be set
	 */
	private Target(int addr, boolean isAbs) {
		this.addr = addr;
		this.isReg = false;
		this.isAbs = isAbs;
	}

	/**
	 * Make a Target using a Register. The PC will be set to the value stored in
	 * the register.
	 * 
	 * @param reg
	 *            The register which will store the address.
	 * @return A Target pointing to <code>reg</code>
	 */
	public static Target indirect(Register reg) {
		return new Target(reg);
	}

	/**
	 * Make an absolute Target. The PC will be set to the given value.
	 * 
	 * @param addr
	 *            The address to jump to.
	 * @return A Target pointing to <code>addr</code>
	 */
	public static Target absolute(int addr) {
		return new Target(addr, true);
	}

	/**
	 * Make a relative Target. The PC will be set to the current value plus the
	 * given value.
	 * 
	 * @param offset
	 *            The address offset to use.
	 * @return A relative Target jumping the given amount.
	 */
	public static Target relative(int offset) {
		return new Target(offset, false);
	}

	@Override
	public String toCode() {
		return isReg ? "(Ind " + reg.toCode() + ")" : (isAbs ? "(Abs "
				: "(Rel ") + (addr >= 0 ? addr : "(0-" + -addr + ")") + ")";
	}

	@Override
	public OpType getType() {
		return OpType.TARGET;
	}
}