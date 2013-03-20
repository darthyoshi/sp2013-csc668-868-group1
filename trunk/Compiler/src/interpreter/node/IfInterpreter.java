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
        AST branch = evaluate(tree.getKid(0), visitor) ? tree.getKid(1) : tree.getKid(2);
        if (branch != null) {
            branch.accept(visitor);
        }
        return null;
    }   
    private boolean evaluate(AST t, ASTVisitor v) {
        Object result = t.accept(v);
        if (result instanceof DSSValue) {
            return ((DSSValue)result).asBoolean(); 
        }
        return false;
    }

}
