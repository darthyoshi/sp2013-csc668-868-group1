package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.date;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueDate;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueList;
import sun.util.calendar.BaseCalendar.Date;

/**
 * recentTimeItem(list) – return the item with the most recent time
 * DSSCurrentTime class extends DSSFunction and return the current time in
 * specific format.DSSCurrentTIme also owns instances of DSSFunction. The
 * display format will match the system format. Default format yyyy-MM-dd
 * hh:mm:ss e.g. Name	Value System Date	2013-04-11 System Time 09:07:32
 *
 * @author kent
 */
public class DSSRecentTimeItem extends DSSFunction {
    /*
     * Iterate through the whole array of DSSValu and return the 
     * most recentItem by comparing the DSSValue.
     * @param DSSValue array of items
     * @return DSSValue the recentItem
     */

    public DSSValue call(DSSValue... args) {
        java.util.Date date = new java.util.Date();
        DSSValue recentItem = new DSSValueDate(date);
        DSSValueList dssDateList = (DSSValueList) args[0];

        int i = 0;
        int max = dssDateList.length();
        // get(i) return a DSSValue from the DSSValuDate list
        // getTimeStamp return a Date type of the item
        // Compare each DSSValueDate as Date type to find the oldest
        // If it is greater than and equal to 0, recentItem is the same as current item 
        // otherwise, it gets assgined to next item
        for (i = 0; i < max; i++) {
            if (dssDateList.get(i).getTimeStamp().compareTo(dssDateList.get(++i).getTimeStamp()) >= 0) {
                recentItem = dssDateList.get(i);
            } else {
                recentItem = dssDateList.get(++i);
            }
        }
        return recentItem;
    }
}
