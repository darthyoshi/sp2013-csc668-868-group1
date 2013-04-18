/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.date;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;

/**
 * addDays(time,numDays) - return a new time based on numDays
 * The display format I used will match the system format.
 * Default format yyyy-MM-dd hh:mm:ss    
 * e.g.
 * Name	Value
 * System Date	2013-04-11 
 * System Time  09:07:32 
 * @author kent
 */
public class DSSAddDays extends DSSFunction{
    /*
     * Calcute amount of time based on args[1] in millisecond
     * Call a DSSValueDate.add(DSSValueDate temp) functionto add this value
     * to args[0]
     * Return argr[0]
     * 
     * @param DSSValue... (Date time,int numMonths)
     * @return DSSValueDate
     */
     public DSSValue call(DSSValue... args){
        args[0].add(args[1]);
        return args[0];
    }
}
