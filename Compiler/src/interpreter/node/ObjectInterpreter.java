package interpreter.node;

import ast.ObjectTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ObjectInterpreter implements ASTInterpreter<ObjectTree> {

    public Object interpret(ObjectTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
