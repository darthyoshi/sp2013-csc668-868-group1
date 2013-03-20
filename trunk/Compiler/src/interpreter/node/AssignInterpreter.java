package interpreter.node;

import ast.AssignTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class AssignInterpreter implements ASTInterpreter<AssignTree> {

    public AssignInterpreter() {
    }

    public Object interpret(AssignTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
