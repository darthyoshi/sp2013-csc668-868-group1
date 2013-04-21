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
import org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date.DSSAddDays;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date.DSSAddMonths;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date.DSSBefore;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date.DSSCurrentTime;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date.DSSOldestTimeItem;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date.DSSRecentTimeItem;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date.DSSTime;
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
public class DSSDateLibrary implements DSSLibrary{
        private static final Map<String, DSSFunction> MAP =
            new HashMap<String, DSSFunction>();

    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        // Initialize lazily
        if (MAP.isEmpty()) {
            MAP.put("AddDays", new DSSAddDays());
            MAP.put("AddMonths", new DSSAddMonths());
            MAP.put("Before", new DSSBefore());
            MAP.put("CurrentTime", new DSSCurrentTime());
            MAP.put("OldestTimeItem", new DSSOldestTimeItem());
            MAP.put("RecentTimeItem", new DSSRecentTimeItem());
            MAP.put("Time", new DSSTime());
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
