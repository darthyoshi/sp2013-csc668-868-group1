/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.value;
import java.util.Date;


/**
 *
 * @author bierman
 */
public class DSSValueFactory {
    public static DSSValue getDSSValue (int x) {
        return(new DSSValueInt (x));
    }
    public static DSSValue getDSSValue (long x) {
        return(new DSSValueInt (x));
    }
    public static DSSValue getDSSValue (String x) {
        return(new DSSValueString (x));
    }
    public static DSSValue getDSSValue (float x) {
        return(new DSSValueFloat (x));
    }
    public static DSSValue getDSSValue (double x) {
        return(new DSSValueFloat (x));
    }
    public static DSSValue getDSSValue (boolean x) {
        return(new DSSValueBool (x));
    }
//    public static DSSValue getDSSValue (DSSObject x) {
//        return(new DSSValueObject (x));
 //   }
    public static DSSValue getDSSValue (Date x) {
        return(new DSSValueDate (x));
    }
}