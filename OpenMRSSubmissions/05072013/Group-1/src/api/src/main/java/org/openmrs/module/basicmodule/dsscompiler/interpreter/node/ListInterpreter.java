package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import java.util.ArrayList;
import java.util.List;
import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.ast.ListTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class ListInterpreter implements ASTInterpreter<ListTree> {
    public Object interpret(ListTree tree, ExecutionContext context, ASTVisitor visitor) {
        List<Object> values = new ArrayList<Object>();
        for (AST kid : tree.getKids()) {
            values.add(kid.accept(visitor));
        }
        return context.getEvaluator().toDSSValue(values);
    }
}
