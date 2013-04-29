package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.ast.BlockTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

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
