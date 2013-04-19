/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.intrinsics;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueBool;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueDate;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueFactory;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueFloat;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueInt;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueList;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueString;

/**
 *
 * @author kent
 */
public class DSSDateLibrary extends AnnotatedDSSLibrary{
        private static final Map<String, DSSFunction> MAP = 
            new HashMap<String, DSSFunction>();
    
    @Override
    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        // Initialize lazily
        if (MAP.isEmpty()) {
            MAP.put("DSSAddDays", new DSSDateLibrary.DateFunction());
            MAP.put("DSSAddMonths", new DSSDateLibrary.DateFunction());
            MAP.put("DSSBefore", new DSSDateLibrary.DateFunction());
            MAP.put("DSSCurrentTime", new DSSDateLibrary.DateFunction());
            MAP.put("DSSOldestTimeItem", new DSSDateLibrary.DateFunction());
            MAP.put("DSSRecentTimeItem", new DSSDateLibrary.DateFunction());
            MAP.put("DSSTime", new DSSDateLibrary.DateFunction());  
            //MAP.put("isObject", new IsFunction(DSSValueObject.class));
            //MAP.put("isNull", new IsFunction(DSSValueNull.class));
        }
        return MAP;
    }
    
    
    private static class DateFunction extends DSSFunction {
        private Class isClass;
        
        @Override
        public DSSValue call(DSSValue... args) {
            boolean is = args.length > 0 && 
                    isClass.isAssignableFrom(args[0].getClass());
            return DSSValueFactory.getDSSValue(is);
        }        
    }
    
    
    //@DSSIntrinsic
    //public DSSValue DSSAddDays(){
      //  return null;
    //}
    
    //@DSSIntrinsic
    //public DSSAddMonths(){
    //}
    
    //@DSSIntrinsic
    //public DSSBefore(){
    //}
    
    //@DSSIntrinsic
    //public DSSCurrentTime(){
    //}
    
    //@DSSIntrinsic
    //public DSSRecentTimeItem(){
    //}
    
    //@DSSIntrinsic
    //public DSSTime(){
    //}
}
