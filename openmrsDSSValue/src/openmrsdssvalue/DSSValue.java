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
        if (timestamp == null) {
            return DSSValueFactory.getDSSValue();
        }
        
        return DSSValueFactory.getDSSValue(timestamp);
    }
    
    public DSSValue not (DSSValue b){return null;}
    
    public  DSSValue power (DSSValue b) {
        return DSSValueFactory.getDSSValue();
    }

    public  DSSValue mult (DSSValue b) {
        return DSSValueFactory.getDSSValue();
    }


    public DSSValue div (DSSValue b) {
        return DSSValueFactory.getDSSValue();
    }


    public DSSValue and (DSSValue b) {
        return DSSValueFactory.getDSSValue();
    }


    public DSSValue add (DSSValue b) {
        return DSSValueFactory.getDSSValue();
    }


    public DSSValue sub (DSSValue b) {
        return DSSValueFactory.getDSSValue();
    }


    public DSSValue or (DSSValue b) {
        return DSSValueFactory.getDSSValue();
    }

    public abstract boolean lessthan (DSSValue b);
    public abstract boolean lessthanequal (DSSValue b);
    public abstract boolean greaterthan (DSSValue b);
    public abstract boolean greaterthanequal (DSSValue b);
    public abstract boolean notequal (DSSValue b);
    public abstract boolean equal (DSSValue b);
    
    public DSSValue concat (DSSValue b) {
        return DSSValueFactory.getDSSValue();
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
    public boolean isNull() {return false;}
    public int length() {return 0;}

}
