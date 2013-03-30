package interpreter.node;

import ast.LiteralTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class LiteralInterpreter implements ASTInterpreter<LiteralTree> {

    public Object interpret(LiteralTree tree, ExecutionContext context, ASTVisitor visitor) {
        return context.getEvaluator().evaluateLiteral(tree.getSymbol());
    }
    
}
