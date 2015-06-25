package tests;

import org.junit.Test;

import parsing.IType;
import parsing.Type.Pointer;
import static org.junit.Assert.*;

public class TypeTest {

	@Test
	public void testBasic() {
		IType type = IType.forName("int");
		assertEquals("int", type.toString());
		type = IType.forName("Bool");
		assertEquals("bool", type.toString());
		type = IType.forName("vOiD");
		assertEquals("void", type.toString());
		type = IType.forName("<err>");
		assertEquals("<err>", type.toString());
	}

	@Test
	public void testPtr() {
		Pointer ptr;
		IType type = IType.forName("int*");
		assertInstance(Pointer.class, type);
		assertEquals("int*", type.toString());
		assertEquals("int", ((Pointer) type).getWrappedType().toString());

		type = IType.forName("BOOL**");
		assertInstance(Pointer.class, type);
		ptr = (Pointer) type;
		assertInstance(Pointer.class, ptr.getWrappedType());
		assertEquals("bool*", ptr.getWrappedType().toString());
		assertEquals("bool", ((Pointer) ptr.getWrappedType()).getWrappedType()
				.toString());
	}

	private void assertInstance(Class<?> clazz, Object value) {
		if (value.getClass() != clazz)
			throw new AssertionError(String.format(
					"Expected type %s, but was %s", clazz.getCanonicalName(),
					value.getClass().getCanonicalName()));
	}
}
