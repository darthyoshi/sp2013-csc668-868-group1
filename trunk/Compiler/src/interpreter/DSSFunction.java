package interpreter;

import visitor.ASTVisitor;

/**
 * Represents a callable function in DSS. This may be either an intrinsic, or 
 * once defined in the source code.
 * 
 * @author woeltjen
 */
public interface DSSFunction {
    public DSSValue call(ASTVisitor v, ExecutionContext context, DSSValue... args);
}
