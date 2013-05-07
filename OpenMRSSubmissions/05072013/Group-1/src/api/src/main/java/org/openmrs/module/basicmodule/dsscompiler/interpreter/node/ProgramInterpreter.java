package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.ProgramTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

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
