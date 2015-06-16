package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

public class CheckerTest {
	File testCorrect = new File("src/sample/testTrue.tmp");
	File testInCorrect = new File("src/sample/testFalse.tmp");
	
	@Test
	public void testCorrect() {
	}
	
	@Test
	public void testInCorrect() {
		
	}
}