package interpreter.node;

import ast.OpTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class OpInterpreter implements ASTInterpreter<OpTree> {

    public OpInterpreter() {
    }

    public Object interpret(OpTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
