package org.openmrs.module.dssmodule.flowcontrol;

import java.util.ArrayList;
import java.util.List;
import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.ListTree;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Handles interpretation of List literals
 * @author woeltjen
 */
public class ListInterpreter implements ASTInterpreter<ListTree> {
    @Override
    public Object interpret(ListTree tree, ExecutionContext context, ASTVisitor visitor) {
        List<Object> values = new ArrayList<Object>();
        for (AST kid : tree.getKids()) {
            values.add(kid.accept(visitor));
        }
        return context.getEvaluator().toDSSValue(values);
    }
}
