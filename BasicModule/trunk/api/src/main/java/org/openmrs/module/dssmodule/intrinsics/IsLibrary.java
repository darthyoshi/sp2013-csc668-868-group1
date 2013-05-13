package org.openmrs.module.dssmodule.intrinsics;

import org.openmrs.module.dssmodule.value.DSSValueFactory;
import org.openmrs.module.dssmodule.value.DSSValueInt;
import org.openmrs.module.dssmodule.value.DSSValueFloat;
import org.openmrs.module.dssmodule.value.DSSValueList;
import org.openmrs.module.dssmodule.value.DSSValueString;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.value.DSSValueBool;
import org.openmrs.module.dssmodule.value.DSSValueDate;
import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.dssmodule.state.DSSFunction;
import org.openmrs.module.dssmodule.state.ExecutionContext;

/**
 * The various "is" intrinsics (isString, isInteger, etc)
 * @author woeltjen
 */
public class IsLibrary implements DSSLibrary {
    private static final Map<String, DSSFunction> MAP = 
            new HashMap<String, DSSFunction>();

    @Override
    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        // Initialize lazily
        if (MAP.isEmpty()) {
            MAP.put("isString", new IsFunction(DSSValueString.class));
            MAP.put("isInt", new IsFunction(DSSValueInt.class));
            MAP.put("isFloat", new IsFunction(DSSValueFloat.class));
            MAP.put("isBoolean", new IsFunction(DSSValueBool.class));
            MAP.put("isDate", new IsFunction(DSSValueDate.class));
            MAP.put("isList", new IsFunction(DSSValueList.class));            
            //MAP.put("isObject", new IsFunction(DSSValueObject.class));
            //MAP.put("isNull", new IsFunction(DSSValueNull.class));
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
