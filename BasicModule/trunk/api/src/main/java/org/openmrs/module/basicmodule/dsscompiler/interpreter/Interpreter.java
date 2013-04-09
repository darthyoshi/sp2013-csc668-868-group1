package org.openmrs.module.basicmodule.dsscompiler.interpreter;

import java.util.Map.Entry;
import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.DSSAlert;
import org.openmrs.module.basicmodule.dsscompiler.parser.Parser;

/**
 * An interpreter is responsible for running parsed DSS1 programs.
 * @author woeltjen
 */
public class Interpreter {
    private static final String DEFAULT_DSS_FILE = "/home/woeltjen/School/csc868/sp2013-csc668-868-group1/Compiler/src/fib.dss";
    
    private ExecutionContext context = new ExecutionContext(new DSSEvaluator());    
    
    public Interpreter() {
        context.setFunction("alert", new DSSAlert());
    }
    
    /**
     * Interpret the DSS1 program described by the provided AST.
     * @param ast 
     */
    public void interpret(AST ast) {
        ast.accept(new InterpreterVisitor(context));
    }
    
    /**
     * Install the provided library of functions into this interpreter's 
     * execution context. This allows users of the interpreter to pre-define 
     * or override certain functions, as appropriate to usage.
     * @param library 
     */
    public void install(DSSLibrary library) {
        for (Entry<String, DSSFunction> entry : library.getFunctions(context).entrySet()) {
            install(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Install a function into this interpreter's execution context. This can 
     * be used to introduce intrinsics and to override existing functions.
     * @param name
     * @param func 
     */
    public void install(String name, DSSFunction func) {
        context.setFunction(name, func);
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
