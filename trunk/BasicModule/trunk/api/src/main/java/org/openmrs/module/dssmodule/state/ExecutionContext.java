package org.openmrs.module.dssmodule.state;

import org.openmrs.module.dssmodule.value.DSSValue;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Maintains things like variables & known functions.
 * @author woeltjen
 */
public class ExecutionContext implements NamingContext {
    private Evaluator evaluator;
    private Map<String, DSSFunction> functions = new HashMap<String, DSSFunction>();
    private Map<String, DSSValue>    variables = new HashMap<String, DSSValue>();
    private DSSValue returnValue = null;
    private Stack<Map<String, DSSValue>> scopes = 
            new Stack<Map<String, DSSValue>>();

    public ExecutionContext(Evaluator evaluator) {
        this.evaluator = evaluator;
    }
    
    /**
     * Begin a new variable scope.
     */
    public void beginScope() {
        scopes.push(variables);
        variables = new HashMap<String, DSSValue>();
    }
    
    /**
     * End the current variable scope, and restore the previous one.
     */
    public void endScope() {
        variables = scopes.pop();
    }

    /**
     * Get the evaluator appropriate to this execution context
     * @return 
     */
    public Evaluator getEvaluator() {
        return evaluator;
    }

    /**
     * Retrieve a named function.
     * @param name the name of the function
     * @return  an object which handles calls to the function
     */
    public DSSFunction getFunction(String name) {
        return functions.get(name);
    }

    /**
     * Get the currently-specified return value. This will be Java null 
     * whenever no return value has been specified. This is important, as 
     * BlockInterpreter polls for changes to return value and stops executing 
     * if their are any (in order to ensure that a function call ends 
     * immediately upon return.)
     * @see BlockInterpreter
     * @return 
     */
    public DSSValue getReturnValue() {
        return returnValue;
    }

    /**
     * Store a named function to this execution context.
     * @param name
     * @param f 
     */
    public void setFunction(String name, DSSFunction f) {
        functions.put(name, f);
    }

    /**
     * Set the current return value. Setting to null means that any current 
     * return value has been processed.
     * @param v the new return value (or null to clear return value)
     */
    public void setReturnValue(DSSValue v) {
        returnValue = v;
    }

    /**
     * Get the value of a variable associated with the specified name
     * @param name
     * @return 
     */
    public DSSValue get(String name) {
        return variables.containsKey(name) ? 
                variables.get(name) : evaluator.toDSSValue(null);
    }

    /**
     * Get a list of all variable names defined in this execution context
     * @return 
     */
    public String[] names() {
        return variables.keySet().toArray(new String[variables.keySet().size()]);
    }

    /**
     * Associate a value with a variable name. Any previously-defined value 
     * for that name in the current execution scope will be overwritten.
     * @param name
     * @param value 
     */
    public void set(String name, DSSValue value) {
        variables.put(name, value);
    }

}
