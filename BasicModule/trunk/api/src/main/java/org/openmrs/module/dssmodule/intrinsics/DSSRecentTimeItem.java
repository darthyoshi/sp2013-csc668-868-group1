package org.openmrs.module.dssmodule.intrinsics;

import org.openmrs.module.dssmodule.interpreter.DSSFunction;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.value.DSSValueDate;
import org.openmrs.module.dssmodule.value.DSSValueList;


/**
 * recentTimeItem(list) � return the item with the most recent time
 * DSSCurrentTime class extends DSSFunction and return the current time in
 * specific format.DSSCurrentTIme also owns instances of DSSFunction. The
 * display format will match the system format. Default format yyyy-MM-dd
 * hh:mm:ss e.g. Name	Value System Date	2013-04-11 System Time 09:07:32
 *
 * @author kent
 */
public class DSSRecentTimeItem extends DSSFunction {
    public DSSRecentTimeItem(){
        
    }
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

        int i =0;
        //the first item gets assigned to recentItem 
        recentItem = dssDateList.get(0);
        int max = dssDateList.length();
       
        // Starting counter 1, Compare each DSSValueDate as Date type to find the recent item
        // If it is greater than and equal to 0, recentItem is the same as current item 
        // otherwise, recentItem doesn't change it
        
        for (i = 1; i < max; i++) {
            if (recentItem.getTimeStamp().compareTo(dssDateList.get(i).getTimeStamp()) <= 0) {
                recentItem = dssDateList.get(i);
            } 
        }
        return recentItem;
    }
}
