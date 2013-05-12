/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dssmodule.value;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bierman
 */
public class DSSValueString extends DSSValue {
    String value;

    DSSValueString(String x) {
        value = x;
    }
    
    
    @Override
    public int length() {
        return value.length();
    }
    
    
    @Override
    public DSSValue power(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DSSValue mult(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DSSValue div(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DSSValue and(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DSSValue add(DSSValue b) {
        return (this.concat (b));
    }

    @Override
    public DSSValue sub(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DSSValue or(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean lessthan(DSSValue b) {
         return (value.compareTo(b.toString()) < 0);
   }

    @Override
    public boolean lessthanequal(DSSValue b) {
        return (value.compareTo(b.toString()) <= 0);
    }

    @Override
    public boolean greaterthan(DSSValue b) {
        return (value.compareTo(b.toString()) > 0);
    }

    @Override
    public boolean greaterthanequal(DSSValue b) {
        return (value.compareTo(b.toString()) >= 0);
    }

    @Override
    public boolean notequal(DSSValue b) {
        return (value.compareTo(b.toString()) != 0);
    }

    @Override
    public boolean equal(DSSValue b) {
        return (value.compareTo(b.toString()) == 0);
    }

    @Override
    public DSSValue concat(DSSValue b) {
        String x;
        x = value;
        x = x.concat(b.toString());
        return( DSSValueFactory.getDSSValue(x));
    }

    @Override
    public int toInt() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public long toLong() {
         return Long.parseLong(value);
   }

    @Override
    public double toFloat() {
        return Double.parseDouble(value);
    }
    
    public DSSValue toDate() throws ParseException {
        Date result;  
        DateFormat df = new SimpleDateFormat("E MM dd kk:mm:ss z yyyy");
        result = df.parse(value);
        return (DSSValueFactory.getDSSValue(result));
    }
    
}
