import java.lang.reflect.*;


public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        System.out.println();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	
    	if (c == null) {
    		return;
    	}
    	if (c.getName() == null) {
    		return;
    	}

    	//1) Name of Declaring Class
    	indent(depth);
    	System.out.println("Name of Declared Class: " + c.getName());
    	System.out.println();
    	
    	
    	
    	//2)
    	indent(depth);
    	System.out.println("~~~SuperClass Herarchy:~~~");
    	
    	if (c.equals(Object.class) || c.isPrimitive() || c.getInterfaces().length == 0) {
    		indent(depth);
    		System.out.println("Highest class, no SuperClass exist or Interface");
    	} else {
    		
        	superHierarchy(c, depth);
        	System.out.println();
        	
        	//3) Name of each interface the class implements
    		//Similar to Super-Class
        	indent(depth);
    		System.out.println("~~~Interface Hierarchy~~~:");
    		indent(depth+1);
    		System.out.println(c.getName());
    		Class[] superInterface = c.getInterfaces();
    		interfaceHierarchy(superInterface, depth+2);
    		System.out.println();
    	}
    	
    	
    	
    	//4)the constructors the class declares, FOR EACH
		indent(depth);
    	System.out.println("~~~Constructor~~~: ");
    	Constructor[] constr = c.getDeclaredConstructors();
    	for (int i =0; i < constr.length; i++) {
	    	//a) The name
    		indent(depth+1);
    		System.out.println("Name: " + constr[i].getName());
	    	//b) The Parameter types
    		indent(depth+2);
    		System.out.print("Parameters: ");
    		Parameter[] param = constr[i].getParameters();
    		if (param.length == 0) {
        		System.out.println("NONE");
        	} else {
	    		for (int o=0;o<param.length;o++) {
	    			System.out.print(param[o].getType().getName() + " ");
	    		}
	    		System.out.println();
        	}
    		
	    	//c)The modifiers
    		indent(depth+2);
    		System.out.println("Modifier: " + Modifier.toString(constr[i].getModifiers()));
    		System.out.println();
    	}
    	
    	
    	
    	
    	//5) The methods the class declares, for each, also find
	    	//a) The name
	    	//b) The Exceptions throw
	    	//c) The Parameter types
	    	//d) The return type
 	    	//e) The modifiers  	
    	indent(depth);
    	System.out.println("~~~Methods~~~:");
    	Method[] m;
    	//m = c.getMethods();			//All public Methods (Includes inherited)
    	m = c.getDeclaredMethods();		//All declared Methods (Not includes inherited
    	for (int i=0; i< m.length; i++) {
    		indent(depth+1);
    		System.out.println("Method Name: " + m[i].getName());
    		
    		//Exception Part
    		indent(depth+2);
    		System.out.print("Exceptions Thrown: ");
    		Class[] ExTypes = m[i].getExceptionTypes();
    		if (ExTypes.length == 0) {
    			System.out.println("NONE");
    		} else {
	    		for (int o=0; o<ExTypes.length; o++) {
	    			System.out.print(ExTypes[o].getTypeName() + " ");
	    		}
	    		System.out.println();
    		}
    		
    		
    		//Parameter Types
    		indent(depth+2);
    		System.out.print("Parameter Types: ");
    		Class[] PaTypes = m[i].getParameterTypes();
    		if (PaTypes.length == 0) {
    			System.out.println("NONE");
    		} else {
    			for (int o=0; o<PaTypes.length; o++) {
	    			System.out.print(PaTypes[o].getTypeName() + " ");
	    		}
	    		System.out.println();
    		}
    		
    		//Return Type
    		indent(depth+2);
    		System.out.print("Return Type: ");
    		Class ReType = m[i].getReturnType();
    		System.out.println(ReType);
    		
    		
    		//Modifiers
    		indent(depth+2);
    		System.out.print("Modifiers: ");
    		int MoTypes = m[i].getModifiers();	
    		System.out.println(Modifier.toString(MoTypes));
    		System.out.println();
    	}
    	
    	
    	
    	
    	
    	
    	//6) The fields the class declares
    	indent(depth);
    	System.out.println("~~~Fields~~~: ");
    	Field[] fields = c.getDeclaredFields();
    	for (int i=0; i < fields.length; i++) {
    		//a) The name
    		indent(depth+1);
    		System.out.println("Name: " + fields[i].getName());
    		
    		//b) The type
    		String fieldType = fields[i].getType().getName();
    		indent(depth+2);
    		System.out.println("Type: " + fieldType);
	    	//c) The modifiers
    		indent(depth+2);
    		System.out.println("Modifier: " + Modifier.toString(fields[i].getModifiers()));
	    	//d) The current value of each field
    		indent(depth+2);
    		System.out.print("Current Value: ");
    		
    		try {
    			fields[i].setAccessible(true);
	    		if (fieldType == "null") {
	    			System.out.println("null");
	    		} else if (fieldType == "boolean") {
					System.out.println(fields[i].getBoolean(obj));
	    		} else if (fieldType == "char") {
	    			System.out.println(fields[i].getChar(obj));
	    		} else if (fieldType == "double") {
	    			System.out.println(fields[i].getDouble(obj));
	    		} else if (fieldType == "float") {
	    			System.out.println((fields[i].getFloat(obj)));
	    		} else if (fieldType == "int") {
	    			System.out.println(fields[i].getInt(obj));
	    		} else if (fieldType == "long") {
	    			System.out.println(fields[i].getLong(obj));
	    		} else if (fieldType == "byte") {
	    			System.out.println(fields[i].getByte(obj));
	    		} else if (fieldType == "short") {
	    			System.out.println(fields[i].getShort(obj));
	    		} else {
	    			System.out.println(fields[i].getType().getName() + "@" + System.identityHashCode(fields[i]));
	    		}
    		} catch (IllegalArgumentException | IllegalAccessException e) {
				System.out.println(e);
			}
    		
    		
    		if (recursive == true) {
    			indent(depth);
    			System.out.println("~~~Recursively going through Field~~~");
    			inspectClass(fieldType.getClass(), obj, recursive, depth+1);
    		}
    		System.out.println();
    	}
    	System.out.println();
    	
    	if (!(c.equals(Object.class) || c.isPrimitive() || c.getInterfaces().length ==0)) {
    		System.out.println("\n");
    		indent(depth);
	    	System.out.println("~~~Going through superclasses~~~");
	    	inspectClass(c.getSuperclass(), obj, recursive, depth+1);
    	}
    	
    	
    	//Recursively go through interfaces
    	Class[] superInterface = c.getInterfaces();
    	if (superInterface.length != 0) {
    		inspectClass(superInterface[0], obj, recursive, depth+1);
    	}
    	
    }
    
    
    
    public static void superHierarchy(Class c, int depth) {
		 //2) Name of the immediate super-class
		//Maybe replace with running Inspection again
    	indent(depth+1);
		System.out.println(c.getName());
		Class superClass;
		superClass = c.getSuperclass();				//Retrieves the Super Class
		do {
			if (superClass == null) {													//If End of Herarchy
				indent(depth+2);														//Creates Indentation for Output
				System.out.println("Super-class is a primitive type, void, interface");
			} else {
				indent(depth+2);
				System.out.println(superClass.getName());
		    }
			try {
			Class superClassTemp = superClass.getSuperclass();
			superClass = superClassTemp;
			} catch (java.lang.NullPointerException e) {
				return;
			}
			
			depth++;
		} while (superClass != null);	
	
    }
    
    
    
    public static void interfaceHierarchy(Class[] c, int depth) {
    	for (int i=0; i<c.length;i++) {
    		indent(depth);
    		System.out.println(c[i].getName());
    		interfaceHierarchy(c[i].getInterfaces(), depth+1);
    	}
    	
    	
    }
    
    //Indents by the int given
    public static void indent(int i) {
    	for (int o =0; o < i; o++) {
    		System.out.print("    ");
    	}
    }
    
    
    

}