/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dssmodule.value;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author bierman
 */
public class DSSValueList extends DSSValue {
    Vector<DSSValue> value;
    
    DSSValueList (Vector<DSSValue> v) {
        value = v;
    }

    @Override
    public DSSValue add (DSSValue b) {
        if (b.isList()) {
            value.addAll(((DSSValueList)b).value);
            
        } else {
            value.add (b);
        }
        return(this);   
    }
    
    public DSSValue get (int i) {
        return ((DSSValue)value.get(i));
    }
    
    public void clear () {
        value.clear();
    }
    
    
    @Override
    public boolean lessthan(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean lessthanequal(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean greaterthan(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean greaterthanequal(DSSValue b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean notequal(DSSValue b) {
        return !equal(b);
    }

    @Override
    public boolean equal(DSSValue b) {
        if (b instanceof DSSValueList) {
            Vector<DSSValue> other = ((DSSValueList)b).value;
            if (other.size() == value.size()) {
                for (int i = 0; i < value.size(); i++) {
                    if (other.get(i).notequal(value.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        } 
        return false;
    }

    @Override
    public String toString() {
        int i, sz;
        String s;
        
        sz = value.size();
        
        s= "{";
        for (i=0; i < sz; i++) {
            s = s + (value.get(i)).toString();
            if ( i < (sz-1)) {
                s = s + ", ";
            }
        }
        s = s + "}";
        return s;
    }
    
    @Override
    public DSSValue sub (DSSValue b) {
        return DSSValueFactory.getDSSValue(); 
    }

    
    @Override
    public DSSValue concat (DSSValue b) {
        return DSSValueFactory.getDSSValue(); 
    }

    //public abstract DSSValue ref (DSSValue b);
    @Override
    public boolean isList() {return true;}
    
    @Override
    public int length() {return value.size();}

    @Override
    public int toInt() {
        return 0;
    }

    @Override
    public long toLong() {
        return 0;
    }

    @Override
    public double toFloat() {
        return( Double.NaN);
    }
    
    @Override
    public DSSValue sort() {
        Collections.sort(value);
        return DSSValueFactory.getDSSValue(true);
    }

}
