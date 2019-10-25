
public class Inspector {

    public void inspect(Object obj, boolean recursive) {
        Class c = obj.getClass();
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth) {
    	
    	//1) Name of Declaring Class
    	System.out.println("Name of Declared Object: " + obj.getClass());
    	
    	
    	//2) Name of the immediate super-class
    	
    	//3) Name of each interface the class implements
    	//4)the constructors the class declares, FOR EACH
	    	//a) The name
	    	//b) The Parameter types
	    	//c)The modifiers
    	
    	//5) The methods the class declares, for each, also find
	    	//a) The name
	    	//b)The Exceptions throw
	    	//c) The Parameter types
	    	//d) The return type
 	    	//e) The modifiers  	
    	//6) The fields the class declares
    		//a) The name
	    	//b) The type
	    	//c) The modifiers
	    	//d) The current value of each field
	    		//e) sdklfjsldksdlfksjdlksjf
    	
    	
    	
    	
    	
    	
    }

}