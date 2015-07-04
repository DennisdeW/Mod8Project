package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import parsing.BaseGrammarLexer;
import parsing.BaseGrammarParser;
import parsing.BaseGrammarParser.ProgramContext;
import parsing.Checker;
import parsing.Checker.CheckResult;
import parsing.Generator;
import translation.Program;
import write.ProgramRunner;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestRunner {

	private boolean failed = false;
	
	@Test
	public void runGoodTests() {
		File testDir = new File("tests/good");
		for (File f : testDir.listFiles())
			test(f, true);
		assertFalse(failed);
	}

	@Test
	public void runBadTests() {
		File testDir = new File("tests/bad");
		for (File f : testDir.listFiles())
			test(f, false);
		assertFalse(failed);
	}

	
	private void test(File file, boolean shouldSucceed) {
		ProgramContext ctx;
		try {
			System.out.println("Parsing " + file.getName());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String base = reader.lines().collect(Collectors.joining("\n"));
			BufferedReader stdreader = new BufferedReader(new FileReader(
					new File("stdlib.txt")));
			String std = stdreader.lines().collect(Collectors.joining("\n"));

			ctx = new BaseGrammarParser(new CommonTokenStream(
					new BaseGrammarLexer(new ANTLRInputStream(base + "\n\n\n"
							+ std)))).program();
			reader.close();
			stdreader.close();
		} catch (RecognitionException | IOException e) {
			e.printStackTrace();
			fail("An exception occured while scanning/parsing.");
			return;
		}
		CheckResult cres = new Checker().check(ctx);
		if (!cres.getErrors().isEmpty()) {
			System.out.flush();
			System.err.println("Checker has reported errors:");
			for (String error : cres.getErrors())
				System.err.println(error);
			System.err.flush();
			if (shouldSucceed)
				failed = true;
		} else {
			Program prog = new Generator().compile(ctx, cres);
			try {
				if (ProgramRunner.runTest(prog)) {
					System.out.println("Testing of " + file.getName()
							+ " successful.");
					if (!shouldSucceed) {
						failed = true;
					}
				} else {
					System.out.println("Invalid response code, test failed.");
					if (shouldSucceed)
						failed = true;
				}
			} catch (Exception e) {
				System.out.println("Error occurred while compiling or running: " + e);
			}
		}
		System.out.println();
	}
}
