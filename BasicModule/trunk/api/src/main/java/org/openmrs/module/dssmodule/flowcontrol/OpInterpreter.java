package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.OpTree;
import org.openmrs.module.dssmodule.interpreter.ASTInterpreter;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.interpreter.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class OpInterpreter implements ASTInterpreter<OpTree> {

    public Object interpret(OpTree tree, ExecutionContext context, ASTVisitor visitor) {
        AST leftKid = tree.getKid(1);
        AST rightKid = tree.getKid(2);
        return context.getEvaluator().evaluate( 
                (leftKid != null) ? ((DSSValue) leftKid.accept(visitor)) : null,
                tree.getSymbol().toString(),
                (rightKid != null) ? ((DSSValue) rightKid.accept(visitor)) : null);
    }
    
}
