package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.ReturnTree;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Handle interpretation of return statements in a DSS1 program.
 * @author woeltjen
 */
public class ReturnInterpreter implements ASTInterpreter<ReturnTree> {    
    @Override
    public Object interpret(ReturnTree tree, ExecutionContext context, ASTVisitor visitor) {
        Object result = tree.getKid(1).accept(visitor);
        if (result instanceof DSSValue) {
            context.setReturnValue((DSSValue)result);
        }
        return null;
    }
}
