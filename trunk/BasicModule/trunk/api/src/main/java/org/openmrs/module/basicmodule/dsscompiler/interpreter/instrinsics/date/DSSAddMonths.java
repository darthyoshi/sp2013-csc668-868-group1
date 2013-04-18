/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.date;

import java.util.Date;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueDate;

/**
 * addMonths(time,numMonths) - return a new time based on numMonths
 * return a new time based on numMonths
 * The display format I used will match the system format.
 * Default format yyyy-MM-dd hh:mm:ss    
 * e.g.
 * Name	Value
 * System Date	2013-04-11 
 * System Time  09:07:32 
 * @author kent
 */
public class DSSAddMonths extends DSSFunction{
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
      
        //month_in_millisec = args[1]*
        args[0].add(args[1]);
        return args[0];
    }
}
