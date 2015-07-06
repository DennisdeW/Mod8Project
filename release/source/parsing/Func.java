package parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The definition of a function.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public class Func {
	private final String name;
	private final List<Type> args;
	private final Type ret;

	/**
	 * @param name
	 *            The function's name.
	 * @param ret
	 *            The return type.
	 * @param args
	 *            The argument types (in order).
	 */
	public Func(String name, Type ret, List<Type> args) {
		this.name = name;
		this.ret = ret;
		this.args = new ArrayList<>(args);
	}

	/**
	 * @param name
	 *            The function's name.
	 * @param types
	 *            The first <code>Type</code> is the return type, any further
	 *            ones are argument types.
	 * @throws IllegalArgumentException
	 *             If no return type is supplied. (<code>types.length</code>
	 *             must be greater than 0)
	 */
	public Func(String name, Type... types) {
		if (types.length == 0)
			throw new IllegalArgumentException("Varargs parameters to Type.Func#<init> may not be empty.");
		this.name = name;
		this.ret = types[0];
		Primitive[] args = new Primitive[types.length - 1];
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
		return "Func [name=" + name + ", args=" + args + ", ret=" + ret + "]";
	}

	/**
	 * Getter for the argument list.
	 * 
	 * @return The arguments.
	 */
	public List<Type> getArgs() {
		return args;
	}

	/**
	 * Getter for the return type.
	 * 
	 * @return The return type.
	 */
	public Type getReturnType() {
		return ret;
	}

	/**
	 * Returns the name, arguments and type of the program.
	 * 
	 * @return The name, arguments and type of the program.
	 */
	public String getReturnName() {
		return String.format("<<retval_%s(%s):%s>>", name, args, ret);
	}

	/**
	 * Getter for the name of the program.
	 * 
	 * @return The value of name.
	 */
	public String getName() {
		return name;
	}
}