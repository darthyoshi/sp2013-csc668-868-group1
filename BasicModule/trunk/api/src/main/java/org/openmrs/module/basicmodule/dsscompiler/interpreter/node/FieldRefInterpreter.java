package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.FieldRefTree;
import org.openmrs.module.basicmodule.dsscompiler.ast.IdTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.NamingContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

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
