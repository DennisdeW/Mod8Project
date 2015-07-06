package write;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Class Output is used to create a haskell file with Spril instructions. This
 * class does not implement debug functionality.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class Output {

	/**
	 * Method write is used to create a haskell file.
	 * 
	 * @param title
	 *            The title of the program.
	 * @param cores
	 *            The amount on sprockells the program needs to be run on.
	 * @param lines
	 *            The String[] with Spril instructions.
	 */
	public static void write(String title, int cores, String[] lines) {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(title, "UTF-8");
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		}
		writer.println("{-# LANGUAGE RecordWildCards #-}");
		writer.println("import System");
		writer.println("import Sprockell");
		writer.println("import TypesEtc\n");
		writer.println("prog :: [Instruction]");
		writer.println("prog = [");
		if (lines != null) {
			writer.println("		  " + lines[0]);
			for (int i = 1; i < lines.length; i++) {
				writer.println("		, " + lines[i]);
			}
		}
		writer.println("		, EndProg");
		writer.println("       ]\n");
		writer.println("main = run " + cores + " prog");
		writer.flush();
		writer.close();
	}
}