package write;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Output {
	private static String fileName = "prog.hs";

	public static void write(String[] lines) {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(fileName, "UTF-8");
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
		writer.println("main = run 1 prog");
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) {
		String[] lines = new String[]{"Const 6 RegA", "Const 7 RegB", "Compute Mul RegA RegB RegC"};
		write(lines);
	}
}
