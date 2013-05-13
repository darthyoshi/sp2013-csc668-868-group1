package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.ProgramTree;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Interpret a program tree. This is typically the top-level node of an AST.
 * @author woeltjen
 */
public class ProgramInterpreter implements ASTInterpreter<ProgramTree> {
    @Override
    public Object interpret(ProgramTree tree, ExecutionContext context, ASTVisitor visitor) {
        visitor.visitKids(tree);
        return null;
    }    
}
