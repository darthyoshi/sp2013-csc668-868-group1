/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.intrinsics;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSLibrary;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.value.*;

/**
 * The various "is" intrinsics (isString, isInteger, etc)
 * @author woeltjen
 */
public class IsLibrary implements DSSLibrary {
    private static final Map<String, DSSFunction> MAP = 
            new HashMap<String, DSSFunction>();
    
    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        // Initialize lazily
        if (MAP.isEmpty()) {
            MAP.put("isString", new IsFunction(DSSValueString.class));
            MAP.put("isInt", new IsFunction(DSSValueInt.class));
            MAP.put("isFloat", new IsFunction(DSSValueFloat.class));
            MAP.put("isBoolean", new IsFunction(DSSValueBool.class));
            MAP.put("isDate", new IsFunction(DSSValueDate.class));
            //MAP.put("isObject", new IsFunction(DSSValueObject.class));
            //MAP.put("isNull", new IsFunction(DSSValueList.class));
        }
        return MAP;
    }
    
    
    private static class IsFunction extends DSSFunction {
        private Class isClass;

        public IsFunction(Class isClass) {
            this.isClass = isClass;
        }
        
        @Override
        public DSSValue call(DSSValue... args) {
            boolean is = args.length > 0 && 
                    isClass.isAssignableFrom(args[0].getClass());
            return DSSValueFactory.getDSSValue(is);
        }
        
    }
}
