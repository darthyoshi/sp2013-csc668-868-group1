package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.ForTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

/**
 * Note that DSS grammar defines this as a "for-each" style loop
 * @author woeltjen
 */
public class ForInterpreter implements ASTInterpreter<ForTree> {
    public Object interpret(ForTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
