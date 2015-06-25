package parsing;

import parsing.Type.Pointer;

public interface IType {

	int getSize();
	
	@Override
	String toString();
	
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
