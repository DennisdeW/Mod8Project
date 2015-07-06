package parsing;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface Type, the interface for types of variables that are used in the
 * program.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public interface Type {

	/**
	 * Inner class Pointer, implements type. It defines a pointer.
	 * 
	 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
	 */
	public static class Pointer implements Type {

		// Instance variables
		public static final int POINTER_SIZE = Primitive.INT.size;
		private final Type wrappedType;

		/**
		 * Constructor Pointer, used to set certain instance variables.
		 * 
		 * @param wrappedType
		 *            The new value of wrappedType.
		 */
		public Pointer(Type wrappedType) {
			this.wrappedType = wrappedType;
		}

		/**
		 * Getter for the size of wrappedType.
		 * 
		 * @return The size of wrappedType.
		 */
		public int getValueSize() {
			return wrappedType.getSize();
		}

		/**
		 * The value of wrappedType.
		 * 
		 * @return The value of wrappedType.
		 */
		public Type getWrappedType() {
			return wrappedType;
		}

		@Override
		public int getSize() {
			return POINTER_SIZE;
		}

		/**
		 * Tries to create a Pointer with a base and length.
		 * 
		 * @param base
		 *            The Type of the new Pointer.
		 * @param length
		 *            The length of the new Pointer.
		 * @return The new Pointer.
		 */
		public static Pointer pointerChain(Type base, int length) {
			if (length <= 0)
				throw new IllegalArgumentException();
			Type current = base;
			for (int i = 0; i < length; i++)
				current = new Pointer(current);
			return (Pointer) current;
		}

		@Override
		public String toString() {
			return wrappedType.toString() + '*';
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((wrappedType == null) ? 0 : wrappedType.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pointer other = (Pointer) obj;
			if (wrappedType == null) {
				if (other.wrappedType != null)
					return false;
			} else if (!wrappedType.equals(other.wrappedType))
				return false;
			return true;
		}
	}

	/**
	 * Inner class Array defines an array.
	 * 
	 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
	 */
	public static class Array implements Type {

		// Instance variables
		private final Type containedType;
		private int count;

		/**
		 * Constructor for Array.
		 * 
		 * @param containedType
		 *            The new value of containedType.
		 * @param size
		 *            The new value of count.
		 */
		public Array(Type containedType, int size) {
			this.containedType = containedType;
			this.count = size;
		}

		/**
		 * Constructor for Array.
		 * 
		 * @param containedType
		 *            The new value of containedType.
		 */
		public Array(Type containedType) {
			this(containedType, -1);
		}

		/**
		 * Returns the size of containedType * count.
		 * 
		 * @return The heapsize.
		 */
		public int getHeapSize() {
			return containedType.getSize() * count;
		}

		@Override
		public int getSize() {
			return Pointer.POINTER_SIZE;
		}

		/**
		 * Getter for count.
		 * 
		 * @return The value of count.
		 */
		public int getCount() {
			return count;
		}

		/**
		 * Setter for count.
		 * 
		 * @param count
		 *            The new value of count.
		 */
		public void setCount(int count) {
			this.count = count;
		}

		/**
		 * Getter for containedType.
		 * 
		 * @return The value of containedType.
		 */
		public Type getContainedType() {
			return containedType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((containedType == null) ? 0 : containedType.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Array other = (Array) obj;
			if (containedType == null) {
				if (other.containedType != null)
					return false;
			} else if (!containedType.equals(other.containedType))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return containedType.toString() + "[]";
		}

	}

	/**
	 * Inner class Enum defines Enumeration as a type.
	 *
	 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
	 */
	public static class Enum implements Type {

		// Instance variables
		public static final Enum DUMMY = new Enum("", new ArrayList<>());

		private final List<String> values;
		private final String name;

		/**
		 * Constructor for Enum.
		 * 
		 * @param name
		 *            The new value of name.
		 * @param values
		 *            The new value of values.
		 */
		public Enum(String name, List<String> values) {
			this.values = values;
			this.name = name;
		}

		/**
		 * Getter for name.
		 * 
		 * @return The value of name.
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns the index of a certain value in values.
		 * 
		 * @param value
		 *            The value you want to find.
		 * @return The index of a certain value.
		 */
		public int ordinal(String value) {
			return values.indexOf(value);
		}

		/**
		 * Getter for values.
		 * 
		 * @return The value of values.
		 */
		public List<String> getValues() {
			return new ArrayList<>(values);
		}

		@Override
		public int getSize() {
			return Primitive.INT.size;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Enum other = (Enum) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return name + "(enum)";
		}
	}

	// Instance variable
	int getSize();

	@Override
	String toString();

	static Primitive baseType(Type type) {
		if (type instanceof Primitive)
			return (Primitive) type;
		if (type instanceof Array)
			return baseType(((Array) type).getContainedType());
		if (type instanceof Pointer)
			return baseType(((Pointer) type).getWrappedType());
		throw new IllegalArgumentException();
	}

	/**
	 * Returns the type belonging to a certain string.
	 * 
	 * @param name
	 *            The string which you want the type of.
	 * @return The type corresponding to a certain string.
	 */
	static Type forName(String name) {
		if (name.matches("[A-Z].*")) {
			return Enum.DUMMY;
		}
		Type type = null;
		for (Primitive t : Primitive.values()) {
			if (t.toString().equalsIgnoreCase(name.replaceAll("[\\[\\]\\*]", "")))
				type = t;
		}
		if (type == null)
			throw new IllegalArgumentException(name + " is not a valid type!");

		String decorations = name.replaceAll("\\w", "");

		while (decorations.length() > 0) {
			if (decorations.startsWith("*")) {
				type = new Pointer(type);
				decorations = decorations.substring(1);
			} else if (decorations.startsWith("[]")) {
				type = new Array(type);
				decorations = decorations.substring(2);
			}
		}
		return type;
	}
}