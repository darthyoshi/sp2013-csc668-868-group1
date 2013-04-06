package org.openmrs.module.basicmodule.dsscompiler.interpreter.value;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSValue;

/**
 *
 * @author woeltjen
 */
public class DSSBoolean extends DSSValue<Boolean> {

    public DSSBoolean(Boolean javaObject, long timeStamp) {
        super(javaObject, timeStamp);
    }

    public DSSBoolean(Boolean javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 1;
    }

    @Override
    public DSSValue<?> add(DSSValue<?> v) {
        return and(v);
    }

    @Override
    public DSSValue<?> and(DSSValue<?> v) {
        if (v instanceof DSSBoolean) {
            return (getJavaObject() && ((DSSBoolean)v).getJavaObject()) ? 
                    TRUE : FALSE;
        }
        return super.and(v);
    }

    @Override
    public DSSValue<?> not() {
        return getJavaObject() ? FALSE : TRUE;
    }

    @Override
    public DSSValue<?> or(DSSValue<?> v) {
        if (v instanceof DSSBoolean) {
            return (getJavaObject() || ((DSSBoolean)v).getJavaObject()) ? 
                    TRUE : FALSE;
        }
        return super.or(v);    
    }      
    
    public static final DSSBoolean TRUE = new DSSBoolean(true);
    public static final DSSBoolean FALSE = new DSSBoolean(false);    
}
