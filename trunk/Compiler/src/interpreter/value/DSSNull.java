package interpreter.value;

import interpreter.DSSValue;

/**
 *
 * @author woeltjen
 */
public class DSSNull extends DSSValue<Object> {
    public static final DSSNull VALUE = new DSSNull(new Object());
    
    private DSSNull(Object javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 0;
    }
}
