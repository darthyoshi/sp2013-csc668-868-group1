package interpreter.node;

import ast.AST;
import ast.WhileTree;
import interpreter.ASTInterpreter;
import interpreter.DSSValue;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class WhileInterpreter implements ASTInterpreter<WhileTree> {

    public Object interpret(WhileTree tree, ExecutionContext context, ASTVisitor visitor) {
        while (evaluate(tree.getKid(1), context, visitor)) {
            tree.getKid(2).accept(visitor); // Execute block
        }
        return null;
    }
    
    private boolean evaluate(AST t, ExecutionContext context, ASTVisitor v) {
        Object result = t.accept(v);
        if (result instanceof DSSValue) {
            return context.getEvaluator().castTo(Boolean.class, (DSSValue)result);
        }
        return false;
    }
    
}
