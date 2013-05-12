package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.IdTree;
import org.openmrs.module.dssmodule.ast.ObjectDeclTree;
import org.openmrs.module.dssmodule.interpreter.ASTInterpreter;
import org.openmrs.module.dssmodule.interpreter.ExecutionContext;
import java.util.List;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

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
