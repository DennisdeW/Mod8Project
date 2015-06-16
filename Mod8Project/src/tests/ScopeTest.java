package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import parsing.Scope;
import parsing.Type;
import parsing.Type.Func;

public class ScopeTest {

	@Test
	public void testSingleLayer() {
		Scope scope = new Scope();
		scope.declare("a", Type.INT);
		scope.declare("b", Type.BOOL);
		assertTrue(scope.isDeclared("a"));
		assertTrue(scope.isDeclaredLocally("b"));
		assertTrue(scope.isDeclared("a", Type.INT));
		assertFalse(scope.isDeclared("b", Type.VOID));
		assertEquals(Type.INT, scope.getType("a"));
	}
	
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
		scope.closeScope();
		assertEquals(Type.INT, scope.getType("a"));
		scope.closeScope();
		assertTrue(scope.isDeclaredLocally("a"));
		try {
			scope.closeScope();
			fail();
		} catch (IllegalStateException e) {
			//expected - trying to close global scope
		}
	}
	
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
		assertFalse(scope.isDeclared(new Func("a", Type.VOID, Type.INT, Type.INT)));
	}
	
}