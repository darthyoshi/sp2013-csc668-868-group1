package openmrsdssvalue;
import java.util.Date;

/**
 *
 * @author bierman
 */
public abstract class DSSValue extends Object {
    Date timestamp = null;
    
    public void setTimeStamp (Date d) {
        timestamp = d;
    }
    
    public void setTimeStamp (Long d) {    
        timestamp.setTime(d);
    }
    
    public void setTimeStamp (DSSValueDate d) {
        timestamp = d.value;
    }
    
    public Date getTimeStamp () {
        return (timestamp);
    }
    
    public DSSValue getDSSValueTimeStamp () {
        return DSSValueFactory.getDSSValue(timestamp);
    }
    
    public DSSValue not (DSSValue b){return null;}
    
    public  DSSValue power (DSSValue b) {
        throw new UnsupportedOperationException("Power function not supported by this DSSValue class.");
    }

    public  DSSValue mult (DSSValue b) {
        throw new UnsupportedOperationException("Multiply (mult) function not supported by this DSSValue class."); 
    }


    public DSSValue div (DSSValue b) {
        throw new UnsupportedOperationException("Division (div) function not supported by this DSSValue class."); 
    }


    public DSSValue and (DSSValue b) {
        throw new UnsupportedOperationException("And function not supported by this DSSValue class."); 
    }


    public DSSValue add (DSSValue b) {
        throw new UnsupportedOperationException("Add function not supported by this DSSValue class."); 
    }


    public DSSValue sub (DSSValue b) {
        throw new UnsupportedOperationException("Subtraction (sub) function not supported by this DSSValue class."); 
    }


    public DSSValue or (DSSValue b) {
        throw new UnsupportedOperationException("Or function not supported by this DSSValue class."); 
    }

    public abstract boolean lessthan (DSSValue b);
    public abstract boolean lessthanequal (DSSValue b);
    public abstract boolean greaterthan (DSSValue b);
    public abstract boolean greaterthanequal (DSSValue b);
    public abstract boolean notequal (DSSValue b);
    public abstract boolean equal (DSSValue b);
    
    public DSSValue concat (DSSValue b) {
        throw new UnsupportedOperationException("Concatenate (concat) function not supported by this DSSValue class."); 
    }

    //public abstract DSSValue ref (DSSValue b);
    public abstract int toInt();
    public abstract long toLong();
    public abstract double toFloat();
    public boolean isString() {return false;}
    public boolean isInt() {return false;}
    public boolean isFloat() {return false;}
    public boolean isBoolean() {return false;}
    public boolean isDate() {return false;}
    public boolean isObject() {return false;}
    public boolean isList() {return false;}
    public boolean isNumeric() {return false;}  
    public int length() {return 0;}

}
