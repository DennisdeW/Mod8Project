package parsing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import parsing.Type.Func;

/**
 * Stores declarations of variables and functions.
 * 
 * @author Dennis
 *
 */
public class Scope {

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
	public boolean isDeclared(String id, Type type) {
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
	public boolean declare(String id, Type type) {
		return current.declare(id, type);
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
	public Type getType(String id) {
		return current.getType(id);
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
	public Map<String, Type> getVars() {
		return current.getVars();
	}

	/**
	 * The inner workings of {@link Scope}.
	 * 
	 * @author Dennis
	 *
	 */
	private class Level {
		private Map<String, Type> vars;
		private Level enclosing;

		Level(Level enclosing) {
			vars = new HashMap<>();
			this.enclosing = enclosing;
		}

		Level() {
			this(null);
		}

		Level open() {
			return new Level(this);
		}

		Level close() {
			if (isGlobal())
				throw new IllegalStateException("Tried to close global scope!");
			return enclosing;
		}

		boolean isGlobal() {
			return enclosing == null;
		}

		boolean declare(String id, Type type) {
			if (isDeclaredLocally(id))
				return false;
			this.vars.put(id, type);
			return true;
		}

		boolean isDeclared(String id) {
			return vars.containsKey(id)
					|| (!isGlobal() && enclosing.isDeclared(id));
		}

		boolean isDeclaredLocally(String id) {
			return vars.containsKey(id);
		}

		boolean isDeclared(String id, Type type) {
			return isDeclared(id) && getType(id) == type;
		}

		Type getType(String id) {
			if (isDeclaredLocally(id))
				return vars.get(id);
			else if (!isGlobal())
				return enclosing.getType(id);
			else
				throw new IllegalArgumentException("Var '" + id
						+ "' was not declared.");
		}

		Map<String, Type> getLocals() {
			return new HashMap<>(vars);
		}

		Map<String, Type> getVars() {
			Map<String, Type> res;
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
