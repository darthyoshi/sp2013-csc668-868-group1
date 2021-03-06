/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dssmodule.value;

import java.util.Date;

/**
 *
 * @author bierman
 */
public class DSSValueDate extends DSSValue {
    Date value;

    public DSSValueDate(Date x) {
        value = x;
    }


    @Override
    public boolean isDate() {
        return true;
    }

    @Override
    public DSSValue add(DSSValue b) {
        Date x;
        
        if (b.isDate() || b.isNumeric()){
            x = new Date(value.getTime() + b.toLong());
            return( DSSValueFactory.getDSSValue(x));
            
        }
        
        return( DSSValueFactory.getDSSValue());
    }

    @Override
    public DSSValue sub(DSSValue b) {
        Date x;
        if (b.isDate() || b.isNumeric()){
            x = new Date(value.getTime() - b.toLong());
            return( DSSValueFactory.getDSSValue(x));
        }
        return( DSSValueFactory.getDSSValue());
    }

    @Override
    public boolean lessthan(DSSValue b) {
        if (b.isDate()) {
            return (value.compareTo(((DSSValueDate)b).value) < 0);
        }
        else {
            return( false );
        }
    }

    @Override
    public boolean lessthanequal(DSSValue b) {
        if (b.isDate()) {
            return (value.compareTo(((DSSValueDate)b).value) <= 0);
        }
        else {
            return (false);
        }
    }

    @Override
    public boolean greaterthan(DSSValue b) {
        if (b.isDate()) {
            return (value.compareTo(((DSSValueDate)b).value) > 0);
        }
        else {
            return (false);
        }
    }

    @Override
    public boolean greaterthanequal(DSSValue b) {
        if (b.isDate()) {
            return (value.compareTo(((DSSValueDate)b).value) >= 0);
        }
        else {
            return (false);
        }
    }

    @Override
    public boolean notequal(DSSValue b) {
        if (b.isDate()) {
            return (value.compareTo(((DSSValueDate)b).value) != 0);
        }
        else {
            return (false);
        }
    }

    @Override
    public boolean equal(DSSValue b) {
        if (b.isDate()) {
            return (value.compareTo(((DSSValueDate)b).value) == 0);
        }
        else {
            return (false);
        }
    }

    //toInt on the Date class returns a value in seconds not milliseconds as in toLong
    //this is done to prevent overflow of the integer
    @Override
    public int toInt() {
        return ((int)(value.getTime()/1000));
    }

    @Override
    public long toLong() {
        return (value.getTime());
    }

    @Override
    public double toFloat() {
        return( Double.NaN);
    }
    
    @Override
    public String toString() {
        return (value.toString());
    }

   
}
