package org.openmrs.module.dssmodule.state;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.dssmodule.value.DSSValue;

/**
 * Extends the base ExecutionContext to allow variables and functions to 
 * be defined which cannot be changed by a running DSS program.
 * @author woeltjen
 */
public class DSSExecutionContext extends ExecutionContext {
    /*
     * These maps hold functions and variables that will not be modified 
     * during the running of a DSSFunction - intrinsics, patientId...
     */
    private Map<String, DSSFunction> intrinsics = 
            new HashMap<String, DSSFunction>(); 
    private Map<String, DSSValue> constants = 
            new HashMap<String, DSSValue>(); 
    
    public DSSExecutionContext(Evaluator evaluator) {
        super(evaluator);
    }

    @Override
    public DSSValue get(String name) {
        return constants.containsKey(name) ? constants.get(name) : super.get(name);
    }

    @Override
    public DSSFunction getFunction(String name) {
        return intrinsics.containsKey(name) ? intrinsics.get(name) : super.getFunction(name);
    }
    
    /**
     * Associate a constant value with a given name. This is used to set 
     * pre-defined values in OpenMRS, such as patientId
     * @param name
     * @param value 
     */
    public void setConstant(String name, DSSValue value) {
        constants.put(name, value);
    }
    
    /**
     * Associate a constant function with a given name. This is used to 
     * set intrinsic functions.
     * @param name
     * @param func 
     */
    public void setIntrinsic(String name, DSSFunction func) {
        intrinsics.put(name, func);
    }
    
}
