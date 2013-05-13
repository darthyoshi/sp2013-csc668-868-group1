/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dssmodule.intrinsics;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.dssmodule.state.DSSFunction;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.value.DSSValueBool;
import org.openmrs.module.dssmodule.value.DSSValueDate;
import org.openmrs.module.dssmodule.value.DSSValueFactory;
import org.openmrs.module.dssmodule.value.DSSValueFloat;
import org.openmrs.module.dssmodule.value.DSSValueInt;
import org.openmrs.module.dssmodule.value.DSSValueList;
import org.openmrs.module.dssmodule.value.DSSValueString;

/**
 *
 * @author kent
 */
public class DSSDateLibrary implements DSSLibrary{
        private static final Map<String, DSSFunction> MAP =
            new HashMap<String, DSSFunction>();

    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        // Initialize lazily
        if (MAP.isEmpty()) {
            MAP.put("addDays", new DSSAddDays());
            MAP.put("addMonths", new DSSAddMonths());
            MAP.put("before", new DSSBefore());
            MAP.put("currenttime", new DSSCurrentTime());
            MAP.put("oldestTimeItem", new DSSOldestTimeItem());
            MAP.put("recentTimeItem", new DSSRecentTimeItem());
            MAP.put("time", new DSSTime());
            //MAP.put("isObject", new IsFunction(DSSValueObject.class));
            //MAP.put("isNull", new IsFunction(DSSValueNull.class));
        }
        return MAP;
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
