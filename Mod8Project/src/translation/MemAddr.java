package translation;

/**
 * A memory address. It can either be a constant address, or a register containing one.
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public class MemAddr implements Operand {
	// Instance variables
	private Register reg;
	private int addr;
	private boolean isReg;
	
	/**
	 * Build a <code>Deref</code> address.
	 * @param reg The Register which is to contain the required address.
	 */
	private MemAddr(Register reg) {
		this.reg = reg;
		this.isReg = true;
	}
	
	/**
	 * Build a <code>Addr</code> address.
	 * @param addr The address to point to.
	 */
	private MemAddr(int addr) {
		this.addr = addr;
		this.isReg = false;
	}
	
	/**
	 * Build a MemAddr with a direct pointer.
	 * @param addr The address to point to.
	 * @return A MemAddr pointing to <code>addr</code>
	 */
	public static MemAddr direct(int addr) {
		return new MemAddr(addr);
	}
	
	/**
	 * Build a MemAddr which will retrieve its address from the given Register.
	 * @param reg The register which will contain the address.
	 * @return A MemAddr pointing to <code>reg</code>.
	 */
	public static MemAddr deref(Register reg) {
		return new MemAddr(reg);
	}
	
	@Override
	public String toCode() {
		return isReg ? "(Deref " + reg.toCode() + ")" : "(Addr " + addr + ")";
	}

	@Override
	public OpType getType() {
		return OpType.MEM;
	}
}