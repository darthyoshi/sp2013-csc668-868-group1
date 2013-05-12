package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.FormalsTree;
import org.openmrs.module.dssmodule.interpreter.ASTInterpreter;
import org.openmrs.module.dssmodule.interpreter.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class FormalsInterpreter implements ASTInterpreter<FormalsTree> {
    public Object interpret(FormalsTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
