package interpreter.instrinsics;

import interpreter.DSSFunction;
import interpreter.DSSValue;

/**
 *
 * @author woeltjen
 */
public class DSSAlert extends DSSFunction {
    @Override
    public DSSValue call(DSSValue... args) {
        for (DSSValue v : args) {
            System.out.println(v.toString());
        }
        return DSSValue.DSS_NULL;
    }    
}
