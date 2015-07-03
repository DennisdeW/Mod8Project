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

	@Test
	public void runTests() {
		File testDir = new File("tests");
		for (File f : testDir.listFiles())
			test(f);
	}

	private void test(File file) {
		ProgramContext ctx;
		try {
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
		Program prog = new Generator().compile(ctx, cres);
		assertTrue(ProgramRunner.runTest(prog));
	}
}
