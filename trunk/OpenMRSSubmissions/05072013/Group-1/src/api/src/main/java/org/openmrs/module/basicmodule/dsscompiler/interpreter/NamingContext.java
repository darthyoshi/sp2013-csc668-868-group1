package org.openmrs.module.basicmodule.dsscompiler.interpreter;

import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;

/**
 * Represents a place where values may be stored and 
 * retrieved by name. This may be the ExecutionContext 
 * in general, or it may be a DSS object.
 * @author woeltjen
 */
public interface NamingContext {
    /**
     * Get the value currently associated with 
     * the specified name in this context
     * @param name
     * @return 
     */
    public DSSValue get(String name);
    
    /**
     * Set a name-value association in this context. 
     * This may overwrite any previous association.
     * @param name
     * @param value 
     */
    public void     set(String name, DSSValue value);
    
    /**
     * All names used by objects in this context
     * @return 
     */
    public String[] names();
}
