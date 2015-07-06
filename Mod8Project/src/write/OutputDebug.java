package write;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import translation.Int;
import translation.MemAddr;
import translation.OpCode;
import translation.Program;
import translation.Register;
import translation.Spril;
import translation.Target;

/**
 * Creates a haskell file with debug functionalities.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class OutputDebug {

	// Instance variables
	private static String fileName = "progDebug.hs";

	/**
	 * Method write writes a String[] with Spril instructions to a file.
	 * 
	 * @param lines
	 *            The String[] with Spril instructions.
	 */
	public static void write(String[] lines) {
		write(fileName, 1, lines);
	}

	/**
	 * Method writes creates a haskell file.
	 * 
	 * @param title
	 *            The title of the haskell file.
	 * @param cores
	 *            The amount of cores on which the program needs to be run.
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
		writer.println("debug :: SystemState -> String");
		writer.println(
				"debug SysState{..} | all halted sprs = (show val) ++ \"\\n\" ++ (show mem) ++ \"\\n\" ++ (show smem)");
		writer.println("				 	| cycleCount `mod` 1 == 0 = \"Cycle \" ++ (show cycleCount) ++ \": \""
				+ " ++ (show val) ++ \"\\n\" ++ (show mem) ++ \"\\n\" ++ (show smem) ++ \"\\n\"");
		writer.println("					where");
		writer.println("						(RegFile val) = regbank $ sprs!!0");
		writer.println("						(Memory mem) = localMem $ sprs!!0");
		writer.println("						(Memory smem) = sharedMem");
		writer.println("debug _ = \"\"");
		writer.println("main = runDebug debug " + cores + " prog");
		writer.flush();
		writer.close();
	}

	/**
	 * The main-method, to create a new Program.
	 * 
	 * @param args
	 *            Instructions.
	 */
	public static void main(String[] args) {
		Program prog = new Program();
		int n = 7;
		/*
		 * Calculate (n-1)!
		 * 
		 * Const 1 RegA Const n RegB Const 1 RegC Compute Eq RegB RegC RegC
		 * Branch (Rel 5) RegC Const 1 RegC Compute Sub RegB RegC RegB Compute
		 * Mul RegA RegB RegA Jump (Abs 2) EndProg
		 */
		prog.addInstruction(new Spril(OpCode.CONST, new Int(0), Register.B));
		prog.addInstruction(new Spril(OpCode.WRITE, Register.B, MemAddr.direct(0)));
		prog.addInstruction(new Spril(OpCode.TEST_AND_SET, MemAddr.direct(0)));
		prog.addInstruction(new Spril(OpCode.RECEIVE, Register.A));
		prog.addInstruction(new Spril(OpCode.BRANCH, Register.A, Target.relative(2)));
		prog.addInstruction(new Spril(OpCode.JUMP, Target.relative(-3)));
		prog.addInstruction(new Spril(OpCode.END_PROG));
		ProgramRunner.run(prog);
	}
}