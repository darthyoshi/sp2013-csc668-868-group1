package org.openmrs.module.basicmodule.dsscompiler.interpreter;

import java.lang.reflect.Method;
import java.util.*;
import org.openmrs.module.basicmodule.dsscompiler.lexer.Symbol;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueFactory;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueList;

/**
 *
 * @author woeltjen
 */
public class DSSEvaluator implements Evaluator {
    private Map<String, DSSOperator> operators = new HashMap<String, DSSOperator>();
    private static final DSSValue TRUE = DSSValueFactory.getDSSValue(true);
       
    public DSSEvaluator() {
        operators.put("+",  new MethodLookup("add"));
        operators.put("-",  new MethodLookup("sub"));
        operators.put("/",  new MethodLookup("div"));
        operators.put("*",  new MethodLookup("mult"));
        operators.put("**", new MethodLookup("power"));
        operators.put("&",  new MethodLookup("and"));
        operators.put("|",  new MethodLookup("or"));
        operators.put("||", new MethodLookup("concat"));
        operators.put("==", new BooleanMethodLookup("equal"));
        operators.put("<",  new BooleanMethodLookup("lessthan"));
        operators.put("<=", new BooleanMethodLookup("lessthanequal"));        
    }
    
    /**
     * Convert a DSSValue to another Java type. May return null (Java null) 
     * if the conversion does not make sense.
     * @param <T>
     * @param type
     * @param value
     * @return 
     */
    public <T> T castTo(Class<T> type, DSSValue value) {
        if (value != null) {
            if (type.isAssignableFrom(value.getClass())) {
                return type.cast(value);
            } else if (type.isAssignableFrom(Boolean.class)) {
                return type.cast(Boolean.valueOf(TRUE.equal(value)));
            } else if (type.isAssignableFrom(String.class)) {
                return type.cast(value.toString());
            } else if (type.isAssignableFrom(Integer.class) ||
                    type.isAssignableFrom((Long.class))) {
                return type.cast(value.toInt());
            } else if (type.isAssignableFrom(Double.class) ||
                    type.isAssignableFrom((Float.class))) {
                return type.cast(value.toFloat());
            } else if (type.isAssignableFrom(List.class)) {
                List<DSSValue> list = new ArrayList<DSSValue>();
                if (value instanceof DSSValueList) {
                    for (int i = 0; i < value.length(); i++) {
                        list.add(((DSSValueList)value).get(i));
                    }                    
                } else {
                    list.add(value);
                }
                return type.cast(list);
            } else if (type.isAssignableFrom(Map.class)) {
                Map<String, DSSValue> map = new HashMap<String, DSSValue>();
                // Todo - DSSValueObject
                return type.cast(map);
            } else if (type.isAssignableFrom(Date.class)) {
                return type.cast(new Date(value.toLong()));
            }
        }
        return null;
    }

    public DSSValue evaluate(DSSValue leftOperand, String operator, DSSValue rightOperand) {
        DSSOperator op = operators.get(operator);
        
        if (op == null) {
            return null; //DSSValue.DSS_NULL;
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
                    return toDSSValue(Boolean.TRUE);
                case False:
                    return toDSSValue(Boolean.FALSE);
                case Null:
                    return toDSSValue(null);
            }
        } catch (Exception e) {
            // fall down to default 
        }
        
        return toDSSValue(literal);
    }

    public DSSValue newAllocation(String... fields) {
        Map<String, DSSValue> map = new HashMap<String, DSSValue>();
        for (String field : fields) {
            map.put(field, DSSValueFactory.getDSSValue());
        }
        return DSSValueFactory.getDSSValue(map);
    }

    public DSSValue toDSSValue(Object javaObject) {
        if (javaObject == null) {
            return DSSValueFactory.getDSSValue();
        }
        
        if (javaObject instanceof DSSValue) {
            return (DSSValue) javaObject;
        }
        
        if (javaObject instanceof Number) {
            if (javaObject instanceof Float || javaObject instanceof Double) {
                return DSSValueFactory.getDSSValue( ((Number)javaObject).doubleValue());
            } else {
                return DSSValueFactory.getDSSValue(((Number)javaObject).longValue());
            }
        }
        
        // Handle arrays and lists
        if (javaObject instanceof Object[]) {
            javaObject = Arrays.asList((Object[]) javaObject);
        }        
        if (javaObject instanceof Collection) {
            Vector<DSSValue> dssValues = new Vector<DSSValue>();
            for (Object obj : (Collection)javaObject) {
                dssValues.add(toDSSValue(obj));
            }
            return DSSValueFactory.getDSSValue(dssValues);
        }
        
        if (javaObject instanceof Boolean) {
            return DSSValueFactory.getDSSValue((Boolean) javaObject);
        }
        
        if (javaObject instanceof Date) {
            return DSSValueFactory.getDSSValue((Date) javaObject);
        }
        
        return DSSValueFactory.getDSSValue(javaObject.toString());
    }
    
    
    private static interface DSSOperator {
        public DSSValue apply(DSSValue left, DSSValue right);
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
        
        public DSSValue apply(DSSValue left, DSSValue right) {
            try {
                Method m = left.getClass().getMethod(methodName, DSSValue.class);
                return (DSSValue) m.invoke(left, right);
            } catch (Exception e) {
                return null; //DSSValue.DSS_NULL;
            }
        }
    }
    
    private static class BooleanMethodLookup implements DSSOperator {
        private String methodName;

        public BooleanMethodLookup(String methodName) {
            this.methodName = methodName;
        }
        
        public DSSValue apply(DSSValue left, DSSValue right) {
            try {
                Method m = left.getClass().getMethod(methodName, DSSValue.class);
                boolean result = ((Boolean) m.invoke(left, right)).booleanValue();
                return DSSValueFactory.getDSSValue(result);
            } catch (Exception e) {
                return null; //DSSValue.DSS_NULL;
            }
        }
    }
}
