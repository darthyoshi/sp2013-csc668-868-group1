package interpreter.node;

import ast.*;
import interpreter.ASTInterpreter;
import interpreter.DSSFunction;
import interpreter.DSSValue;
import interpreter.ExecutionContext;
import java.util.List;
import visitor.ASTVisitor;

/**
 *
 * @author woeltjen
 */
public class FunctionDeclInterpreter implements ASTInterpreter<FunctionDeclTree> {

    public FunctionDeclInterpreter() {
    }

    public Object interpret(FunctionDeclTree tree, ExecutionContext context, ASTVisitor visitor) {
        IdTree id = (IdTree) tree.getKid(0);
        context.setFunction(id.getSymbol().toString(), 
                new DeclaredFunction((FormalsTree) tree.getKid(1), (BlockTree) tree.getKid(2)));
        return null;
    }
    
    private static class DeclaredFunction implements DSSFunction {
        private FormalsTree formals;
        private BlockTree   block;

        public DeclaredFunction(FormalsTree formals, BlockTree block) {
            this.formals = formals;
            this.block = block;
        }

        public DSSValue call(ASTVisitor v, ExecutionContext context, DSSValue... args) {
            List<AST> formalIds = formals.getKids();
            if (formalIds.size() != args.length) {
                throw new IllegalArgumentException("Mismatched arguments in function call");
            }            
            
            context.beginScope(true);
            for (int i = 0; i < formalIds.size(); i++) {
                context.set(((IdTree)formalIds.get(i)).getSymbol().toString(), args[i]);
            }
            block.accept(v);
            DSSValue result = context.getReturnValue();
            context.setReturnValue(null); // Clear return value
            context.endScope();
            
            return result;
        }
        
    }
}
