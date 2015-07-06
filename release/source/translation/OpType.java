package translation;

/**
 * The different types of operands.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public enum OpType {
	/**
	 * Immediate Integers.
	 */
	INT,
	/**
	 * Memory Addresses, either immediate or through a register.
	 */
	MEM,
	/**
	 * Registers, either one of the general-purpose registers or a specialized
	 * one.
	 */
	REG,
	/**
	 * Operands as listed in the Sprockell docs.
	 */
	OP,
	/**
	 * Jump targets, can be relative, absolute or indirect.
	 */
	TARGET;
}