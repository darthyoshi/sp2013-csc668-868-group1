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
 * oldestTimeItem(list) – return the item with the oldest time
 * The display format I used will match the system format.
 * Default format yyyy-MM-dd hh:mm:ss   
 * e.g.
 * Name	Value
 * System Date	2013-04-11 
 * System Time  09:07:32 
 * @author kent
 */
public class DSSOldestTimeItem extends DSSFunction{
    /* 
     * Iterate through the whole array of DSSValu and return the 
     * oldestItem by comparing the DSSValue.
     * @param DSSValue... args
     * @return DSSValueDate the oldestItem 
     */
     public DSSValue call(DSSValue... args){
         Date date = new Date();
         DSSValue oldestItem = new DSSValueDate(date) ;
        
         //Set<DSSValue> = args;
         int i;
         int max =args.length -1;
        for(i=0; i< max ;i++){
            if (args[i].lessthanequal(args[++i])) {oldestItem = args[i] ;}
            else { oldestItem = args[++i];}
        }
        return oldestItem;
       
    }
}
