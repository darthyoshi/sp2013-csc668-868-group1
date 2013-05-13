package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.CallTree;
import org.openmrs.module.dssmodule.ast.IdTree;
import org.openmrs.module.dssmodule.state.DSSFunction;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;

/**
 * Handles 'call' nodes in the abstract syntax tree for DSS1
 * @author woeltjen
 */
public class CallInterpreter implements ASTInterpreter<CallTree> {

    public CallInterpreter() {
    }

    public Object interpret(CallTree tree, ExecutionContext context, ASTVisitor visitor) {
        IdTree id = (IdTree) tree.getKid(1);        
        DSSFunction func = context.getFunction(id.getSymbol().toString());
        if (func == null) {
            System.err.println("DSS invoked missing function " + id.getSymbol().toString());
            return context.getEvaluator().toDSSValue(null);
        }
        DSSValue args[] = new DSSValue[tree.kidCount() - 1];
        for (int i = 0 ; i < args.length ; i++) {
            AST kid = tree.getKid(i+2);
            if (func.passAsIdentifier(i) && kid instanceof IdTree) {
                args[i] = context.getEvaluator().evaluateLiteral(((IdTree)kid).getSymbol());
            } else {
                args[i] = context.getEvaluator().toDSSValue(kid.accept(visitor));
            }
        }
        try {
            return context.getEvaluator().toDSSValue(func.call(args));        
        } catch (Exception e) {
            System.err.println("DSS interpreter calling " + id.getSymbol().toString());
            e.printStackTrace();
            return context.getEvaluator().toDSSValue(null);
        }
    }
    
}
