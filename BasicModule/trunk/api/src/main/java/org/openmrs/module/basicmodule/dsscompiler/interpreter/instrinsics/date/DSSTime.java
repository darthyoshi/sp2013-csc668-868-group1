/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueDate;
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
    /*
     * Takes one argument v and construct a DSSValueDate object for it.
     * @param DSSValue v, v is date formatted data
     * @return DSSValue Date cooresponding to the argument v
     */
      public DSSValue call(DSSValue... args){
          Date date = new Date();
          DSSValueDate dssDate= new DSSValueDate(date); 
          dssDate.setTimeStamp((DSSValueDate)args[0]);
          return dssDate.getDSSValueTimeStamp() ;
      }
}
