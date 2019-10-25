import java.lang.reflect.*;


public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	
    	//1) Name of Declaring Class
    	System.out.println("\nName of Declared Class: " + c.getName());
    	
    	
    	//4)the constructors the class declares, FOR EACH
	    	//a) The name
	    	//b) The Parameter types
	    	//c)The modifiers
    	
    	
    	
    	
    	
    	//5) The methods the class declares, for each, also find
	    	//a) The name
	    	//b) The Exceptions throw
	    	//c) The Parameter types
	    	//d) The return type
 	    	//e) The modifiers  	
    	Method[] m;
    	//m = c.getMethods();			//All public Methods (Includes inherited)
    	m = c.getDeclaredMethods();		//All declared Methods (Not includes inherited
    	for (int i=0; i< m.length; i++) {
    		System.out.println("Declaring Class: " + m[i].getDeclaringClass().getName());
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
    		System.out.println(MoTypes);
    		System.out.println();
    	}
    	
    	
    	
    	
    	
    	
    	//6) The fields the class declares
    		//a) The name
	    	//b) The type
	    	//c) The modifiers
	    	//d) The current value of each field
	    		//e) sdklfjsldksdlfksjdlksjf
    	
    	
    	//2) Name of the immediate super-class
    			//Maybe replace with running Inspection again
    	
    	/*
    	 * isPrimitive() to check if class object is primitive 
    	 * 
    	 */
    	System.out.println("SuperClass Herarchy:");
    	int superCount = 0;
    	Class superClass;
    	superClass = c.getSuperclass();				//Retrieves the Super Class
    	do {
	    											
	    	
	    	if (superClass == null) {													//If End of Herarchy
	    		for (int i=0; i < superCount ; i++) { System.out.print("    ");}		//Creates Indentation for Output
	    		System.out.println("Super-class is a primitive type, void, interface, or Object Class");
	    	} else {
	    		
		    	try {
					Object superObj = superClass.newInstance();
					for (int i=0; i < superCount ; i++) {
		    			System.out.print("    ");
		    		}
					System.out.println(superClass.getName());
				} catch (InstantiationException | IllegalAccessException e) { System.out.println(e); }
		    }
	    	
	    	Class superClassTemp = superClass.getSuperclass();
	    	superClass = superClassTemp;
	    	superCount++;
    	} while (superClass != null);	
    	
    	
    	
    	
    	//3) Name of each interface the class implements
    		//Similar to Super-Class
    	
    	
    }
    
    
    
    
    
    

}