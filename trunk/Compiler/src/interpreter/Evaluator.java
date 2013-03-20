package interpreter;

/**
 * Responsible for evaluating simple expressions; the fine-grained semantics 
 * of the language.
 * @author woeltjen
 */
public interface Evaluator {
    public DSSValue evaluate(DSSValue leftOperand, String operator, DSSValue rightOperand);
    
    /**
     * Try to cast this value to another type (likely a normal Java type).
     * Support may vary upon implementation, but should include Boolean and 
     * String. Returns null if the cast is invalid.
     * 
     * @param <T>
     * @param type
     * @param value
     * @return 
     */
    public <T> T castTo(Class<T> type, DSSValue value);
    
    public DSSValue evaluateLiteral(String literal);
    
    public DSSValue newAllocation(String... fields);
}
