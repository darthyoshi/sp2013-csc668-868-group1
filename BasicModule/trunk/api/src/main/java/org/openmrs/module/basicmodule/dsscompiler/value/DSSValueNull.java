/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.value;

/**
 *
 * @author bierman
 */
public class DSSValueNull extends DSSValue {

    @Override
    public boolean lessthan(DSSValue b) {
        return (false);
    }

    @Override
    public boolean lessthanequal(DSSValue b) {
        return (false);
    }

    @Override
    public boolean greaterthan(DSSValue b) {
        return (false);
    }

    @Override
    public boolean greaterthanequal(DSSValue b) {
        return (false);
    }

    @Override
    public boolean notequal(DSSValue b) {
        return (false);
    }

    @Override
    public boolean equal(DSSValue b) {
        return (false);
    }

    @Override
    public int toInt() {
        return(0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long toLong() {
        return(0); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double toFloat() {
        return (Double.NaN);
    }
    
    @Override
    public String toString() {
        return ("null");
    }
    
    @Override
    public boolean isNull() {return true;}

    
}
