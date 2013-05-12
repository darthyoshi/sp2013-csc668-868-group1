package org.openmrs.module.dssmodule.interpreter;

import org.openmrs.module.dssmodule.value.DSSValue;

/**
 * Represents a callable function in DSS. This may be either an intrinsic, or 
 * one defined in the source code.
 * 
 * @author woeltjen
 */
public abstract class DSSFunction {
    /**
     * Call this function. The arguments provided are as observed by the 
     * interpreter. Note that the actual number of arguments may not match 
     * the number of parameters expected; it is ultimately the function's 
     * responsibility to handle this situation.
     * @param args the arguments to the function
     * @return the return value of the represented function
     */
    public abstract DSSValue call(DSSValue... args);

    /**
     * Some DSS intrinsics want raw identifiers passed, straight 
     * from the code. They may indicate this through this method.
     * 
     * The majority of DSS functions will never need this, so 
     * by default this simply returns false.
     * 
     * @param argumentIndex
     * @return 
     */
    public boolean passAsIdentifier(int argumentIndex) {
        return false;
    }
            
}
