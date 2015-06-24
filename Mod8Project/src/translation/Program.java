package translation;

import java.util.ArrayList;
import java.util.List;

/**
 * A Sprockell program
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public class Program {

	// Instance variables
	private String name;
	private int coreCount;
	private List<Spril> instructions;

	/**
	 * Create a new, empty program
	 */
	public Program() {
		this.instructions = new ArrayList<>();
	}

	/**
	 * Constructor Program is used to set the instance variables
	 * @param name The name that needs to be set.
	 * @param coreCount The coreCount that needs to be set.
	 */
	public Program(String name, int coreCount) {
		this();
		this.name = name;
		this.coreCount = coreCount;
	}
	
	/**
	 * getInstructions() returns list with instructions (Sprils)
	 * @return instructions, the list with instructions
	 */
	public List<Spril> getInstructions() {
		return instructions;
	}

	/**
	 * setInstructions() sets the a List with instructions
	 * @param instructions, the List with instructions that needs to be set
	 */
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

	/**
	 * getName() returns the name of the program
	 * @return name, the name of the program
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName() sets the name of the program
	 * @param name, the name of the program
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getCoreCount() returns the amount of cores on which the program has to be run
	 * @return the amount of cores of the program
	 */
	public int getCoreCount() {
		return coreCount <= 1 ? 1 : coreCount;
	}

	/**
	 * setCoreCount() sets the amount of cores on which the program has to be run
	 * @param coreCount, the amount of cores of the program
	 */
	public void setCoreCount(int coreCount) {
		this.coreCount = coreCount;
	}
}
