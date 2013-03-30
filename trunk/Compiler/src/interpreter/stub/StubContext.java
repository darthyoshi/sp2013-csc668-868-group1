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

    public StubContext() {
        super(new StubEvaluator());
    }
}
