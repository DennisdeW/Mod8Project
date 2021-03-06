package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import parsing.Checker;
import parsing.Checker.CheckResult;

/**
 * Class CheckerTest is used to test the Checker class.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class CheckerTest {

	// Instance Variables
	private FileInputStream testCorrect;
	private FileInputStream testIncorrect;
	private Checker checker;

	/**
	 * init() initializes the instance variables.
	 */
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

	/**
	 * tearDown() is used to close the FileInputStreams testCorrect and
	 * testIncorrect.
	 */
	@After
	public void tearDown() {
		try {
			testCorrect.close();
			testIncorrect.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * testCorrect() tries to parse testTrue.tmp and checks whether it is parsed
	 * correctly.
	 */
	@Test
	public void testCorrect() {
		try {
			checker.check(new ANTLRInputStream(testCorrect));
			if (checker.hasErrors()) {
				System.out.println(checker.getErrors().stream()
						.collect(Collectors.joining("\n")));
				fail();
			}
			checker = new Checker();
		} catch (RecognitionException | IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * testIncorrect() tries to parse testFalse.tmp and checks whether it is
	 * parsed correctly.
	 */
	@Test
	public void testInCorrect() {
		try {
			checker.check(new ANTLRInputStream(testIncorrect));
			if (!checker.hasErrors()) {
				fail();
			}
			System.out.println(checker.getErrors().stream()
					.collect(Collectors.joining("\n")));
			checker = new Checker();
		} catch (RecognitionException | IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}