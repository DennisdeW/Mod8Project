package translation;

import static translation.OpType.INT;
import static translation.OpType.MEM;
import static translation.OpType.OP;
import static translation.OpType.REG;
import static translation.OpType.TARGET;

/**
 * The different OpCodes defined by the Spril language
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public enum OpCode {
	CONST(INT, REG), COMPUTE(OP, REG, REG, REG), LOAD(MEM, REG), STORE(REG, MEM), BRANCH(
			REG, TARGET), JUMP(TARGET), PUSH(REG), POP(REG), NOP(), END_PROG(
			"EndProg"), READ(MEM), RECEIVE(REG), WRITE(REG, MEM), TEST_AND_SET(
			"TestAndSet", MEM), OUT(REG), IN(REG);

	// Instance variables
	
	private OpType[] operands;
	private String name;

	/**
	 * Private Constructor
	 * @param operands
	 */
	private OpCode(OpType... operands) {
		this.name = name().charAt(0) + name().substring(1).toLowerCase();
		this.operands = new OpType[4];
		System.arraycopy(operands, 0, this.getOperands(), 0, operands.length);
	}

	/**
	 * Private Constructor
	 * @param name
	 * @param operands
	 */
	private OpCode(String name, OpType... operands) {
		this(operands);
		this.name = name;
	}


	public String toString() {
		return name;
	}

	public OpType[] getOperands() {
		return operands;
	}
}
