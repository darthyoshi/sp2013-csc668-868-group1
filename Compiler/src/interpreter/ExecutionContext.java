package interpreter;

/**
 * Maintains things like variables & known functions.
 * @author woeltjen
 */
public abstract class ExecutionContext {
    /* NOTE: This class is abstract pending complete implementation. 
             Once complete, this should (probably?) be a concrete class. */
    
    public abstract void        beginScope();
    public abstract void        declare(String variableName);
    public abstract void        set(String variableName, Object value);
    public abstract Object      get(String variableName);
    public abstract void        setFunction(String name, DSSFunction f);
    public abstract DSSFunction getFunction(String name);
    public abstract void        endScope();
    
}
