package org.openmrs.module.basicmodule.dsscompiler.intrinsics;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSLibrary;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueFactory;

/**
 *
 * @author Jason
 */
public class DSSLengthAndWithinLibrary implements DSSLibrary{
   private static final Map<String, DSSFunction> Map  = new HashMap<String, DSSFunction>();;
   
    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
       if(Map.isEmpty()){
           Map.put("length", new DSSLengthAndWithinLibrary.DSSLength());
           Map.put("within", new DSSLengthAndWithinLibrary.DSSWithin());
       }
       return Map;
    }
    
    // the length of either list/string
    private static class DSSLength extends DSSFunction{

        public DSSValue call(DSSValue... args){
            return DSSValueFactory.getDSSValue(args[0].length());
        }
    }
    
    // returns true if v is between a and b e.g., within(3,2,5) is true
    private class DSSWithin extends DSSFunction {
    
            public DSSValue call(DSSValue... args){
                if(args[0].greaterthanequal(args[1]) && args[0].lessthanequal(args[2])) 
                    return DSSValueFactory.getDSSValue(true);
                    
                return DSSValueFactory.getDSSValue(false);
            }
    }
}

