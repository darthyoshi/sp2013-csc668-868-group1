package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.BlockTree;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Interprets Block sub-trees in a compiled AST
 * @author woeltjen
 */
public class BlockInterpreter implements ASTInterpreter<BlockTree> {
    @Override
    public Object interpret(BlockTree tree, ExecutionContext context, ASTVisitor visitor) {
        for (AST kid : tree.getKids()) {
            if (context.getReturnValue() != null) {
                break;
            }
            kid.accept(visitor);
        }
        return null;
    }
}
