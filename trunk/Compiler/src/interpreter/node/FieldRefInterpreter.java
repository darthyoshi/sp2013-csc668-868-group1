package interpreter.node;

import ast.FieldRefTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class FieldRefInterpreter implements ASTInterpreter<FieldRefTree> {

    public FieldRefInterpreter() {
    }

    public Object interpret(FieldRefTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
