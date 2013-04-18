
package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.date;


import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueDate;
import sun.util.calendar.BaseCalendar.Date;

/**
 * recentTimeItem(list) – return the item with the most recent time
 * DSSCurrentTime class extends DSSFunction and return the current time in
 * specific format.DSSCurrentTIme also owns instances of DSSFunction.
 * The display format will match the system format.
 * Default format yyyy-MM-dd hh:mm:ss   
 * e.g.
 * Name	Value
 * System Date	2013-04-11 
 * System Time  09:07:32 
 * @author kent
 */
public class DSSRecentTimeItem extends DSSFunction{
    /*
     * Iterate through the whole array of DSSValu and return the 
     * most recentItem by comparing the DSSValue.
     * @param DSSValue array of items
     * @return DSSValue the recentItem
     */
    public DSSValue call(DSSValue... args){
        java.util.Date date = new java.util.Date();
         DSSValue recentItem = new DSSValueDate(date) ;
        
         //Set<DSSValue> = args;
         int i;
         int max =args.length -1;
        for(i=0; i< max ;i++){
            if (args[i].greaterthanequal(args[++i])) recentItem = args[i] ;
            else recentItem  = args[++i];
        }
        return recentItem ;
    }

}
