package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.IdTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

/**
 * Evaluate an identifier. This evaluates as though it were an expression; 
 * interpreters that need to get the actual name of the identifier should 
 * look at the IdTree directly.
 * @author woeltjen
 */
public class IdInterpreter implements ASTInterpreter<IdTree> {
    public Object interpret(IdTree tree, ExecutionContext context, ASTVisitor visitor) {
        return context.get(tree.getSymbol().toString());
    }
}
