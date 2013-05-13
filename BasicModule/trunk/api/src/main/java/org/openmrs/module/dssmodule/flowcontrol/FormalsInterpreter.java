package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.FormalsTree;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Handles interpretation of Formals in a DSS program. Note that these should 
 * never be visited directly under normal operation.
 * @author woeltjen
 */
public class FormalsInterpreter implements ASTInterpreter<FormalsTree> {
    @Override
    public Object interpret(FormalsTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
