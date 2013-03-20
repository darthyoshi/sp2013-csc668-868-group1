package interpreter.node;

import ast.ForTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 * Note that DSS grammar defines this as a "for-each" style loop
 * @author woeltjen
 */
public class ForInterpreter implements ASTInterpreter<ForTree> {
    public Object interpret(ForTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
