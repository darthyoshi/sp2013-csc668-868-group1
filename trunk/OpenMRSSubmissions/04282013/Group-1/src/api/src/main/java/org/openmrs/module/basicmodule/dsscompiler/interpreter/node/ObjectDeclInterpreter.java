package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.ast.IdTree;
import org.openmrs.module.basicmodule.dsscompiler.ast.ObjectDeclTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import java.util.List;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

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
