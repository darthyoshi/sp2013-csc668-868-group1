package interpreter.node;

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
        IdTree id = (IdTree) tree.getKid(0);        
        DSSFunction func = context.getFunction(id.getSymbol().toString());
        DSSValue args[] = new DSSValue[tree.kidCount() - 1];
        for (int i = 0 ; i < args.length ; i++) {
            args[i] = (DSSValue) tree.getKid(i+1).accept(visitor);
        }        
        return func.call(visitor, context, args);        
    }
    
}
