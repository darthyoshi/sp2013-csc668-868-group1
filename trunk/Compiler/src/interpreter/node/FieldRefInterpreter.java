package interpreter.node;

import ast.FieldRefTree;
import ast.IdTree;
import interpreter.ASTInterpreter;
import interpreter.DSSValue;
import interpreter.ExecutionContext;
import interpreter.NamingContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class FieldRefInterpreter implements ASTInterpreter<FieldRefTree> {

    public Object interpret(FieldRefTree tree, ExecutionContext context, ASTVisitor visitor) {
        return context.getEvaluator().castTo(NamingContext.class, 
                (DSSValue) tree.getKid(1).accept(visitor)).get( 
                    ((IdTree) tree.getKid(2)).getSymbol().toString());
    }
    
}
