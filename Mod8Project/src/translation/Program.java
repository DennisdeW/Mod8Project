package translation;

import java.util.ArrayList;
import java.util.List;

/**
 * A Sprockell program
 * @author Dennis
 *
 */
public class Program {

	private List<Spril> instructions;

	/**
	 * Create a new, empty program
	 */
	public Program() {
		this.instructions = new ArrayList<>();
	}

	public List<Spril> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Spril> instructions) {
		this.instructions = instructions;
	}

	/**
	 * Add an instruction to the program.
	 * @param instr The instruction to add.
	 */
	public void addInstruction(Spril instr) {
		this.instructions.add(instr);
	}

	/**
	 * Write the program to a String array: One instruction per array element.
	 * @return The resulting array.
	 */
	public String[] print() {
		return this.instructions.stream().map(instr -> instr.toString())
				.toArray(String[]::new);
	}
}
