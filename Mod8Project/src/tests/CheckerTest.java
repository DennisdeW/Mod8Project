package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import parsing.BaseGrammarLexer;
import parsing.BaseGrammarParser;
import parsing.BaseGrammarParser.ProgramContext;
import parsing.Checker;

public class CheckerTest {
	private FileInputStream testCorrect;
	private FileInputStream testIncorrect;
	private Checker checker;

	@Before
	public void init() {
		try {
			testCorrect = new FileInputStream(new File(
					"src/sample/testTrue.tmp"));
			testIncorrect = new FileInputStream(new File(
					"src/sample/testFalse.tmp"));
			checker = new Checker();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		try {
			testCorrect.close();
			testIncorrect.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCorrect() {
		try {
			checker.check(new ANTLRInputStream(testCorrect));
			if (checker.hasErrors()) {
				System.out.println(checker.getErrors().stream().collect(Collectors.joining("\n")));
				fail();
			}
			checker = new Checker();
		} catch (RecognitionException | IOException e) {
			e.printStackTrace();
			fail();

		}
	}

	@Test
	public void testInCorrect() {
		try {
			checker.check(new ANTLRInputStream(testIncorrect));
			if (!checker.hasErrors()) {
				fail();
			}
			System.out.println(checker.getErrors().stream().collect(Collectors.joining("\n")));
			checker = new Checker();
		} catch (RecognitionException | IOException e) {
			e.printStackTrace();
			fail();

		}
	}
}