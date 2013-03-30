/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.value;

import interpreter.DSSValue;
import java.util.Date;

/**
 *
 * @author woeltjen
 */
public class DSSFloat extends DSSValue<Double> {

    public DSSFloat(Double javaObject, long timeStamp) {
        super(javaObject, timeStamp);
    }

    public DSSFloat(Double javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 3;
    }

    @Override
    public DSSValue<?> add(DSSValue<?> v) {
        return v instanceof DSSFloat ?
                new DSSFloat(getJavaObject() + ((DSSFloat)v).getJavaObject()) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> div(DSSValue<?> v) {
        return v instanceof DSSFloat ?
                new DSSFloat(getJavaObject() / ((DSSFloat)v).getJavaObject()) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> exp(DSSValue<?> v) {
        return v instanceof DSSFloat ?
                new DSSFloat(Math.pow(getJavaObject(), ((DSSFloat)v).getJavaObject())) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> lessThan(DSSValue<?> v) {
        return v instanceof DSSFloat ?
                new DSSBoolean(getJavaObject() < ((DSSFloat)v).getJavaObject()) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> lessThanOrEqual(DSSValue<?> v) {
        return v instanceof DSSFloat ?
                new DSSBoolean(getJavaObject() <= ((DSSFloat)v).getJavaObject()) :
                DSS_NULL;
    }

    @Override
    public DSSValue<?> mul(DSSValue<?> v) {
        return v instanceof DSSFloat ?
                new DSSFloat(getJavaObject() * ((DSSFloat)v).getJavaObject()) :
                DSS_NULL;
    }


    @Override
    public DSSValue<?> sub(DSSValue<?> v) {
        return v instanceof DSSFloat ?
                new DSSFloat(getJavaObject() - ((DSSFloat)v).getJavaObject()) :
                DSS_NULL;    
    }

    @Override
    public DSSValue<?> cast(DSSValue<?> value) {
        if (value instanceof DSSInteger) {
            return new DSSFloat(((DSSInteger)value).getJavaObject().doubleValue());
        } else {
            return super.cast(value);
        }
    }
    
    
    
}
