package interpreter;

import ast.AST;
import interpreter.value.DSSEvaluator;
import parser.Parser;

/**
 *
 * @author woeltjen
 */
public class Interpreter {
    private static final String DEFAULT_DSS_FILE = "math.dss";
    
    private ExecutionContext context = new ExecutionContext(new DSSEvaluator());    
    
    public Interpreter() {
        context.setFunction("alert", new DSSFunction() {
            @Override
            public DSSValue call(DSSValue... args) {
                for (DSSValue arg : args) {
                    System.out.println(arg.toString());
                }
                return DSSValue.DSS_NULL;
            }            
        });
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
