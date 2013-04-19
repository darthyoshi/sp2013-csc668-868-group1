package org.openmrs.module.basicmodule.dsscompiler.interpreter;

import java.util.Map;

/**
 * Base class for libraries. Used as a convenience for 
 * organizing related intrinsics.
 * @author woeltjen
 */
public interface DSSLibrary {
    
    /**
     * Get all functions defined in this library. These should be returned 
     * in a map from function name -> function object.
     * 
     * Function name should be the name used to call the function from a DSS 
     * program (e.g. "read"); function object should be a Java object that 
     * extends DSSFunction (e.g. new DSSRead())
     * 
     * An ExecutionContext is provided in case a library needs to use or 
     * link against elements in that environment. DSSLibrary implementations 
     * are free to ignore this argument.
     * 
     * @param context the execution context to use for interactions
     * @return a map of functions defined by this library
     */
    public Map<String, DSSFunction> getFunctions(ExecutionContext context);
}
