package write;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import translation.Int;
import translation.OpCode;
import translation.Operator;
import translation.Program;
import translation.Register;
import translation.Spril;
import translation.Target;

public class OutputDebug {
	private static String fileName = "progDebug.hs";

	public static void write(String[] lines) {
		write(fileName, lines);
	}

	public static void write(String outputFile, String[] lines) {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(outputFile, "UTF-8");
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
		writer.println("debug SysState{..} | halted $ sprs!!0 = show val");
		writer.println("					where");
		writer.println("						(RegFile val) = regbank $ sprs!!0");
		writer.println("debug _ = \"\"");
		writer.println("main = runDebug debug 3 prog");
		writer.flush();
		writer.close();
	}

	public static void main(String[] args) {
		Program prog = new Program();
		int n = 7;
		/*
		 * Calculate (n-1)!
		 * 
		 * Const 1 RegA 
		 * Const n RegB 
		 * Const 1 RegC
		 * Compute Eq RegB RegC RegC 
		 * Branch (Rel 5) RegC 
		 * Const 1 RegC 
		 * Compute Sub RegB RegC RegB 
		 * Compute Mul RegA RegB RegA 
		 * Jump (Abs 2) 
		 * EndProg
		 */
		prog.addInstruction(new Spril(OpCode.CONST, new Int(1), Register.A));
		prog.addInstruction(new Spril(OpCode.CONST, new Int(n), Register.B));
		prog.addInstruction(new Spril(OpCode.CONST, new Int(1), Register.C));
		prog.addInstruction(new Spril(OpCode.COMPUTE, Operator.EQUAL, Register.B,
				Register.C, Register.C));
		prog.addInstruction(new Spril(OpCode.BRANCH, Register.C, Target
				.relative(5)));
		prog.addInstruction(new Spril(OpCode.CONST, new Int(1), Register.C));
		prog.addInstruction(new Spril(OpCode.COMPUTE, Operator.SUB, Register.B,
				Register.C, Register.B));
		prog.addInstruction(new Spril(OpCode.COMPUTE, Operator.MUL, Register.A,
				Register.B, Register.A));
		prog.addInstruction(new Spril(OpCode.JUMP, Target.absolute(2)));
		prog.addInstruction(new Spril(OpCode.END_PROG));
		ProgramRunner.run(prog);
	}
}