package interpreter.node;

import ast.ListTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ListInterpreter implements ASTInterpreter<ListTree> {
    public Object interpret(ListTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
