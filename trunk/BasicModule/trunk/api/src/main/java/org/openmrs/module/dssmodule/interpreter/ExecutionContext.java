package org.openmrs.module.dssmodule.interpreter;

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
    
    public void beginScope() {
        scopes.push(variables);
        variables = new HashMap<String, DSSValue>();
    }
    
    public void endScope() {
        variables = scopes.pop();
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public DSSFunction getFunction(String name) {
        return functions.get(name);
    }

    public DSSValue getReturnValue() {
        return returnValue;
    }

    public void setFunction(String name, DSSFunction f) {
        functions.put(name, f);
    }

    public void setReturnValue(DSSValue v) {
        returnValue = v;
    }

    public DSSValue get(String name) {
        return variables.containsKey(name) ? 
                variables.get(name) : evaluator.toDSSValue(null);
    }

    public String[] names() {
        return variables.keySet().toArray(new String[variables.keySet().size()]);
    }

    public void set(String name, DSSValue value) {
        variables.put(name, value);
    }

}
