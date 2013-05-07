package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.ObjectTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.NamingContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

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
