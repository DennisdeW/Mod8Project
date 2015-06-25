package parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * All pre-defined types.
 * 
 * @author Dennis
 *
 */
public enum Type implements IType {
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
	private int size;

	private Type() {
		this.name = this.name().toLowerCase();
		this.size = 1;
	}

	private Type(String name) {
		this.name = name;
		this.size = 1;
	}
	
	private Type(int size) {
		this.name = this.name().toLowerCase();
		this.size = size;
	}
	
	private Type(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public static Type forName(String name) {
		for (Type t : values())
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
	
	/**
	 * The definition of a function.
	 * 
	 * @author Dennis
	 *
	 */
	public static class Func {
		private final String name;
		private final List<IType> args;
		private final IType ret;

		/**
		 * @param name
		 *            The function's name.
		 * @param ret
		 *            The return type.
		 * @param args
		 *            The argument types (in order).
		 */
		public Func(String name, IType ret, List<IType> args) {
			this.name = name;
			this.ret = ret;
			this.args = new ArrayList<>(args);
		}

		/**
		 * @param name
		 *            The function's name.
		 * @param types
		 *            The first <code>Type</code> is the return type, any
		 *            further ones are argument types.
		 * @throws IllegalArgumentException
		 *             If no return type is supplied. (<code>types.length</code>
		 *             must be greater than 0)
		 */
		public Func(String name, IType... types) {
			if (types.length == 0)
				throw new IllegalArgumentException(
						"Varargs parameters to Type.Func#<init> may not be empty.");
			this.name = name;
			this.ret = types[0];
			Type[] args = new Type[types.length - 1];
			System.arraycopy(types, 1, args, 0, types.length - 1);
			this.args = Arrays.asList(args);
		}

		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((args == null) ? 0 : args.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((ret == null) ? 0 : ret.hashCode());
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
			Func other = (Func) obj;
			if (args == null) {
				if (other.args != null)
					return false;
			} else if (!args.equals(other.args))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (ret != other.ret)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Func [name=" + name + ", args=" + args + ", ret=" + ret
					+ "]";
		}

		/**
		 * Getter for the argument list.
		 * 
		 * @return The arguments.
		 */
		public List<IType> getArgs() {
			return args;
		}

		/**
		 * Getter for the return type.
		 * 
		 * @return The return type.
		 */
		public IType getReturnType() {
			return ret;
		}
		
		public String getReturnName() {
			return String.format("<<retval_%s(%s):%s>>", name, args, ret);
		}
				
		public String getName() {
			return name;
		}
	}

	public static class Pointer implements IType {
		
		public static final int POINTER_SIZE = INT.size;
		
		private final IType wrappedType;
		
		public Pointer(IType wrappedType) {
			this.wrappedType = wrappedType;
		}
		
		public int getValueSize() {
			return wrappedType.getSize();
		}
		
		public IType getWrappedType() {
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
}
