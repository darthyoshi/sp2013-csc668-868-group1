package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.LiteralTree;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class LiteralInterpreter implements ASTInterpreter<LiteralTree> {

    public Object interpret(LiteralTree tree, ExecutionContext context, ASTVisitor visitor) {
        return context.getEvaluator().evaluateLiteral(tree.getSymbol());
    }
    
}
