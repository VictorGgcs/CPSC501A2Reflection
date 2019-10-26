import java.lang.reflect.*;


public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	
    	//1) Name of Declaring Class
    	System.out.println("\nName of Declared Class: " + c.getName());
    	System.out.println();
    	
    	//2)
    	System.out.println("~~~SuperClass Herarchy:~~~");
    	superHierarchy(c);
    	System.out.println();
    	
    	//3) Name of each interface the class implements
		//Similar to Super-Class
		System.out.println("~~~Interface Hierarchy~~~:");
		System.out.println(c.getName());
		int interCount = 1;
		Class[] superInterface = c.getInterfaces();
		interfaceHierarchy(superInterface, interCount);
		System.out.println();
    	
    	
    	
    	//4)the constructors the class declares, FOR EACH
    	System.out.println("~~~Constructor~~~: ");
    	Constructor[] constr = c.getDeclaredConstructors();
    	for (int i =0; i < constr.length; i++) {
	    	//a) The name
    		System.out.println("Name: " + constr[i].getName());
	    	//b) The Parameter types
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
    		System.out.println("Modifier: " + Modifier.toString(constr[i].getModifiers()));
    		System.out.println();
    	}
    	
    	
    	
    	
    	//5) The methods the class declares, for each, also find
	    	//a) The name
	    	//b) The Exceptions throw
	    	//c) The Parameter types
	    	//d) The return type
 	    	//e) The modifiers  	
    	System.out.println("~~~Methods~~~:");
    	Method[] m;
    	//m = c.getMethods();			//All public Methods (Includes inherited)
    	m = c.getDeclaredMethods();		//All declared Methods (Not includes inherited
    	for (int i=0; i< m.length; i++) {
    		
    		System.out.println("Method Name: " + m[i].getName());
    		
    		//Exception Part
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
    		System.out.print("Return Type: ");
    		Class ReType = m[i].getReturnType();
    		System.out.println(ReType);
    		
    		
    		//Modifiers
    		System.out.print("Modifiers: ");
    		int MoTypes = m[i].getModifiers();	
    		System.out.println(Modifier.toString(MoTypes));
    		System.out.println();
    	}
    	
    	
    	
    	
    	
    	
    	//6) The fields the class declares
    	System.out.println("~~~Fields~~~: ");
    	Field[] fields = c.getDeclaredFields();
    	for (int i=0; i < fields.length; i++) {
    		//a) The name
    		System.out.println("Name: " + fields[i].getName());
    		
    		//b) The type
    		String fieldType = fields[i].getType().getName();
    		System.out.println("Type: " + fieldType);
	    	//c) The modifiers
    		System.out.println("Modifier: " + Modifier.toString(fields[i].getModifiers()));
	    	//d) The current value of each field
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
	    			if (recursive == false) {
	    				System.out.println(fields[i].getType().getName() + "@" + System.identityHashCode(fields[i]));
	    			} else if (recursive == true ) {
	    				///Something
	    				
	    			}
	    			
	    			
	    		}
    		} catch (IllegalArgumentException | IllegalAccessException e) {
				System.out.println(e);
			}
    		
    		
    		System.out.println();
    	}
    	System.out.println();
    	
	    	
    	
    	
    	
    }
    
    
    
    public static void superHierarchy(Class c) {
		 //2) Name of the immediate super-class
		//Maybe replace with running Inspection again
		System.out.println(c.getName());
		int superCount = 1;
		Class superClass;
		superClass = c.getSuperclass();				//Retrieves the Super Class
		do {
			if (superClass == null) {													//If End of Herarchy
				for (int i=0; i < superCount ; i++) { System.out.print("    ");}		//Creates Indentation for Output
				System.out.println("Super-class is a primitive type, void, interface");
			} else {
				for (int i=0; i < superCount ; i++) {
					System.out.print("    ");
				}
				System.out.println(superClass.getName());
		    }
			
			Class superClassTemp = superClass.getSuperclass();
			superClass = superClassTemp;
			superCount++;
		} while (superClass != null);	
	
    }
    
    
    
    public static void interfaceHierarchy(Class[] c, int count) {
    	for (int i=0; i<c.length;i++) {
    		for (int o=0; o<count;o++) {
    			System.out.print("    ");
    		}
    		System.out.println(c[i].getName());
    		interfaceHierarchy(c[i].getInterfaces(), count+1);
    	}
    	
    	
    }
    
    
    //Indents by the int given
    public static void indent(int i) {
    	for (int o =0; o < i; o++) {
    		System.out.print("  ");
    	}
    }
    
    
    

}