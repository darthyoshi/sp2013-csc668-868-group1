/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.value;

import interpreter.DSSValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author woeltjen
 */
public class DSSList extends DSSValue<List<DSSValue<?>>> {

    public DSSList(List<DSSValue<?>> javaObject, long timeStamp) {
        super(javaObject, timeStamp);
    }

    public DSSList(List<DSSValue<?>> javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 6;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        int c = getJavaObject().size();
        b.append("{ ");
        for (DSSValue<?> v : getJavaObject()) {
            b.append(v.toString());
            if (--c > 0) {
                b.append(" , ");
            }        
        }
        b.append(" }");
        
        return b.toString();
    }

    @Override
    public DSSValue<?> promoteOther(DSSValue<?> value) {
        if (value instanceof DSSList) {
            return value;
        } else {
            return new DSSList(Arrays.<DSSValue<?>>asList(value));
        }
    }
    
    public boolean contains(DSSValue<?> v) {
        for (DSSValue<?> c : getJavaObject()) {
            DSSValue<?> cmp = c.equal(v);
            if (cmp instanceof DSSBoolean && ((DSSBoolean)cmp).getJavaObject()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DSSValue<?> add(DSSValue<?> v) {
        // Append
        if (v instanceof DSSList) {
            List<DSSValue<?>> newList = new ArrayList<DSSValue<?>>();
            newList.addAll(getJavaObject());
            newList.addAll(((DSSList)v).getJavaObject());
            return new DSSList(newList);
        } else {
            return DSS_NULL;
        }
    }

    @Override
    public DSSValue<?> and(DSSValue<?> v) {
        // Intersect
        if (v instanceof DSSList) {
            List<DSSValue<?>> newList = new ArrayList<DSSValue<?>>();
            for (DSSValue<?> c : getJavaObject()) {
                if (((DSSList)v).contains(c)) {
                    newList.add(c);
                }
            }
            return new DSSList(newList);
        } else {
            return DSS_NULL;
        }
    }

    @Override
    public DSSValue<?> equal(DSSValue<?> v) {
        if (v instanceof DSSList) {
            List<DSSValue<?>> mine = getJavaObject();
            List<DSSValue<?>> theirs = ((DSSList)v).getJavaObject();
            if (mine.size() == theirs.size()) {
                // Check every element for equality
                for (int i = 0; i < mine.size(); i++) {
                    DSSValue<?> cmp = mine.get(i).equal(theirs.get(i));
                    if (!(cmp instanceof DSSBoolean && ((DSSBoolean)cmp).getJavaObject())) {
                        return DSSBoolean.FALSE;
                    }
                }
                return DSSBoolean.TRUE;
            }
        }
        return DSSBoolean.FALSE;
    }

    @Override
    public DSSValue<?> or(DSSValue<?> v) {
        return add(v); // Union
    }

    @Override
    public DSSValue<?> sub(DSSValue<?> v) {
        // Difference
        if (v instanceof DSSList) {
            List<DSSValue<?>> newList = new ArrayList<DSSValue<?>>();
            for (DSSValue<?> c : getJavaObject()) {
                if (!((DSSList)v).contains(c)) {
                    newList.add(c);
                }
            }
            return new DSSList(newList);
        } else {
            return DSS_NULL;
        }    
    }

    
    
    
}
