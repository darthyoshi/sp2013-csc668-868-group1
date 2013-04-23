/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueBool;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueFactory;

/**
 * before(time1,time2) – return true if time1 is before time2
 * @param DSSValueDate time1, DSSValueDate time2
 * @author kent
 */
public class DSSBefore extends DSSFunction{
    public DSSBefore(){
        
    }
    /*
     * Usage the DSSValueDate  less than function to compare two parameter args
     * @return DSSValueBool a boolean indicate whether time1 is before time2
     */
    public DSSValue call(DSSValue... args){
        if(args[0].lessthan(args[1])) return DSSValueFactory.getDSSValue(true);
        
        return DSSValueFactory.getDSSValue(false);
    }
}
