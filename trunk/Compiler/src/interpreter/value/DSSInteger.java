package interpreter.value;

import interpreter.DSSValue;

/**
 *
 * @author woeltjen
 */
public class DSSInteger extends DSSValue<Long> {

    public DSSInteger(Long javaObject, long timeStamp) {
        super(javaObject, timeStamp);
    }

    public DSSInteger(Long javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 2;
    }

    @Override
    public DSSValue<?> promote() {
        return new DSSFloat(getJavaObject().doubleValue());
    }

        @Override
    public DSSValue<?> add(DSSValue<?> v) {
        return v instanceof DSSInteger ?
                new DSSInteger(getJavaObject() + ((DSSInteger)v).getJavaObject()) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> div(DSSValue<?> v) {
        return v instanceof DSSInteger ?
                new DSSInteger(getJavaObject() / ((DSSInteger)v).getJavaObject()) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> equal(DSSValue<?> v) {
        return v instanceof DSSInteger ?
                new DSSBoolean(getJavaObject() == ((DSSInteger)v).getJavaObject()) :
                DSS_NULL;    
    }

    @Override
    public DSSValue<?> exp(DSSValue<?> v) {
        if (v instanceof DSSInteger) {
            long result = 1;
            for (long i = 0; i < ((DSSInteger)v).getJavaObject(); i++) {
                result *= getJavaObject();
            }
            return new DSSInteger(result);
        } else {
            return DSS_NULL;
        }
    }

    @Override
    public DSSValue<?> lessThan(DSSValue<?> v) {
        return v instanceof DSSInteger ?
                new DSSBoolean(getJavaObject() < ((DSSInteger)v).getJavaObject()) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> lessThanOrEqual(DSSValue<?> v) {
        return v instanceof DSSInteger ?
                new DSSBoolean(getJavaObject() <= ((DSSInteger)v).getJavaObject()) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> mul(DSSValue<?> v) {
        return v instanceof DSSInteger ?
                new DSSInteger(getJavaObject() * ((DSSInteger)v).getJavaObject()) :
                DSS_NULL;
    }


    @Override
    public DSSValue<?> sub(DSSValue<?> v) {
        return v instanceof DSSInteger ?
                new DSSInteger(getJavaObject() - ((DSSInteger)v).getJavaObject()) :
                DSS_NULL;    
    }

    // TODO: - bitwise operators?
    
}
