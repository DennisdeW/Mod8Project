package parsing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import parsing.Type.Func;

/**
 * Stores declarations of variables and functions.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
 */
public class Scope {
	// Instance variables
	private Set<Func> functions;
	private Level current;

	/**
	 * Construct a new, empty scope
	 */
	public Scope() {
		current = new Level();
		functions = new HashSet<>();
	}

	/**
	 * Open a new scope. The new scope will retain all declared variables, but
	 * may override them.
	 */
	public void openScope() {
		current = current.open();
	}

	/**
	 * Close the current scope and return to the previous one.
	 * 
	 * @throws IllegalStateException
	 *             If the current scope is the global scope.
	 */
	public void closeScope() {
		current = current.close();
	}

	/**
	 * Checks if a variable with the given id is declared in <i>any</i>
	 * accessible scope. This method differs from
	 * {@link Scope#isDeclaredLocally(String)} in that it checks all available
	 * scopes.
	 * 
	 * @param id
	 *            The id of the variable to look for.
	 * @return <code>true</code> iff a variable called <code>id</code> is
	 *         declared in the current scope, or in any enclosing scope.
	 */
	public boolean isDeclared(String id) {
		return current.isDeclared(id);
	}

	/**
	 * Checks if a variable with the given id is declared in <i>any</i>
	 * accessible scope, and if its type matches <code>type</code>.
	 * 
	 * @param id
	 *            The id of the variable to look for.
	 * @param type
	 *            The type to check against.
	 * @return <code>true</code> iff a variable called <code>id</code> is
	 *         declared and its type matches <code>type</code>.
	 */
	public boolean isDeclared(String id, IType type) {
		return current.isDeclared(id, type);
	}

	/**
	 * Checks if the given function is declared. Note that functions are always
	 * declared globally.
	 * 
	 * @param func
	 *            The function to check for.
	 * @return <code>true</code> iff <code>func</code> is declared.
	 */
	public boolean isDeclared(Func func) {
		return functions.contains(func);
	}

	/**
	 * Checks if a variable with the given id is declared within the current
	 * scope. <br>
	 * This method differs from {@link Scope#isDeclared(String)} in that it only
	 * checks the current scope.
	 * 
	 * @param id
	 *            The id of the variable to look for.
	 * @return <code>true</code> iff a variable called <code>id</code> is
	 *         declared in the current scope.
	 */
	public boolean isDeclaredLocally(String id) {
		return current.isDeclaredLocally(id);
	}

	/**
	 * Declares a new variable in the current scope.
	 * 
	 * @param id
	 *            The name of the variable.
	 * @param type
	 *            The type of the variable.
	 * @return <code>true</code> iff the variable was not already declared.
	 */
	public boolean declare(String id, IType type) {
		return current.declare(id, type);
	}

	/**
	 * Declares a new variable in the current scope.
	 * 
	 * @param id
	 *            The name of the variable.
	 * @param type
	 *            The type of the variable.
	 * @param shared
	 *            Whether the boolean is shared or not.
	 * @return <code>true</code> iff the variable was not already declared.
	 */
	public boolean declare(String id, IType type, boolean shared) {
		return current.declare(id, type, shared);
	}

	/**
	 * Declares a function.
	 * 
	 * @param func
	 *            The function to declare.
	 * @return <code>true</code> iff the function was not already declared.
	 */
	public boolean declare(Func func) {
		return functions.add(func);
	}

	/**
	 * Gets the type of the variable with the given name.
	 * 
	 * @param id
	 *            The name of the variable to get the type of.
	 * @return The type of the variable.
	 * @throws IllegalArgumentException
	 *             If no variable with this name has been declared.
	 */
	public IType getType(String id) {
		return current.getType(id);
	}

	/**
	 * Gets the offset of the variable with the given name.
	 * 
	 * @param id
	 *            The name of the variable to get the type of.
	 * @return The offset of the variable.
	 * @throws IllegalArgumentException
	 *             If no variable with this name has been declared.
	 */
	public int getOffset(String id) {
		return current.getOffset(id);
	}

	/**
	 * Gets all declared functions.
	 * 
	 * @return The set of declared functions.
	 */
	public Set<Func> getFunctions() {
		return new HashSet<>(functions);
	}

	/**
	 * Gets all declared variables.
	 * 
	 * @return The map of all variables.
	 */
	public Map<String, IType> getVars() {
		return current.getVars();
	}

	/**
	 * Returns whether a variable is shared or not.
	 * 
	 * @param id
	 *            the name of the variable.
	 * @return Whether the variable is shared or not.
	 */
	public boolean isShared(String id) {
		return current.isShared(id);
	}

	/**
	 * The inner workings of {@link Scope}.
	 * 
	 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321)
	 *
	 */
	private class Level {

		// Instance variables
		private Map<String, IType> vars;
		private Map<String, Integer> offsets;
		private Map<String, Boolean> shared;
		private Map<String, Integer> sharedOffsets;
		private final int baseOffset, sharedOffset;
		private final Level enclosing;
		private int nextOffset, nextShared;

		/**
		 * Constructor Level sets a new Level.
		 * 
		 * @param enclosing
		 *            A Level with certain values.
		 */
		Level(Level enclosing) {
			this.vars = new HashMap<>();
			this.offsets = new HashMap<>();
			this.shared = new HashMap<>();
			this.sharedOffsets = new HashMap<>();
			this.enclosing = enclosing;
			this.baseOffset = enclosing == null ? 0 : enclosing.getOffset();
			this.sharedOffset = enclosing == null ? 0 : enclosing
					.getSharedOffset();
			this.nextOffset = this.baseOffset;
			this.nextShared = this.sharedOffset;
		}

		/**
		 * Constructor Level
		 */
		Level() {
			this(null);
		}

		/**
		 * Returns this level of the scope.
		 * 
		 * @return This level.
		 */
		Level open() {
			return new Level(this);
		}

		/**
		 * Tries to close a Level, then returns the higher level.
		 * 
		 * @return Returns a higher level of the scope.
		 */
		Level close() {
			if (isGlobal())
				throw new IllegalStateException("Tried to close global scope!");
			return enclosing;
		}

		/**
		 * Returns the offset of a local variable.
		 * 
		 * @return The offset of a local variable.
		 */
		private int getOffset() {
			return nextOffset;
		}

		/**
		 * Returns the offset of a global variable.
		 * 
		 * @return The offset of a global variable.
		 */
		private int getSharedOffset() {
			return nextShared;
		}

		/**
		 * Returns whether a variable is global or not.
		 * 
		 * @return Whether a variable is global or not.
		 */
		boolean isGlobal() {
			return enclosing == null;
		}

		/**
		 * Declares a local variable.
		 * 
		 * @param id
		 *            The name of the variable.
		 * @param type
		 *            The type of the variable.
		 * @return Whether a variable is declared correctly or not.
		 */
		boolean declare(String id, IType type) {
			return declare(id, type, false);
		}

		/**
		 * Declares a variable.
		 * 
		 * @param id
		 *            The name of the variable.
		 * @param type
		 *            The type of the variable.
		 * @param shared
		 *            Whether a variable is global or not.
		 * @return Whether a variable is declared correctly or not.
		 */
		boolean declare(String id, IType type, boolean shared) {
			if (isDeclaredLocally(id))
				return false;
			this.vars.put(id, type);
			this.shared.put(id, shared);
			if (shared) {
				this.sharedOffsets.put(id, nextShared);
			} else {
				this.offsets.put(id, nextOffset);
			}
			bubble(shared, type.getSize());
			return true;
		}

		/**
		 * Bubble goes to a new Memaddr, where the next variable should be
		 * declared. It also does the same with every overlaying scope.
		 * 
		 * @param shared
		 *            Whether a variable is global or not.
		 * @param size
		 *            The size of the newly declared variable.
		 */
		private void bubble(boolean shared, int size) {
			if (shared)
				nextShared += size;
			else
				nextOffset += size;
			if (!isGlobal())
				enclosing.bubble(shared, size);
		}

		/**
		 * Returns whether a variable was declared already or not.
		 * 
		 * @param id
		 *            The name of the variable.
		 * @return Whether a variable was declared already or not.
		 */
		boolean isDeclared(String id) {
			return vars.containsKey(id)
					|| (!isGlobal() && enclosing.isDeclared(id));
		}

		/**
		 * Returns whether a variable was declared locally or not.
		 * 
		 * @param id
		 *            The name of the variable.
		 * @return Whether a variable was declared already or not.
		 */
		boolean isDeclaredLocally(String id) {
			return vars.containsKey(id);
		}

		/**
		 * Returns whether a variable of the same type was declared already or
		 * not.
		 * 
		 * @param id
		 *            The name of the variable.
		 * @param type
		 *            The type of the variable.
		 * @return Whether a variable of the same type was declared already or
		 *         not.
		 */
		boolean isDeclared(String id, IType type) {
			return isDeclared(id) && getType(id) == type;
		}

		/**
		 * Returns whether a variable is global or not.
		 * 
		 * @param id
		 *            The name of the variable.
		 * @return Whether a variable is global or not.
		 */
		boolean isShared(String id) {
			if (!isDeclared(id)) {
				throw new IllegalArgumentException("Var '" + id
						+ "' was not declared.");
			}
			if (isDeclaredLocally(id))
				return shared.get(id);
			else
				return enclosing.isShared(id);
		}

		/**
		 * Returns the type of a variable.
		 * 
		 * @param id
		 *            The name of the variable.
		 * @return The type of a variable.
		 */
		IType getType(String id) {
			if (isDeclaredLocally(id))
				return vars.get(id);
			else if (!isGlobal())
				return enclosing.getType(id);
			else
				throw new IllegalArgumentException("Var '" + id
						+ "' was not declared.");
		}

		/**
		 * Returns the offset of a variable.
		 * 
		 * @param id
		 *            The name of the variable.
		 * @return The offset of a variable.
		 */
		int getOffset(String id) {
			if (isDeclaredLocally(id))
				return isShared(id) ? sharedOffsets.get(id) : offsets.get(id);
			else if (!isGlobal())
				return enclosing.getOffset(id);
			else
				throw new IllegalArgumentException("Var '" + id
						+ "' was not declared.");
		}

		/**
		 * Returns the list with local variables, declared in this level.
		 * 
		 * @return HashMap containing local variables.
		 */
		Map<String, IType> getLocals() {
			return new HashMap<>(vars);
		}

		/**
		 * Returns the list with variables declared in this level and above.
		 * 
		 * @return HashMap containing all accessible variables in this level.
		 */
		Map<String, IType> getVars() {
			Map<String, IType> res;
			if (!isGlobal()) {
				res = enclosing.getVars();
				res.putAll(vars);
			} else {
				res = getLocals();
			}
			return res;
		}
	}
}