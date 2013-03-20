package interpreter.node;

import ast.AST;
import ast.OpTree;
import interpreter.ASTInterpreter;
import interpreter.DSSValue;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class OpInterpreter implements ASTInterpreter<OpTree> {

    public Object interpret(OpTree tree, ExecutionContext context, ASTVisitor visitor) {
        AST leftKid = tree.getKid(1);
        AST rightKid = tree.getKid(2);
        return context.getEvaluator().evaluate( 
                (leftKid != null) ? ((DSSValue) leftKid.accept(visitor)) : null,
                tree.getSymbol().toString(),
                (rightKid != null) ? ((DSSValue) rightKid.accept(visitor)) : null);
    }
    
}
