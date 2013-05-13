package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.ProgramTree;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ProgramInterpreter implements ASTInterpreter<ProgramTree> {
    public Object interpret(ProgramTree tree, ExecutionContext context, ASTVisitor visitor) {
        visitor.visitKids(tree);
        return null;
    }    
}
