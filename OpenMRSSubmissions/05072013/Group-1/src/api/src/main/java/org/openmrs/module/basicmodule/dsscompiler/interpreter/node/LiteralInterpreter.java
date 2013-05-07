package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.LiteralTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class LiteralInterpreter implements ASTInterpreter<LiteralTree> {

    public Object interpret(LiteralTree tree, ExecutionContext context, ASTVisitor visitor) {
        return context.getEvaluator().evaluateLiteral(tree.getSymbol());
    }
    
}
