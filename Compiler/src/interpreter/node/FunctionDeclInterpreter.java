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
        IdTree id = (IdTree) (tree.getKid(1));
        String functionName = id.getSymbol().toString();
        DSSFunction function = new DeclaredFunction(
                (FormalsTree) (tree.getKid(2)), (BlockTree) (tree.getKid(3)), 
                context, visitor);
        context.setFunction(functionName, function);
        return null;
    }
    
    private static class DeclaredFunction implements DSSFunction {
        private FormalsTree formals;
        private BlockTree   block;
        private ExecutionContext context;
        private ASTVisitor visitor;

        public DeclaredFunction(FormalsTree formals, BlockTree block, ExecutionContext context, ASTVisitor visitor) {
            this.formals = formals;
            this.block = block;
            this.context = context;
            this.visitor = visitor;
        }

        

        public DSSValue call(DSSValue... args) {
            List<AST> formalIds = formals.getKids();
            if (formalIds.size() != args.length) {
                throw new IllegalArgumentException("Mismatched arguments in function call");
            }            
            
            context.beginScope(true);
            for (int i = 0; i < formalIds.size(); i++) {
                context.set(((IdTree)formalIds.get(i)).getSymbol().toString(), args[i]);
            }
            block.accept(visitor);
            DSSValue result = context.getReturnValue();
            context.setReturnValue(null); // Clear return value
            context.endScope();
            
            return result;
        }
        
    }
}
