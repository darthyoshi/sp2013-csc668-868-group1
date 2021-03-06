package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.IfTree;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Handles interpretation of "if" and "elsif" sub-trees.
 * @author woeltjen
 */
public class IfInterpreter implements ASTInterpreter<AST> {
    @Override
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
