package org.openmrs.module.basicmodule.dsscompiler.intrinsics;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSLibrary;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;

/**
 *
 * @author woeltjen
 */
public class DSSAlert extends DSSFunction implements DSSLibrary {
    @Override
    public DSSValue call(DSSValue... args) {
        for (DSSValue v : args) {
            System.out.println(v.toString());
        }
        return null; //DSSValue.DSS_NULL;
    }

    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        Map<String, DSSFunction> map = new HashMap<String, DSSFunction>();
        map.put("alert", this);
        return map;
    }
    
    
}
