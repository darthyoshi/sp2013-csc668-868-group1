/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dssmodule.intrinsics;

import org.openmrs.module.dssmodule.interpreter.DSSFunction;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.value.DSSValueBool;
import org.openmrs.module.dssmodule.value.DSSValueFactory;

/**
 * before(time1,time2) � return true if time1 is before time2
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
