package interpreter.node;

import ast.AST;
import ast.AssignTree;
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
public class AssignInterpreter implements ASTInterpreter<AssignTree> {

    public AssignInterpreter() {
    }

    public Object interpret(AssignTree tree, ExecutionContext context, ASTVisitor visitor) {
        AST leftKid = tree.getKid(1);
        NamingContext targetContext = null;
        IdTree id = null;
        
        if (leftKid instanceof IdTree) {
            targetContext = context;            
            id = (IdTree) leftKid;
        } else if (leftKid instanceof FieldRefTree) {
            DSSValue result = (DSSValue) leftKid.getKid(1).accept(visitor);
            targetContext = context.getEvaluator().castTo(NamingContext.class, result);
            id = (IdTree) (leftKid.getKid(2));
        }
        
        if (targetContext != null && id != null) {
            targetContext.set(id.getSymbol().toString(), 
                    (DSSValue) tree.getKid(2).accept(visitor));
        } else {
            throw new UnsupportedOperationException("Can't assign to left oper");
        }
            
        return null;
    }
    
}
