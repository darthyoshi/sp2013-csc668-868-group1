package org.openmrs.module.basicmodule.dsscompiler.interpreter;

/**
 * Base class for libraries. Used as a convenience for 
 * organizing related intrinsics.
 * @author woeltjen
 */
public interface DSSLibrary {
    /**
     * Add the functions of this library to the specified context.
     * @param context 
     */
    public void install(ExecutionContext context);
}
