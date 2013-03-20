package interpreter.stub;

import ast.AST;
import interpreter.DSSFunction;
import interpreter.DSSValue;
import interpreter.InterpreterVisitor;
import parser.Parser;
import visitor.PrintVisitor;

/**
 *
 * @author woeltjen
 */
public class StubInterpreter {
    private StubContext context = new StubContext();
    private InterpreterVisitor visitor = new InterpreterVisitor(context);
    
    public StubInterpreter() {
        context.setFunction("alert", print);
    }
    
    public void interpret (AST ast) {
        ast.accept(visitor);
    }
    
    public static void main(String[] args) {
        try {
            String dss = args.length > 1 ? args[0] : "fib.dss";
            AST ast = new Parser(dss).execute();
            ast.accept(new PrintVisitor());
            new StubInterpreter().interpret(ast);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private DSSFunction print = new DSSFunction() {
        public DSSValue call(DSSValue... args) {
            for (DSSValue v : args) {
                System.out.println(v.toString());
            }
            return null;
        }        
    };
}
