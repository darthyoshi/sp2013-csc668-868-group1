package interpreter.node;

import ast.FormalsTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class FormalsInterpreter implements ASTInterpreter<FormalsTree> {

    public FormalsInterpreter() {
    }

    public Object interpret(FormalsTree tree, ExecutionContext context, ASTVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
