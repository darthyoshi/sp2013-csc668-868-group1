package interpreter.node;

import ast.AST;
import ast.IfTree;
import interpreter.ASTInterpreter;
import interpreter.DSSValue;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * Also an ElsifInterpreter
 * @author woeltjen
 */
public class IfInterpreter implements ASTInterpreter<AST> {
    public Object interpret(AST tree, ExecutionContext context, ASTVisitor visitor) {
        AST branch = evaluate(tree.getKid(1), context, visitor) ? 
                tree.getKid(2) : tree.getKid(3);
        if (branch != null) {
            branch.accept(visitor);
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
