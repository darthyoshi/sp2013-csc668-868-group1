package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.ObjectTree;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.state.NamingContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Handles interpretation of object sub-trees, used in DSS1 to instantiate new 
 * objects based on prototypes.
 * @author woeltjen
 */
public class ObjectInterpreter implements ASTInterpreter<ObjectTree> {

    @Override
    public Object interpret(ObjectTree tree, ExecutionContext context, ASTVisitor visitor) {
        // New object with same field names
        return context.getEvaluator().newAllocation(
                context.getEvaluator().castTo(NamingContext.class, 
                (DSSValue) tree.getKid(1).accept(visitor)).names());
    }

    
}
