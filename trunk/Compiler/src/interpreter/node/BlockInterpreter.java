package interpreter.node;

import ast.AST;
import ast.BlockTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

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
