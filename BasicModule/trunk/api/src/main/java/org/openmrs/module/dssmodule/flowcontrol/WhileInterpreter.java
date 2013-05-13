package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.WhileTree;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

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
