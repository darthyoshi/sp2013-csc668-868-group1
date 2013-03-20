package interpreter.node;

import ast.ObjectTree;
import interpreter.ASTInterpreter;
import interpreter.DSSValue;
import interpreter.ExecutionContext;
import interpreter.NamingContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ObjectInterpreter implements ASTInterpreter<ObjectTree> {

    public Object interpret(ObjectTree tree, ExecutionContext context, ASTVisitor visitor) {
        // New object with same field names
        return context.getEvaluator().newAllocation(
                context.getEvaluator().castTo(NamingContext.class, 
                (DSSValue) tree.getKid(1).accept(visitor)).names());
    }

    
}
