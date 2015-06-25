package parsing;


/**
 * All pre-defined types.
 * 
 * @author Dennis
 *
 */
public enum Primitive implements Type {
	/**
	 * The <code>int</code> type.
	 */
	INT,
	/**
	 * The <code>bool</code> type.
	 */
	BOOL,
	/**
	 * The <code>void</code> pseudo-type.
	 */
	VOID(0),
	/**
	 * Type used when an acutal type can not be derived.
	 */
	ERR_TYPE("<err>", 0);

	private String name;
	int size;

	private Primitive() {
		this.name = this.name().toLowerCase();
		this.size = 1;
	}

	private Primitive(String name) {
		this.name = name;
		this.size = 1;
	}
	
	private Primitive(int size) {
		this.name = this.name().toLowerCase();
		this.size = size;
	}
	
	private Primitive(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public static Primitive forName(String name) {
		for (Primitive t : values())
			if (t.name.equalsIgnoreCase(name))
				return t;
		throw new IllegalArgumentException("Type '" + name
				+ "' does not exist!");
	}
	
	public int getSize() {
		return size;
	}

	public String toString() {
		return name;
	}
	
}
