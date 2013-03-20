package interpreter;

/**
 * Maintains things like variables & known functions.
 * @author woeltjen
 */
public abstract class ExecutionContext {
    /* NOTE: This class is abstract pending complete implementation. 
             Once complete, this should (probably?) be a concrete class. */
    
    public abstract void        beginScope(boolean hideVariables);
    public abstract void        declare(String variableName);
    public abstract void        set(String variableName, DSSValue value);
    public abstract DSSValue    get(String variableName);
    public abstract void        setFunction(String name, DSSFunction f);
    public abstract DSSFunction getFunction(String name);
    public abstract void        endScope();
    public abstract void        setReturnValue(DSSValue v);
    public abstract DSSValue    getReturnValue();
}
