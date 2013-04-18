package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import java.util.List;
import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.ast.ForTree;
import org.openmrs.module.basicmodule.dsscompiler.ast.IdTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

/**
 * Note that DSS grammar defines this as a "for-each" style loop
 * @author woeltjen
 */
public class ForInterpreter implements ASTInterpreter<ForTree> {
    public Object interpret(ForTree tree, ExecutionContext context, ASTVisitor visitor) {
        AST leftKid = tree.getKid(1);
        if (leftKid instanceof IdTree) {
            String id = ((IdTree) leftKid).getSymbol().toString();
            List list = context.getEvaluator().
                    castTo(List.class, (DSSValue) tree.getKid(2).accept(visitor));
            if (list != null) {
                for (Object obj : list) {
                    context.set(id, context.getEvaluator().toDSSValue(obj));
                    tree.getKid(3).accept(visitor);
                }
            }
        }
        return null;
    }    
}
