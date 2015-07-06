package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Test;

import parsing.BaseGrammarLexer;
import parsing.BaseGrammarParser;
import parsing.BaseGrammarParser.ProgramContext;
import parsing.Checker;
import parsing.Checker.CheckResult;
import parsing.Generator;
import translation.Program;
import write.ProgramRunner;

/**
 * Runs all the test inside the test package.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class TestRunner {

	// Instance variables
	private boolean failed = false;

	/**
	 * Runs all the tests in package good.
	 */
	@Test
	public void runGoodTests() {
		File testDir = new File("tests/good");
		for (File f : testDir.listFiles())
			test(f, true);
		assertFalse(failed);
	}

	/**
	 * Runs all the tests in package bad.
	 */
	@Test
	public void runBadTests() {
		File testDir = new File("tests/bad");
		for (File f : testDir.listFiles())
			test(f, false);
		assertFalse(failed);
	}

	/**
	 * Auxiliary method to test a program.
	 * 
	 * @param file
	 *            The file.
	 * @param shouldSucceed
	 *            Whether the test should succeed or not.
	 */
	private void test(File file, boolean shouldSucceed) {
		ProgramContext ctx;
		try {
			System.out.println("Parsing " + file.getName());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String base = reader.lines().collect(Collectors.joining("\n"));
			BufferedReader stdreader = new BufferedReader(new FileReader(new File("stdlib.txt")));
			String std = stdreader.lines().collect(Collectors.joining("\n"));

			BaseGrammarParser parser = new BaseGrammarParser(
					new CommonTokenStream(new BaseGrammarLexer(new ANTLRInputStream(base + "\n\n\n" + std))));
			parser.setErrorHandler(new BailErrorStrategy());
			ctx = parser.program();
			reader.close();
			stdreader.close();
		} catch (RecognitionException | IOException e) {
			e.printStackTrace();
			fail("An exception occured while scanning/parsing.");
			return;
		} catch (ParseCancellationException e) {
			System.err.flush();
			System.out.flush();
			System.out.println("ANTLR reported errors, test failed.");
			System.out.flush();
			if (shouldSucceed)
				failed = true;
			return;
		}
		Checker checker = new Checker();
		CheckResult cres = checker.check(ctx);
		if (checker.hasErrors() || checker.isDirty()) {
			System.out.println("Checker has reported errors:");
			for (String error : checker.getErrors())
				System.out.println(error);
			if (shouldSucceed)
				failed = true;
		} else {
			Program prog = new Generator().compile(ctx, cres);
			try {
				if (ProgramRunner.runTest(prog)) {
					System.out.println("Testing of " + file.getName() + " successful.");
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