package org.openmrs.module.basicmodule.dsscompiler.interpreter.node;

import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.ast.CallTree;
import org.openmrs.module.basicmodule.dsscompiler.ast.IdTree;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ASTInterpreter;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class CallInterpreter implements ASTInterpreter<CallTree> {

    public CallInterpreter() {
    }

    public Object interpret(CallTree tree, ExecutionContext context, ASTVisitor visitor) {
        IdTree id = (IdTree) tree.getKid(1);        
        DSSFunction func = context.getFunction(id.getSymbol().toString());
        DSSValue args[] = new DSSValue[tree.kidCount() - 1];
        for (int i = 0 ; i < args.length ; i++) {
            AST kid = tree.getKid(i+2);
            if (func.passAsIdentifier(i) && kid instanceof IdTree) {
                args[i] = context.getEvaluator().evaluateLiteral(((IdTree)kid).getSymbol());
            } else {
                args[i] = (DSSValue) kid.accept(visitor);
            }
        }        
        return func.call(args);        
    }
    
}
