package interpreter;

/**
 * Represents a callable function in DSS. This may be either an intrinsic, or 
 * once defined in the source code.
 * 
 * @author woeltjen
 */
public interface DSSFunction {
    public Object call(Object... args);
}
