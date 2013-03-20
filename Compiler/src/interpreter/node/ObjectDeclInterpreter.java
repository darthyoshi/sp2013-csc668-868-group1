package interpreter.node;

import ast.ObjectDeclTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ObjectDeclInterpreter implements ASTInterpreter<ObjectDeclTree> {

    public ObjectDeclInterpreter() {
    }

    public Object interpret(ObjectDeclTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
