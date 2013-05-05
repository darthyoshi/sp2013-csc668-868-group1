/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.value;

/**
 *
 * @author bierman
 */
public class DSSValueInt extends DSSValueNumeric {
    long value;

    
    DSSValueInt (int i) {
        value = i;
    }
    
    DSSValueInt (long i) {
        value = i;
    }   
    
    @Override
    public boolean isInt() {
        return true;
    }
    
    @Override
     public int toInt () {
        return (int)value;
    }
    
    @Override
     public long toLong () {
        return value;
    }
    
    @Override
    public String toString () {
        return Long.toString(value);
    }
    
    @Override
    public double toFloat () {
        return (1.0 * value);
    }
    
    
    @Override
    public DSSValue mult (DSSValue b) {
        return( DSSValueFactory.getDSSValue(value * b.toLong()));
    }
    
    @Override
    public DSSValue div (DSSValue b) {
        return( DSSValueFactory.getDSSValue(value / b.toLong()));   
    }
    
    @Override
    public DSSValue add (DSSValue b) {
        return( DSSValueFactory.getDSSValue(value + b.toLong()));   
    }
    
    @Override
    public DSSValue sub (DSSValue b) {
        return( DSSValueFactory.getDSSValue(value - b.toLong()));
    }
    
    @Override
    public boolean equal (DSSValue b) {
        return (value == b.toLong());   
    }

    @Override
    public boolean notequal (DSSValue b) {
        return (value != b.toLong());   
    }

    @Override
    public boolean lessthan (DSSValue b) {
        return (value < b.toLong());   
    }

    @Override
    public boolean lessthanequal (DSSValue b) {
        return (value <= b.toLong());   
    }

    @Override
    public boolean greaterthan (DSSValue b) {
        return (value > b.toLong());   
    }

    @Override
    public boolean greaterthanequal (DSSValue b) {
        return (value >= b.toLong());   
    }

    @Override
    public DSSValue and(DSSValue b) {
        if (b.isInt()) {
            return( DSSValueFactory.getDSSValue(value & ((DSSValueInt)b).value));
        }
        else
            return DSSValueFactory.getDSSValue();
    }

    @Override
    public DSSValue or(DSSValue b) {
         if (b.isInt()) {
            return( DSSValueFactory.getDSSValue(value | ((DSSValueInt)b).value));
        }
        else
            return DSSValueFactory.getDSSValue();
    }
}
