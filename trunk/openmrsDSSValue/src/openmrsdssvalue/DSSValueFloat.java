/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package openmrsdssvalue;

/**
 *
 * @author bierman
 */
public class DSSValueFloat extends DSSValueNumeric {
    double value;

    DSSValueFloat(float x) {
        value = x;
    }

    DSSValueFloat(double x) {
        value = x;
    }
    
    
    @Override
    public boolean isFloat() {
        return true;
    }
    
    @Override
     public int toInt () {
        return (int) Math.round(value);
    }
    
    @Override
     public long toLong () {
        return (long) Math.round(value);
    }
    
    @Override
    public String toString () {
        return Double.toString(value);
    }
    @Override
    public double toFloat () {
        return (value);
    }
    
    
    @Override
    public DSSValue mult (DSSValue b) {
        return( DSSValueFactory.getDSSValue(value * b.toFloat()));
    }
    @Override
    public DSSValue div (DSSValue b) {
        return( DSSValueFactory.getDSSValue(value / b.toFloat()));   
    }
    @Override
    public DSSValue add (DSSValue b) {
        return( DSSValueFactory.getDSSValue(value + b.toFloat()));   
    }
    @Override
    public DSSValue sub (DSSValue b) {
        return( DSSValueFactory.getDSSValue(value - b.toFloat()));
    }
    @Override
    public boolean equal (DSSValue b) {
        return (value == b.toFloat());   
    }
    @Override
    public boolean notequal (DSSValue b) {
        return (value != b.toFloat());   
    }
    @Override
    public boolean lessthan (DSSValue b) {
        return (value < b.toFloat());   
    }
    @Override
    public boolean lessthanequal (DSSValue b) {
        return (value <= b.toFloat());   
    }
    @Override
    public boolean greaterthan (DSSValue b) {
        return (value > b.toFloat());   
    }
    @Override
    public boolean greaterthanequal (DSSValue b) {
        return (value >= b.toFloat());   
    }

    @Override
    public DSSValue power(DSSValue b) {
        throw new UnsupportedOperationException("Not supported for Float.");
    }

    @Override
    public DSSValue and(DSSValue b) {
        throw new UnsupportedOperationException("Not supported for Float.");
    }

    @Override
    public DSSValue or(DSSValue b) {
        throw new UnsupportedOperationException("Not supported for Float."); 
    }
}
