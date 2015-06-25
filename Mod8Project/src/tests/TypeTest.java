package tests;

import org.junit.Test;

import parsing.Primitive;
import parsing.Type;
import parsing.Type.Array;
import parsing.Type.Pointer;
import static org.junit.Assert.*;

public class TypeTest {

	@Test
	public void testBasic() {
		Type type = Type.forName("int");
		assertEquals("int", type.toString());
		type = Type.forName("Bool");
		assertEquals("bool", type.toString());
		type = Type.forName("vOiD");
		assertEquals("void", type.toString());
	}

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
		assertEquals("bool", ((Pointer) ptr.getWrappedType())
				.getWrappedType().toString());
	}

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
		assertEquals("bool", ((Array) arr.getContainedType())
				.getContainedType().toString());
		
		arr = (Array) Type.forName("int[]");
		arr.setCount(5);
		assertEquals(5, arr.getHeapSize());
		arr = (Array) Type.forName("void[]");
		arr.setCount(5);
		assertEquals(0, arr.getHeapSize());
	}
	
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

	private void assertInstance(Class<?> clazz, Object value) {
		if (value.getClass() != clazz)
			throw new AssertionError(String.format(
					"Expected type %s, but was %s", clazz.getCanonicalName(),
					value.getClass().getCanonicalName()));
	}
}
