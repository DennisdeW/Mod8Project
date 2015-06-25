package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import parsing.Scope;
import parsing.Type;
import parsing.Type.Func;

/**
 * Class ScopeTest is used to test the Scope class.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class ScopeTest {

	/**
	 * Does a test on a scope with a single layer.
	 */
	@Test
	public void testSingleLayer() {
		Scope scope = new Scope();
		scope.declare("a", Type.INT);
		scope.declare("b", Type.BOOL);
		assertTrue(scope.isDeclared("a"));
		assertTrue(scope.isDeclaredLocally("b"));
		assertTrue(scope.isDeclared("a", Type.INT));
		assertFalse(scope.isDeclared("b", Type.VOID));
		assertEquals(0, scope.getOffset("a"));
		assertEquals(1, scope.getOffset("b"));
		assertEquals(Type.INT, scope.getType("a"));
	}

	/**
	 * Does a test on a scope with multiple layers.
	 */
	@Test
	public void testMultiLayer() {
		Scope scope = new Scope();
		scope.declare("a", Type.INT);
		scope.openScope();
		assertTrue(scope.isDeclared("a"));
		assertFalse(scope.isDeclaredLocally("a"));
		scope.openScope();
		scope.declare("a", Type.BOOL);
		assertTrue(scope.isDeclaredLocally("a"));
		assertEquals(Type.BOOL, scope.getType("a"));
		assertEquals(1, scope.getOffset("a"));
		scope.closeScope();
		assertEquals(0, scope.getOffset("a"));
		assertEquals(Type.INT, scope.getType("a"));
		scope.closeScope();
		assertTrue(scope.isDeclaredLocally("a"));
		scope.declare("b", Type.BOOL);
		assertEquals(2, scope.getOffset("b"));
		try {
			scope.closeScope();
			fail();
		} catch (IllegalStateException e) {
			// expected - trying to close global scope
		}
	}

	/**
	 * Does a test on a scope with functions.
	 */
	@Test
	public void testFunctions() {
		Scope scope = new Scope();
		Func a = new Func("a", Type.INT, Type.INT, Type.INT);
		Func b = new Func("b", Type.VOID);
		Func c = new Func("c", Type.BOOL, Type.INT);
		assertTrue(scope.declare(a));
		assertTrue(scope.isDeclared(a));
		scope.openScope();
		assertTrue(scope.declare(b));
		assertFalse(scope.declare(a));
		scope.closeScope();
		assertTrue(scope.isDeclared(b));
		assertFalse(scope.isDeclared(c));
		assertTrue(scope.declare(c));
		assertTrue(scope.isDeclared(new Func("c", Type.BOOL, Type.INT)));
		assertFalse(scope.isDeclared(new Func("a", Type.VOID, Type.INT,
				Type.INT)));
	}

	/**
	 * Does a test on a scope with global variables.
	 */
	@Test
	public void testShared() {
		Scope scope = new Scope();
		scope.declare("a", Type.BOOL, true);
		scope.declare("b", Type.INT, false);
		assertEquals(0, scope.getOffset("a"));
		assertEquals(0, scope.getOffset("b"));
		assertTrue(scope.isShared("a"));
		assertFalse(scope.isShared("b"));
		scope.openScope();
		scope.declare("a", Type.INT, true);
		assertEquals(1, scope.getOffset("a"));
		scope.openScope();
		scope.declare("a", Type.BOOL, false);
		assertEquals(1, scope.getOffset("a"));
	}
}