package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.ReturnTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ReturnInterpreter implements ASTInterpreter<ReturnTree> {    
    public Object interpret(ReturnTree tree, ExecutionContext context, ASTVisitor visitor) {
        Object result = tree.getKid(1).accept(visitor);
        if (result instanceof DSSValue) {
            context.setReturnValue((DSSValue)result);
        }
        return null;
    }
}
