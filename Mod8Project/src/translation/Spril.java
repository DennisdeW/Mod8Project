package translation;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * An instruction in the Spril language.
 * 
 * @author Dennis
 *
 */
public class Spril {

	private Operand[] operands;
	private OpCode opcode;

	/**
	 * Build a new instruction.
	 * 
	 * @param opcode
	 *            The OpCode to use.
	 * @param operands
	 *            The Operands which the instruction requires.
	 * @throws IllegalArgumentException
	 *             If the supplied operands do not match the ones the given
	 *             OpCode requires.
	 */
	public Spril(OpCode opcode, Operand... operands) {
		this.opcode = opcode;
		this.operands = new Operand[4];
		System.arraycopy(operands, 0, this.operands, 0, operands.length);
		for (int i = 0; i < 4; i++)
			if ((this.operands[i] == null ^ opcode.getOperands()[i] == null))
				throw new IllegalArgumentException(String.format(
						"Operand %d: expected %s but got %s.", i,
						opcode.getOperands()[i], this.operands[i] == null ? "null"
								: this.operands[i].getType()));
			else if (this.operands[i] != null
					&& this.operands[i].getType() != opcode.getOperands()[i])
				throw new IllegalArgumentException(String.format(
						"Operand %d: Expected type %s, but got %s.", i,
						opcode.getOperands()[i], this.operands[i].getType()));
	}

	@Override
	public String toString() {
		String res = opcode.toString();
		res += " "
				+ Arrays.stream(operands)
						.map(op -> op == null ? "" : op.toCode())
						.collect(Collectors.joining(" ")).trim();
		return res;
	}
	
	
}