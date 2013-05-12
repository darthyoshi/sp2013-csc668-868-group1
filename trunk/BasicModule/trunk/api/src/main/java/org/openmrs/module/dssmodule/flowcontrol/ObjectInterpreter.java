package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.ObjectTree;
import org.openmrs.module.dssmodule.interpreter.ASTInterpreter;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.interpreter.ExecutionContext;
import org.openmrs.module.dssmodule.interpreter.NamingContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ObjectInterpreter implements ASTInterpreter<ObjectTree> {

    public Object interpret(ObjectTree tree, ExecutionContext context, ASTVisitor visitor) {
        // New object with same field names
        return context.getEvaluator().newAllocation(
                context.getEvaluator().castTo(NamingContext.class, 
                (DSSValue) tree.getKid(1).accept(visitor)).names());
    }

    
}
