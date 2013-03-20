/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.stub;

import interpreter.DSSFunction;
import interpreter.DSSValue;
import interpreter.Evaluator;
import interpreter.ExecutionContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author woeltjen
 */
public class StubContext extends ExecutionContext {
    private Evaluator evaluator = new StubEvaluator();
    private Map<String, DSSFunction> functions = new HashMap<String, DSSFunction>();
    private Map<String, DSSValue>    variables = new HashMap<String, DSSValue>();
    private DSSValue returnValue = null;
    private Stack<Map<String, DSSValue>> scopes = 
            new Stack<Map<String, DSSValue>>();
    
    
    @Override
    public void beginScope(boolean hideVariables) {
        scopes.push(variables);
        variables = new HashMap<String, DSSValue>();
    }
    
    @Override
    public void endScope() {
        variables = scopes.pop();
    }

    @Override
    public Evaluator getEvaluator() {
        return evaluator;
    }

    @Override
    public DSSFunction getFunction(String name) {
        return functions.get(name);
    }

    @Override
    public DSSValue getReturnValue() {
        return returnValue;
    }

    @Override
    public void setFunction(String name, DSSFunction f) {
        functions.put(name, f);
    }

    @Override
    public void setReturnValue(DSSValue v) {
        returnValue = v;
    }

    public DSSValue get(String name) {
        return variables.get(name);
    }

    public String[] names() {
        return variables.keySet().toArray(new String[variables.keySet().size()]);
    }

    public void set(String name, DSSValue value) {
        variables.put(name, value);
    }
    
}
