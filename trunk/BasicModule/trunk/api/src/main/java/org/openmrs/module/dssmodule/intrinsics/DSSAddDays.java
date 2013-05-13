/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dssmodule.intrinsics;

import java.util.Calendar;
import java.util.Date;
import org.openmrs.module.dssmodule.state.DSSFunction;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.value.DSSValueFactory;

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
    /**
     * constructor
     */
    public DSSAddDays() {
    }
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
        //args[0].add(args[1]);
        Date date = new Date(args[0].toLong());
        int days = args[1].toInt();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        
        Date newDate = cal.getTime();
        System.out.println("addDays "+newDate.toString());
       return DSSValueFactory.getDSSValue(newDate);
    }
}
