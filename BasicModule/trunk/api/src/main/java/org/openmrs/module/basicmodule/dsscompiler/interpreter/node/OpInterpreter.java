package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.ast.OpTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

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
