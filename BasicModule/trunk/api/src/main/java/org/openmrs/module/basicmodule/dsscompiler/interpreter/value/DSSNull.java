package org.openmrs.module.basicmodule.dsscompiler.interpreter.value;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSValue;

/**
 *
 * @author woeltjen
 */
public class DSSNull extends DSSValue<Object> {
    public static final DSSNull VALUE = new DSSNull(new Object());
    
    private DSSNull(Object javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "null";
    }
}
