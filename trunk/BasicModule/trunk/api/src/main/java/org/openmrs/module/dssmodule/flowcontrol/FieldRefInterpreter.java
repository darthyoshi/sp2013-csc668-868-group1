package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.FieldRefTree;
import org.openmrs.module.dssmodule.ast.IdTree;
import org.openmrs.module.dssmodule.interpreter.ASTInterpreter;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.interpreter.ExecutionContext;
import org.openmrs.module.dssmodule.interpreter.NamingContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class FieldRefInterpreter implements ASTInterpreter<FieldRefTree> {

    public Object interpret(FieldRefTree tree, ExecutionContext context, ASTVisitor visitor) {
        return context.getEvaluator().castTo(NamingContext.class, 
                (DSSValue) tree.getKid(1).accept(visitor)).get( 
                    ((IdTree) tree.getKid(2)).getSymbol().toString());
    }
    
}
