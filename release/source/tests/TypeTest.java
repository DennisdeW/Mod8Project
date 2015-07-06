package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import parsing.Primitive;
import parsing.Type;
import parsing.Type.Array;
import parsing.Type.Pointer;

/**
 * Class TypeTest is used to test whether the workings of Type are correct.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class TypeTest {

	/**
	 * Tests basic workings of Type.
	 */
	@Test
	public void testBasic() {
		Type type = Type.forName("int");
		assertEquals("int", type.toString());
		type = Type.forName("Bool");
		assertEquals("bool", type.toString());
		type = Type.forName("vOiD");
		assertEquals("void", type.toString());
	}

	/**
	 * Tests the Pointer Type.
	 */
	@Test
	public void testPtr() {
		Pointer ptr;
		Type type = Type.forName("int*");
		assertInstance(Pointer.class, type);
		assertEquals("int*", type.toString());
		assertEquals("int", ((Pointer) type).getWrappedType().toString());

		type = Type.forName("BOOL**");
		assertInstance(Pointer.class, type);
		ptr = (Pointer) type;
		assertInstance(Pointer.class, ptr.getWrappedType());
		assertEquals("bool*", ptr.getWrappedType().toString());
		assertEquals("bool", ((Pointer) ptr.getWrappedType()).getWrappedType().toString());
	}

	/**
	 * Tests the Array Type.
	 */
	@Test
	public void testArray() {
		Array arr;
		Type type = Type.forName("int[]");
		assertInstance(Array.class, type);
		assertEquals("int[]", type.toString());
		arr = (Array) type;
		assertEquals("int", arr.getContainedType().toString());

		type = Type.forName("bool[][]");
		assertInstance(Array.class, type);
		arr = (Array) type;
		assertInstance(Array.class, arr.getContainedType());
		assertEquals("bool[]", arr.getContainedType().toString());
		assertEquals("bool", ((Array) arr.getContainedType()).getContainedType().toString());

		arr = (Array) Type.forName("int[]");
		arr.setCount(5);
		assertEquals(5, arr.getHeapSize());
		arr = (Array) Type.forName("void[]");
		arr.setCount(5);
		assertEquals(0, arr.getHeapSize());
	}

	/**
	 * Tests different Types in the same test (Array and Pointer).
	 */
	@Test
	public void testMixed() {
		Pointer ptr;
		Array arr;
		Type type = Type.forName("int*[]");
		assertInstance(Array.class, type);
		arr = (Array) type;
		assertInstance(Pointer.class, arr.getContainedType());
		ptr = (Pointer) arr.getContainedType();
		assertEquals("int", ptr.getWrappedType().toString());

		assertEquals(new Array(new Pointer(new Array(new Pointer(Primitive.BOOL)))), Type.forName("bool*[]*[]"));
	}

	/**
	 * Throws an exception if the class of value is not the same as clazz.
	 * 
	 * @param clazz
	 *            The Class you want to check.
	 * @param value
	 *            The Object on which this function is called.
	 */
	private void assertInstance(Class<?> clazz, Object value) {
		if (value.getClass() != clazz)
			throw new AssertionError(String.format("Expected type %s, but was %s", clazz.getCanonicalName(),
					value.getClass().getCanonicalName()));
	}
}