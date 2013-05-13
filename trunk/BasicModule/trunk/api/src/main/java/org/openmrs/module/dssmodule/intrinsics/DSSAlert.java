package org.openmrs.module.dssmodule.intrinsics;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.dssmodule.DSSRuleService;
import org.openmrs.module.dssmodule.state.DSSFunction;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.value.DSSValue;

/**
 * Implements the "alert" intrinsic; also exposes itself as a library.
 * Note that this is not the class that will be used when running under 
 * OpenMRS; DSSRuleService defines its own version of this intrinsic to 
 * coordinate with insertion of alerts at appropriate locations. This 
 * version simply outputs to console.
 * @see DSSRuleService
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
