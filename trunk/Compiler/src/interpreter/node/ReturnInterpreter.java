package interpreter.node;

import ast.ReturnTree;
import interpreter.ASTInterpreter;
import interpreter.DSSValue;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ReturnInterpreter implements ASTInterpreter<ReturnTree> {    
    public Object interpret(ReturnTree tree, ExecutionContext context, ASTVisitor visitor) {
        Object result = tree.getKid(0).accept(visitor);
        if (result instanceof DSSValue) {
            context.setReturnValue((DSSValue)result);
        }
        return null;
    }
}
