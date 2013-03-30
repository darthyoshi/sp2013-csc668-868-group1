package interpreter.stub;

import interpreter.DSSValue;
import interpreter.Evaluator;
import interpreter.NamingContext;
import java.util.HashMap;
import java.util.Map;
import lexer.Symbol;

/**
 *
 * @author woeltjen
 */
public class StubEvaluator implements Evaluator {

    public <T> T castTo(Class<T> type, DSSValue value) {
        if (type.isAssignableFrom(value.getClass())) {
            return type.cast(value);
        }
        if (type.isAssignableFrom(Boolean.class)) {
            if (value instanceof StubInteger) {
                Boolean b = ((StubInteger)value).value != 0;
                return type.cast(b);
            } else {
                return type.cast(Boolean.FALSE);
            }
        }
        if (type.isAssignableFrom(Long.class) && value instanceof StubInteger) {
            return type.cast(Long.valueOf(((StubInteger) value).value));
        }
        return null;
    }    

    public DSSValue evaluate(DSSValue leftOperand, String operator, DSSValue rightOperand) {
        if (leftOperand instanceof StubInteger && rightOperand instanceof StubInteger) {
            long a = ((StubInteger)leftOperand).value;
            long b = ((StubInteger)rightOperand).value;
            if (operator.equals("+")) {            
                return new StubInteger(a+b);
            }
            if (operator.equals("-")) {
                return new StubInteger(a-b);
            }
            if (operator.equals("<")) {
                return new StubInteger((a < b) ? 1 : 0);
            }
        }
        return new StubInteger(0);
    }

    public DSSValue evaluateLiteral(Symbol s) {
        String literal = s.toString();
        try {
            return new StubInteger(Long.parseLong(literal));
        } catch (Exception e) {
            return new StubString(literal);//new StubInteger(0);
        }
    }

    public DSSValue newAllocation(String... fields) {
        StubObject o = new StubObject();
        for (String f : fields) {
            o.set(f, new StubInteger(0));
        }
        return o;
    }

    public DSSValue toDSSValue(Object javaObject) {
        return null;
    }

    public StubEvaluator() {
    }
    
    
    
    private abstract static class StubValue extends DSSValue {

        public StubValue() {
            super(null);
        }
    
        

        @Override
        public int complexity() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public DSSValue promote() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    public static class StubInteger extends StubValue {
        private long value;

        public StubInteger(long value) {
            this.value = value;
        }
        
        public String toString() {
            return "" + value;
        }
        
    }
    
    public static class StubString extends StubValue {
        private String value;
        
        public StubString(String value) {
            this.value = value;
        }
        
        public String toString() {
            return value;
        }
    }
    
    public static class StubObject extends StubValue implements NamingContext {
        private Map<String, StubValue> map = new HashMap<String, StubValue>();
        
        public DSSValue get(String name) {
            return map.get(name);
        }

        public String[] names() {
            String[] names = new String[map.keySet().size()];
            return map.keySet().toArray(names);
        }

        public void set(String name, DSSValue value) {
            map.put(name, (StubValue) value);
        }        
    }
    
}
