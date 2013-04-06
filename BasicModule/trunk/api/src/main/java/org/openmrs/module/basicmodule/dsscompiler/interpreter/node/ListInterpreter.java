package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.ListTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ListInterpreter implements ASTInterpreter<ListTree> {
    public Object interpret(ListTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
