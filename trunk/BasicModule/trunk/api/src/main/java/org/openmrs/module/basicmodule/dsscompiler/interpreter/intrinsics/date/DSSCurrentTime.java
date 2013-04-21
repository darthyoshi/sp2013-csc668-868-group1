
package org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueDate;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueFactory;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueString;

/**
 * currenttime() ?return current time; e.g., Tue Nov 06 10:33:56 PST 2012
 * DSSCurrentTime class extends DSSFunction and return the current time in
 * specific format. The display format I used will match the system format.
 * Default format yyyy-MM-dd hh:mm:ss  
 * e.g.
 * Name	Value
 * System Date	2013-04-11 
 * System Time  09:07:32 
 * 
 * @author kent
 */
public class DSSCurrentTime extends DSSFunction{
    
    /**
     * Construct a DSSValueDate object and return it.
     * Convert the DSSValue using getDSSValue method in DSSValue Factory.java
     * @return DSSValueDate: a new data represents the current time
     */
    public DSSValue call(DSSValue... args){
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.print("current time = " +date );
        return DSSValueFactory.getDSSValue(date); 
    }
    
    /**
     * Testing currenttime() function
     */
    public static void main(String[] args){
         DSSCurrentTime dssTime = new  DSSCurrentTime();
         Date x = new Date();
         DSSValueDate arg = new DSSValueDate(x); 
         
        
    }
}
