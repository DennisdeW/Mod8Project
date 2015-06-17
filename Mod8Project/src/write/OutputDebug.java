package write;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class OutputDebug {
	private static String fileName = "progDebug.hs";

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
		writer.println("debug :: SystemState -> String");
		writer.println("debug SysState{..} | (sharedMem !!! 0) == 5 = \"First shared memaddr equals 5.\\n\"");
		writer.println("debug _ = \"Not 5\\n\"\n");
		writer.println("main = runDebug debug 3 prog");
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) {
		String[] lines = new String[]{"Const 78 RegA", "Const 10 RegB", "Const 5 RegC", "Write RegA (Addr 0x1000000)", "Write RegB stdio", "Write RegC (Addr 0)"};
		write(lines);
	}
}