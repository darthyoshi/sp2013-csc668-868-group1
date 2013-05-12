package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.ReturnTree;
import org.openmrs.module.dssmodule.interpreter.ASTInterpreter;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.interpreter.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

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
