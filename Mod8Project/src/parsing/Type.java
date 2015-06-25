package parsing;

public interface Type {

	public static class Pointer implements Type {

		public static final int POINTER_SIZE = Primitive.INT.size;

		private final Type wrappedType;

		public Pointer(Type wrappedType) {
			this.wrappedType = wrappedType;
		}

		public int getValueSize() {
			return wrappedType.getSize();
		}

		public Type getWrappedType() {
			return wrappedType;
		}

		@Override
		public int getSize() {
			return POINTER_SIZE;
		}

		@Override
		public String toString() {
			return wrappedType.toString() + '*';
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((wrappedType == null) ? 0 : wrappedType.hashCode());
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

	public static class Array implements Type {

		private final Type containedType;
		private int count;

		public Array(Type containedType, int size) {
			this.containedType = containedType;
			this.count = size;
		}

		public Array(Type containedType) {
			this(containedType, -1);
		}

		public int getHeapSize() {
			return containedType.getSize() * count;
		}

		@Override
		public int getSize() {
			return Pointer.POINTER_SIZE;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Type getContainedType() {
			return containedType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((containedType == null) ? 0 : containedType.hashCode());
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

	int getSize();

	@Override
	String toString();

	static Type forName(String name) {
		Type type = null;
		for (Primitive t : Primitive.values()) {
			if (t.toString().equalsIgnoreCase(
					name.replaceAll("[\\[\\]\\*]", "")))
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
