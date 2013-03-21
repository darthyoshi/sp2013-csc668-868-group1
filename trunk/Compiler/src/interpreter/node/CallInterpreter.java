package interpreter.node;

import ast.AST;
import ast.CallTree;
import ast.IdTree;
import interpreter.ASTInterpreter;
import interpreter.DSSFunction;
import interpreter.DSSValue;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class CallInterpreter implements ASTInterpreter<CallTree> {

    public CallInterpreter() {
    }

    public Object interpret(CallTree tree, ExecutionContext context, ASTVisitor visitor) {
        IdTree id = (IdTree) tree.getKid(1);        
        DSSFunction func = context.getFunction(id.getSymbol().toString());
        DSSValue args[] = new DSSValue[tree.kidCount() - 1];
        for (int i = 0 ; i < args.length ; i++) {
            AST kid = tree.getKid(i+2);
            if (func.passAsIdentifier(i)) {
                args[i] = context.getEvaluator().evaluateLiteral(((IdTree)kid).getSymbol().toString());
            } else {
                args[i] = (DSSValue) kid.accept(visitor);
            }
        }        
        return func.call(args);        
    }
    
}
