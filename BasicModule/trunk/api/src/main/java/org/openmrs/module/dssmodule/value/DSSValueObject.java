package org.openmrs.module.dssmodule.value;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openmrs.module.dssmodule.state.NamingContext;

/**
 * Represents the "object" type in DSS1. Essentially operates 
 * as a struct (contains named fields which can be read or 
 * written).
 * @author woeltjen
 */
public class DSSValueObject extends DSSValue implements NamingContext {
    private Map<String, DSSValue> fields = new HashMap<String, DSSValue>();   

    @Override
    public boolean equal(DSSValue b) {
        if (b instanceof DSSValueObject) {
            NamingContext nc = (NamingContext) b;
            String[] myNames = names();
            String[] theirNames = names();
            if (myNames.length == theirNames.length) {
                for (String name : theirNames) {
                    // If any field name or contents don't match, not equal
                    if (!fields.containsKey(name) ||
                        !fields.get(name).equal(nc.get(name))) {
                        return false;                        
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean greaterthan(DSSValue b) {
        return false;
    }

    @Override
    public boolean greaterthanequal(DSSValue b) {
        return equal(b);
    }

    @Override
    public boolean lessthan(DSSValue b) {
        return false;
    }

    @Override
    public boolean lessthanequal(DSSValue b) {
        return equal(b);
    }

    @Override
    public boolean notequal(DSSValue b) {
        return !equal(b);
    }

    @Override
    public double toFloat() {
        return Double.NaN;
    }

    @Override
    public int toInt() {
        return 0;
    }

    @Override
    public long toLong() {
        return 0;
    }

    public DSSValue get(String name) {
        return fields.containsKey(name) ? 
                fields.get(name) : DSSValueFactory.getDSSValue();
    }

    public String[] names() {
        Set<String> names = fields.keySet();
        return names.toArray(new String[names.size()]);
    }

    public void set(String name, DSSValue value) {
        fields.put(name, value);
    }
    
}
