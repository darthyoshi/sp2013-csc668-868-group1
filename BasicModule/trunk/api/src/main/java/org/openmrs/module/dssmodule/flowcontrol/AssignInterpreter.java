package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.AssignTree;
import org.openmrs.module.dssmodule.ast.FieldRefTree;
import org.openmrs.module.dssmodule.ast.IdTree;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.state.NamingContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

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
