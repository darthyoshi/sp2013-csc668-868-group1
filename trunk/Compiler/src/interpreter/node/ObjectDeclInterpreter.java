package interpreter.node;

import ast.AST;
import ast.IdTree;
import ast.ObjectDeclTree;
import interpreter.ASTInterpreter;
import interpreter.ExecutionContext;
import java.util.List;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ObjectDeclInterpreter implements ASTInterpreter<ObjectDeclTree> {

    public Object interpret(ObjectDeclTree tree, ExecutionContext context, ASTVisitor visitor) {
        List<AST> kids = tree.getKids();
        String[] fields = new String[kids.size() - 1];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = ((IdTree) kids.get(i+1)).getSymbol().toString();
        }
        return context.getEvaluator().newAllocation(fields);
    }
    
}
