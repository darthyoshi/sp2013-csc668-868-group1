package org.openmrs.module.basicmodule.dsscompiler.interpreter;

import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.DSSAlert;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.value.DSSEvaluator;
import org.openmrs.module.basicmodule.dsscompiler.parser.Parser;

/**
 *
 * @author woeltjen
 */
public class Interpreter {
    private static final String DEFAULT_DSS_FILE = "fib.dss";
    
    private ExecutionContext context = new ExecutionContext(new DSSEvaluator());    
    
    public Interpreter() {
        context.setFunction("alert", new DSSAlert());
    }
    
    public void interpret(AST ast) {
        ast.accept(new InterpreterVisitor(context));
    }
    
    public static void main(String[] args) {
        try {
            String dss = args.length > 1 ? args[0] : DEFAULT_DSS_FILE;
            new Interpreter().interpret(new Parser(dss).execute());
        } catch (Exception e) {
            e.printStackTrace(); // TODO: Handle exceptions meaningfully
        }
    }
}
