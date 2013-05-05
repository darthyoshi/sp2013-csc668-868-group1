/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.value;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;



/**
 *
 * @author bierman
 */
public class DSSValueFactory {
    private static final DSSValue DSS_NULL = new DSSValueNull();
    
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
    
    public static DSSValue getDSSValue (Vector<DSSValue> x) {
        return(new DSSValueList (x));
    }
    
    public static DSSValue getDSSValueList () {
        Vector<DSSValue> x = new Vector<DSSValue>();
        return(new DSSValueList (x));
    }
    
    public static DSSValue getDSSValue (Map<String, DSSValue> map) {
        DSSValueObject value = new DSSValueObject();
        for (Entry<String, DSSValue> entry : map.entrySet()) {
            value.set(entry.getKey(), entry.getValue());            
        }
        return value;
    }
    
    public static DSSValue getDSSValue () {
        return DSS_NULL;
    }
}
