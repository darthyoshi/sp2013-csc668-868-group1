package interpreter;

import interpreter.value.DSSBoolean;
import interpreter.value.DSSFloat;

public abstract class DSSValue<T>  {
    private T javaObject;
    private Long timeStamp;
    
    public DSSValue(T javaObject) {
        this.javaObject = javaObject;
        this.timeStamp = null;
    }
    
    public DSSValue(T javaObject, long timeStamp) {
        this(javaObject);
        this.timeStamp = timeStamp;
    }
    
    public T getJavaObject() {
        return javaObject;
    }
    
    public DSSValue<?> getTime() {
        return this; // TODO: check if null
    }
    
    public DSSValue<?> promoteOther(DSSValue<?> value) {
        return (getClass().isAssignableFrom(value.getClass())) ?
                value : DSSValue.DSS_NULL;
    }
    
    
    public DSSValue<?> not() {
        return DSS_NULL;
    }
    public DSSValue<?> add(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> sub(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> mul(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> div(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> exp(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> and(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> or(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> lessThan(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> lessThanOrEqual(DSSValue<?> v) {
        return DSS_NULL;
    }
    public DSSValue<?> concat(DSSValue<?> v) {
        return DSS_NULL;
    }
    
    public DSSValue<?> notEqual(DSSValue<?> v) {
        return equal(v).not();
    }
    public DSSValue<?> equal(DSSValue<?> v) {
        return getJavaObject().equals(v.getJavaObject()) ? 
                DSSBoolean.TRUE : DSSBoolean.FALSE;  
    }

    public abstract int complexity();
    
    @Override
    public String toString() {
        return javaObject != null ? javaObject.toString() : "null";
    }
    
    public static final DSSValue<Object> DSS_NULL = new DSSValue<Object>(null) {
        @Override
        public int complexity() {
            return 0;
        }

    };
}
