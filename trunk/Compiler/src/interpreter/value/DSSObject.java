/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.value;

import interpreter.DSSValue;
import interpreter.NamingContext;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author woeltjen
 */
public class DSSObject extends DSSValue<Map<String, DSSValue<?>>> implements NamingContext {

    public DSSObject(Map<String, DSSValue<?>> javaObject, long timeStamp) {
        super(javaObject, timeStamp);
    }

    public DSSObject(Map<String, DSSValue<?>> javaObject) {
        super(javaObject);
    }

    @Override
    public int complexity() {
        return 5;
    }

    @Override
    public DSSValue<?> promote() {        
        return new DSSString(toString());
    }

    public DSSValue get(String name) {
        return getJavaObject().get(name);
    }

    public String[] names() {
        String[] names = new String[getJavaObject().keySet().size()];
        return getJavaObject().keySet().toArray(names);
    }

    public void set(String name, DSSValue value) {
        getJavaObject().put(name, value);
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        int c = names().length;
        b.append("Object( ");
        for (Entry<String, DSSValue<?>> entry : getJavaObject().entrySet()) {
            b.append(entry.getKey());
            b.append('=');
            b.append(entry.getValue().toString());
            if (--c > 0) {
                b.append(" , ");
            }        
        }
        b.append(" )");
        
        return b.toString();
    }
    
    
}
