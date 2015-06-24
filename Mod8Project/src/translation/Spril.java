package translation;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * An instruction in the Spril language.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public class Spril {

	// Instance variables
	private Operand[] operands;
	private OpCode opcode;
	private String comment;

	/**
	 * Build a new instruction.
	 * 
	 * @param opcode
	 *            The OpCode to use.
	 * @param operands
	 *            The Operands required by the instruction.
	 * @throws IllegalArgumentException
	 *             If the supplied operands do not match the ones the given
	 *             OpCode requires.
	 */
	public Spril(OpCode opcode, Operand... operands) {
		this(opcode, "", operands);
	}

	/**
	 * Build a new instruction.
	 * 
	 * @param opcode
	 *            The OpCode to use
	 * @param comment
	 *            A comment to place behind the instruction.
	 * @param operands
	 *            The Operands required by the instruction.
	 * @throws IllegalArgumentException
	 *             If the supplied operands do not match the ones the given
	 *             OpCode requires.
	 */
	public Spril(OpCode opcode, String comment, Operand... operands) {
		this.opcode = opcode;
		this.operands = new Operand[4];
		this.comment = comment;
		System.arraycopy(operands, 0, this.operands, 0, operands.length);
		for (int i = 0; i < 4; i++)
			if ((this.operands[i] == null ^ opcode.getOperands()[i] == null))
				throw new IllegalArgumentException(String.format(
						"Operand %d: expected %s but got %s.",
						i,
						opcode.getOperands()[i],
						this.operands[i] == null ? "null" : this.operands[i]
								.getType()));
			else if (this.operands[i] != null
					&& this.operands[i].getType() != opcode.getOperands()[i])
				throw new IllegalArgumentException(String.format(
						"Operand %d: Expected type %s, but got %s.", i,
						opcode.getOperands()[i], this.operands[i].getType()));
	}

	/**
	 * getOpCode() returns opcode, the opcode corresponding with this Spril
	 * 
	 * @return opCode
	 */
	public OpCode getOpCode() {
		return opcode;
	}

	/**
	 * getOperands() returns the array containing operands
	 * 
	 * @return operands
	 */
	public Operand[] getOperands() {
		return operands;
	}

	@Override
	public String toString() {
		String res = opcode.toString();
		res += " "
				+ Arrays.stream(operands)
						.map(op -> op == null ? "" : op.toCode())
						.collect(Collectors.joining(" ")).trim();
		if (!comment.equals("")) {
			int spacing = res.length();
			while (spacing++ < 30)
				res += " ";
			res += "{-" + comment + "-}";
		}
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((opcode == null) ? 0 : opcode.hashCode());
		result = prime * result + Arrays.hashCode(operands);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spril other = (Spril) obj;
		if (opcode != other.opcode)
			return false;
		if (!Arrays.equals(operands, other.operands))
			return false;
		return true;
	}

	/**
	 * getComment() returns the comment of this spril
	 * 
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * addComment() adds a comment
	 * 
	 * @param comment
	 */
	public void addComment(String comment) {
		if (this.comment.equals("")) {
			this.comment = comment;
		} else {
			this.comment += "; " + comment;
		}
	}

	/**
	 * setComment() sets the comment of this Spril
	 * 
	 * @param comment
	 *            , the value that needs to be set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
}