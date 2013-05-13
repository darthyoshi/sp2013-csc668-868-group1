package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.IdTree;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Evaluate an identifier. This evaluates as though it were an expression; 
 * interpreters that need to get the actual name of the identifier should 
 * look at the IdTree directly.
 * @author woeltjen
 */
public class IdInterpreter implements ASTInterpreter<IdTree> {
    public Object interpret(IdTree tree, ExecutionContext context, ASTVisitor visitor) {
        return context.get(tree.getSymbol().toString());
    }
}
