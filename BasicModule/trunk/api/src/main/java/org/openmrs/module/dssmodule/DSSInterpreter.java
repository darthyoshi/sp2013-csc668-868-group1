package org.openmrs.module.dssmodule;

import org.openmrs.module.dssmodule.flowcontrol.InterpreterVisitor;
import org.openmrs.module.dssmodule.intrinsics.DSSLibrary;
import org.openmrs.module.dssmodule.state.DSSExecutionContext;
import org.openmrs.module.dssmodule.state.DSSEvaluator;
import org.openmrs.module.dssmodule.state.DSSFunction;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.intrinsics.DSSAlert;
import org.openmrs.module.dssmodule.parser.Parser;

/**
 * An interpreter is responsible for running parsed DSS1 programs.
 * @author woeltjen
 */
public class DSSInterpreter {
    private static final String DEFAULT_DSS_FILE = "/home/woeltjen/School/csc868/sp2013-csc668-868-group1/Compiler/src/fib.dss";
    
    private DSSExecutionContext context = new DSSExecutionContext(new DSSEvaluator());    

    public DSSInterpreter(DSSLibrary... libraries) {        
        this(Collections.<String,Object>emptyMap(), libraries);
    }

    /**
     * Create a new Interpreter with the supplied elements pre-defined. 
     * Values in the constants maps will be converted from their original 
     * Java types to DSSValues if necessary, and supplied to the running DSS 
     * program as pre-defined variables
     * Libraries will be installed as intrinsics.
     * 
     * @param constants a map of variable names to constant values
     * @param libraries a list of libraries of functions to install
     */
    public DSSInterpreter(Map<String, Object> constants, DSSLibrary... libraries) {
        context.setFunction("alert", new DSSAlert());
        for (DSSLibrary library : libraries) {
            install(library);
        }
        for (Entry<String, Object> constant : constants.entrySet()) {
            defineConstant(constant.getKey(), constant.getValue());
        }
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
        install(library.getFunctions(context));
    }
    
    /**
     * Install the provided library of functions into this interpreter's 
     * execution context. This allows users of the interpreter to pre-define 
     * or override certain functions, as appropriate to usage.
     * Functions are delivered in a map, where keys give the name of the 
     * function, and the value gives the DSSFunction object itself.
     * @param library map of names to functions
     */
    public void install(Map<String, DSSFunction> library) {
        for (Entry<String, DSSFunction> entry : library.entrySet()) {
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
        context.setIntrinsic(name, func);
    }
    
    /**
     * Associate a constant value with the specified name. This is used to 
     * define constants such as patient id
     * Supplied values may be common Java objects (including Long, Double, 
     * String) and will be converted where possible to appropriate types 
     * for usage in the DSS subsystem.
     * @param name
     * @param value 
     */
    public void defineConstant(String name, Object value) {
        context.setConstant(name, context.getEvaluator().toDSSValue(value));
    }
    
    public static void main(String[] args) {
        try {
            String dss = args.length > 1 ? args[0] : DEFAULT_DSS_FILE;
            new DSSInterpreter().interpret(new Parser(dss).execute());
        } catch (Exception e) {
            e.printStackTrace(); // TODO: Handle exceptions meaningfully
        }
    }
}
