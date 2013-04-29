/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueDate;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueFactory;
/**
 * time(v) – return time associated with v
 * The display format I used will match the system format.
 * Default format yyyy-MM-dd hh:mm:ss   
 * e.g.
 * Name	Value
 * System Date	2013-04-11 
 * System Time  09:07:32 
 * @author kent
 */
public class DSSTime extends DSSFunction{
    public DSSTime(){
        
    }
    /*
     * Takes one argument v and construct a DSSValueDate object for it.
     * Since we assume all DSSValue will have a time attached to it
     * I will simply return the time of the argument v
     * @param DSSValue v, v is date formatted data
     * @return DSSValue Date cooresponding to the argument v
     */
      public DSSValue call(DSSValue... args){
          System.out.println(args[0].getDSSValueTimeStamp());
          return args[0].getDSSValueTimeStamp();
      }
}
