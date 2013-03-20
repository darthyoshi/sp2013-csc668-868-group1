package interpreter.node;

import ast.ProgramTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ProgramInterpreter implements ASTInterpreter<ProgramTree> {
    public Object interpret(ProgramTree tree, ExecutionContext context, ASTVisitor visitor) {
        visitor.visitKids(tree);
        return null;
    }    
}
