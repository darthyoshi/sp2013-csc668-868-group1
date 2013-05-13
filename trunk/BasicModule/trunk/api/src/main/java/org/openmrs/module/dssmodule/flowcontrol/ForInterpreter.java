package org.openmrs.module.dssmodule.flowcontrol;

import java.util.List;
import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.ForTree;
import org.openmrs.module.dssmodule.ast.IdTree;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

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
