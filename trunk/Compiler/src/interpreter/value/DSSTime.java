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
public class DSSTime extends DSSValue<Date> {

    public DSSTime(Date javaObject, long timeStamp) {
        super(javaObject, timeStamp);
    }

    public DSSTime(Date javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 4;
    }

    
}
