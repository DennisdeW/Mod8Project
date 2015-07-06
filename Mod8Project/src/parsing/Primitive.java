package parsing;

/**
 * All pre-defined types.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public enum Primitive implements Type {
	/**
	 * The <code>int</code> type.
	 */
	INT, /**
			 * The <code>bool</code> type.
			 */
	BOOL, /**
			 * The <code>void</code> pseudo-type.
			 */
	VOID(0), /**
				 * Type used when an acutal type can not be derived.
				 */
	ERR_TYPE("<err>", 0);

	// Instance variables
	private String name;
	int size;

	/**
	 * Constructor for Primitive. Sets the values of the instance variables.
	 */
	private Primitive() {
		this.name = this.name().toLowerCase();
		this.size = 1;
	}

	/**
	 * Constructor for Primitive. Sets the values of the instance variables.
	 * 
	 * @param name
	 *            The new value of name.
	 */
	private Primitive(String name) {
		this.name = name;
		this.size = 1;
	}

	/**
	 * Constructor for Primitive. Sets the values of the instance variables.
	 * 
	 * @param size
	 *            The new value of int.
	 */
	private Primitive(int size) {
		this.name = this.name().toLowerCase();
		this.size = size;
	}

	/**
	 * Constructor for Primitive. Sets the values of the instance variables.
	 * 
	 * @param name
	 *            The new value of name.
	 * @param size
	 *            The new value of int.
	 */
	private Primitive(String name, int size) {
		this.name = name;
		this.size = size;
	}

	/**
	 * Tries to find a certain Primitive.
	 * 
	 * @param name
	 *            The name of the Primitive you want to find.
	 * @return The Primitive you try to find.
	 */
	public static Primitive forName(String name) {
		for (Primitive t : values())
			if (t.name.equalsIgnoreCase(name))
				return t;
		throw new IllegalArgumentException("Type '" + name + "' does not exist!");
	}

	/**
	 * Getter for size.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the name of Primitive.
	 */
	public String toString() {
		return name;
	}
}