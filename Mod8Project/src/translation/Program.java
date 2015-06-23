package translation;

import java.util.ArrayList;
import java.util.List;

/**
 * A Sprockell program
 * @author Dennis
 *
 */
public class Program {

	private String name;
	private int coreCount;
	private List<Spril> instructions;

	/**
	 * Create a new, empty program
	 */
	public Program() {
		this.instructions = new ArrayList<>();
	}

	public Program(String name, int coreCount) {
		this();
		this.name = name;
		this.coreCount = coreCount;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoreCount() {
		return coreCount <= 1 ? 1 : coreCount;
	}

	public void setCoreCount(int coreCount) {
		this.coreCount = coreCount;
	}
}
