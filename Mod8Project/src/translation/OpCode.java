package translation;

import static translation.OpType.*;

/**
 * The different OpCodes defined by the Spril language.
 * 
 * @author Dennis
 *
 */
public enum OpCode {
	CONST(INT, REG), COMPUTE(OP, REG, REG, REG), LOAD(MEM, REG), STORE(REG, MEM), BRANCH(
			REG, TARGET), JUMP(TARGET), PUSH(REG), POP(REG), NOP(), END_PROG(
			"EndProg"), READ(MEM), RECEIVE(REG), WRITE(REG, MEM);

	private OpType[] operands;
	private String name;

	private OpCode(OpType... operands) {
		this.name = name().charAt(0) + name().substring(1).toLowerCase();
		this.operands = new OpType[4];
		System.arraycopy(operands, 0, this.getOperands(), 0, operands.length);
	}

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