/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.interpreter.value;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.Evaluator;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openmrs.module.basicmodule.dsscompiler.lexer.Symbol;

/**
 *
 * @author woeltjen
 */
public class DSSEvaluator implements Evaluator {
    private Map<String, DSSOperator> operators = new HashMap<String, DSSOperator>();

    public DSSEvaluator() {
        operators.put("+",  new MethodLookup("add"));
        operators.put("-",  new MethodLookup("sub"));
        operators.put("/",  new MethodLookup("div"));
        operators.put("*",  new MethodLookup("mul"));
        operators.put("**", new MethodLookup("exp"));
        operators.put("&",  new MethodLookup("and"));
        operators.put("|",  new MethodLookup("or"));
        operators.put("||", new MethodLookup("concat"));
        operators.put("==", new MethodLookup("equal"));
        operators.put("<",  new MethodLookup("lessThan"));
        operators.put("<=", new MethodLookup("lessThanOrEqual"));        
    }
    
    public <T> T castTo(Class<T> type, DSSValue value) {
        if (type.isAssignableFrom(value.getClass())) {
            return type.cast(value);
        }
        if (type.isAssignableFrom(Boolean.class)) {
            return (T) (value instanceof DSSBoolean ? ((DSSBoolean)value).getJavaObject() : Boolean.FALSE);
        }
        return null;
    }

    public DSSValue evaluate(DSSValue leftOperand, String operator, DSSValue rightOperand) {
        DSSOperator op = operators.get(operator);
        
        if (op == null) {
            return DSSValue.DSS_NULL;
        }
        
        if (leftOperand.complexity() < rightOperand.complexity()) {
            leftOperand = rightOperand.cast(leftOperand);
        } else if (rightOperand.complexity() < leftOperand.complexity()) {
            rightOperand = leftOperand.cast(rightOperand);
        }
        
        return op.apply(leftOperand, rightOperand);
    }

    public DSSValue evaluateLiteral(Symbol symbol) {
        String literal = symbol.toString();

        try {
            switch (symbol.getKind()) {
                case STRing:
                    return toDSSValue(literal);
                case INTeger: // Floats are tagged as INTegers for some reason
                    return literal.contains(".") ? 
                            toDSSValue(Double.valueOf(literal)) : 
                            toDSSValue(Long.valueOf(literal));
                case True:
                    return DSSBoolean.TRUE;
                case False:
                    return DSSBoolean.FALSE;
                case Null:
                    return DSSValue.DSS_NULL;
            }
        } catch (Exception e) {
            // fall down to default 
        }

 
//        try {
//            return toDSSValue(Long.valueOf(literal));
//        } catch (Exception e) {
//            // Keep trying...
//        }
//        
//        try {
//            return toDSSValue(Double.valueOf(literal));
//        } catch (Exception e) {
//            // Keep trying...
//        }
//
//        try {
//            return toDSSValue(Float.valueOf(literal));
//        } catch (Exception e) {
//            // Keep trying...
//        }
//        
//        if ("true".equals(literal)) {
//            return DSSBoolean.TRUE;
//        }
//        
//        if ("false".equals(literal)) {
//            return DSSBoolean.FALSE;
//        }
//        
//        if ("null".equals(literal)) {
//            return DSSValue.DSS_NULL;
//        }
        
        return new DSSString(literal);
    }

    public DSSValue newAllocation(String... fields) {
        Map<String, DSSValue<?>> newMap = new HashMap<String, DSSValue<?>>();
        for (String field : fields) {
            newMap.put(field, DSSValue.DSS_NULL);
        }
        return new DSSObject(newMap);
    }

    public DSSValue toDSSValue(Object javaObject) {
        if (javaObject == null) {
            return DSSValue.DSS_NULL;
        }
        
        if (javaObject instanceof DSSValue) {
            return (DSSValue) javaObject;
        }
        
        if (javaObject instanceof Number) {
            if (javaObject instanceof Float || javaObject instanceof Double) {
                return new DSSFloat( ((Number)javaObject).doubleValue());
            } else {
                return new DSSInteger(((Number)javaObject).longValue());
            }
        }
        
        if (javaObject instanceof Object[]) {
            List<DSSValue<?>> newList = new ArrayList<DSSValue<?>>();
            for (Object o : ((Object[])javaObject)) {
                newList.add(toDSSValue(o));
            }
            return new DSSList(newList);
        }
        
        if (javaObject instanceof Boolean) {
            return ((Boolean)javaObject).booleanValue() ? DSSBoolean.TRUE : DSSBoolean.FALSE;
        }
        
        if (javaObject instanceof Date) {
            return new DSSTime((Date)javaObject);
        }
        
        return new DSSString(javaObject.toString());
    }
    
    
    private static interface DSSOperator {
        public DSSValue<?> apply(DSSValue<?> left, DSSValue<?> right);
    }
    
    /**
     * Convenience class to map operators to methods by name, instead of 
     * a whole ton of DSSOperator implementations
     */
    private static class MethodLookup implements DSSOperator {
        private String methodName;

        public MethodLookup(String methodName) {
            this.methodName = methodName;
        }
        
        public DSSValue<?> apply(DSSValue<?> left, DSSValue<?> right) {
            try {
                Method m = left.getClass().getMethod(methodName, DSSValue.class);
                return (DSSValue) m.invoke(left, right);
            } catch (Exception e) {
                return DSSValue.DSS_NULL;
            }
        }
    }
}
