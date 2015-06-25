package parsing;

import parsing.Type.Pointer;

/**
 * Class IType defines a type for a variable.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public interface IType {

	// Instance variables
	int getSize();

	@Override
	String toString();

	/**
	 * Returns the type of a certain variable.
	 * 
	 * @param name
	 *            The name of the variable.
	 * @return The type of a certain variable.
	 */
	static IType forName(String name) {
		int pointerDepth = 0;
		while (name.endsWith("*")) {
			pointerDepth++;
			name = name.substring(0, name.length() - 1);
		}

		Type baseType = null;
		for (Type t : Type.values()) {
			if (t.toString().equalsIgnoreCase(name))
				baseType = t;
		}
		if (baseType == null)
			throw new IllegalArgumentException(name + " is not a valid type!");

		if (pointerDepth > 0) {
			Pointer ptr = new Pointer(baseType);
			pointerDepth--;
			while (pointerDepth-- > 0)
				ptr = new Pointer(ptr);
			return ptr;
		} else {
			return baseType;
		}
	}
}
