/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.value;

/**
 *
 * @author bierman
 */
public class DSSValueBool extends DSSValue {
    boolean value;

    DSSValueBool(boolean x) {
        value = x;
    }
    
    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public DSSValue and(DSSValue b) {
        if (b.isBoolean()) {
            return( DSSValueFactory.getDSSValue(value && ((DSSValueBool)b).value));
        }
        
    return null;
    }
   
    @Override
    public DSSValue not(DSSValue b) {
        if(b.isBoolean()){
            return( DSSValueFactory.getDSSValue( !((DSSValueBool)b).value));
        }
        
    return null;
    }
   
    @Override
    public DSSValue or(DSSValue b) {
        if (b.isBoolean()) {
            return( DSSValueFactory.getDSSValue(value || ((DSSValueBool)b).value));
        }
        
    return null;
    }

    @Override
    public String toString() {
        if (value)
            return ("True");
        else
            return ("False");
    }


    @Override
    public boolean lessthan(DSSValue b) {
        return false;
    }

    @Override
    public boolean lessthanequal(DSSValue b) {
        return false;
    }

    @Override
    public boolean greaterthan(DSSValue b) {
        return false;
    }

    @Override
    public boolean greaterthanequal(DSSValue b) {
        return false;
    }

    @Override
    public boolean notequal(DSSValue b) {
        if (this.equal (b)) return (false);
        return true;
    }

    @Override
    public boolean equal(DSSValue b) {
        boolean b2;
        
        if (b.isBoolean())
            return (value == ((DSSValueBool)b).value);
        
        int i = b.toInt();
        b2 = true;
        if (i==0) b2=false;
        
        return (value == b2);    
    }

    @Override
    public int toInt() {
        if (value)
            return (1);
        else
            return (0);
    }
    
    @Override
    public long toLong() {
        return (long) toInt();
    }



    @Override
    public double toFloat() {
        return (double) toInt();
    }

}
