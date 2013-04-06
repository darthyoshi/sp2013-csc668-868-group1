package org.openmrs.module.basicmodule.dsscompiler.interpreter.value;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSValue;

/**
 *
 * @author woeltjen
 */
public class DSSString extends DSSValue<String> {

    public DSSString(String javaObject, long timeStamp) {
        super(javaObject, timeStamp);
    }

    public DSSString(String javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 10;
    }

    @Override
    public DSSValue<?> cast(DSSValue<?> value) {
        return new DSSString(value.toString());
    }
    
    

    @Override
    public String toString() {
        return getJavaObject();
    }

    @Override
    public DSSValue<?> add(DSSValue<?> v) {
        return new DSSString(getJavaObject() + v.toString());
    }

    @Override
    public DSSValue<?> concat(DSSValue<?> v) {
        return add(v);
    }
    
    
    
    
}
