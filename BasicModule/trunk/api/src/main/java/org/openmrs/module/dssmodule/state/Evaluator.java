package org.openmrs.module.dssmodule.state;

import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.lexer.Symbol;

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
    
    /**
     * Evaluate this literal and convert it to an appropriate DSSValue. 
     * Note that this is also where raw identifiers are handled (such as 
     * in a read(patientId, cd4counts)) so this method needs to recognize 
     * those as well.
     * @param literal
     * @return 
     */
    public DSSValue evaluateLiteral(Symbol literal);

    /**
     * Allocate a new object (probably DSSObject) containing the 
     * specified fields.
     * @param fields
     * @return 
     */
    public DSSValue newAllocation(String... fields);    
    
    /**
     * Convert a regular Java object to an analogous DSSValue, if possible.
     * @param javaObject
     * @return 
     */
    public DSSValue toDSSValue(Object javaObject);
}
