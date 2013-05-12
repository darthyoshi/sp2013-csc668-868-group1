package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.BlockTree;
import org.openmrs.module.dssmodule.interpreter.ASTInterpreter;
import org.openmrs.module.dssmodule.interpreter.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class BlockInterpreter implements ASTInterpreter<BlockTree> {
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
