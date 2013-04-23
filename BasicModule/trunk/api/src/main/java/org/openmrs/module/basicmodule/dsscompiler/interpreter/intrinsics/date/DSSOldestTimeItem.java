/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date;

import java.util.Date;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueDate;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueList;

/**
 * oldestTimeItem(list) � return the item with the oldest time The display
 * format I used will match the system format. Default format yyyy-MM-dd
 * hh:mm:ss e.g. Name	Value System Date	2013-04-11 System Time 09:07:32
 *
 * @author kent
 */
public class DSSOldestTimeItem extends DSSFunction {
    public DSSOldestTimeItem(){
        
    }
    /* 
     * Iterate through the whole array of DSSValue and return the 
     * oldestItem by comparing the DSSValue.
     * @param DSSValue... args
     * @return DSSValueDate the oldestItem 
     */

    public DSSValue call(DSSValue... args) {
        Date date = new Date();
        DSSValue oldestItem = new DSSValueDate(date);

        //dssDateList will hold all DSSValue Date 
        DSSValueList dssDateList = (DSSValueList) args[0];

        int i;
        int max = dssDateList.length();
        oldestItem = dssDateList.get(0);
        for (i = 0; i < max; i++) {
            // get(i) return a DSSValue from the DSSValuDate list
            // getTimeStamp return a Date type of the item
            // Compare each DSSValueDate as Date type to find the oldest
            // If it is less than and equal to 0, oldestItem is the same as current item 
            // otherwise, it gets assgined to next item
            
            if (oldestItem.getTimeStamp().compareTo(dssDateList.get(i).getTimeStamp()) <= 0) {
                oldestItem = dssDateList.get(i);
            } 
            i++;
        }


        return oldestItem;

    }
}
