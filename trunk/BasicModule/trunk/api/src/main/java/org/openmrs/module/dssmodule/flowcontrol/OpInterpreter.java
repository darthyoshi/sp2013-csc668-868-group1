package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.OpTree;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Handles operations in DSS1 (arithmetic, logical, et cetera). Note that 
 * specific semantics are deferred to the evaluator.
 * @author woeltjen
 */
public class OpInterpreter implements ASTInterpreter<OpTree> {
    @Override
    public Object interpret(OpTree tree, ExecutionContext context, ASTVisitor visitor) {
        AST leftKid = tree.getKid(1);
        AST rightKid = tree.getKid(2);
        return context.getEvaluator().evaluate( 
                (leftKid != null) ? ((DSSValue) leftKid.accept(visitor)) : null,
                tree.getSymbol().toString(),
                (rightKid != null) ? ((DSSValue) rightKid.accept(visitor)) : null);
    }
    
}
