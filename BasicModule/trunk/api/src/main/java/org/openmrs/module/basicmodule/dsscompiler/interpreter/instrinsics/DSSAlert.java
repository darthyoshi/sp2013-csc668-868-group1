package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSValue;

/**
 *
 * @author woeltjen
 */
public class DSSAlert extends DSSFunction {
    @Override
    public DSSValue call(DSSValue... args) {
        for (DSSValue v : args) {
            System.out.println(v.toString());
        }
        return DSSValue.DSS_NULL;
    }    
}
