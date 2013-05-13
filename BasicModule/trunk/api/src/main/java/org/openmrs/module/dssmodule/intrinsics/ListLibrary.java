package org.openmrs.module.dssmodule.intrinsics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openmrs.module.dssmodule.value.DSSValue;
import org.openmrs.module.dssmodule.value.DSSValueFactory;

/**
 * Implements and organizes list-related intrinsic functions.
 * @author woeltjen
 */
public class ListLibrary extends AnnotatedDSSLibrary {

    // merge, sortTime, sortData, last, first
    
    /**
     * Utility method to get a DSSValue from a list, with bounds checking. 
     * Defaults to DSS null when out of bounds. Note that this is not actual 
     * public API; however, it cannot be private without blocking access 
     * to the intrinsic functions that AnnotatedDSSLibrary generates.
     * @param list
     * @param index
     * @return 
     */
    public DSSValue get(List<DSSValue> list, int index) {
        int sz = list.size();
        return sz > 0 && index <= sz ? 
                list.get(index) : 
                DSSValueFactory.getDSSValue();
    }
    
    @DSSIntrinsic
    public DSSValue last(List<DSSValue> list) {
        return get(list, list.size() - 1);
    }

    @DSSIntrinsic
    public DSSValue first(List<DSSValue> list) {
        return get(list, 0);
    }
    
    @DSSIntrinsic
    public List<DSSValue> merge(Collection<DSSValue> a, Collection<DSSValue> b) {
        List<DSSValue> v = new ArrayList<DSSValue>();
        v.addAll(a);
        v.addAll(b);
        return sortTime(v);
    }
    
    @DSSIntrinsic
    public List<DSSValue> sortTime(Collection<DSSValue> c) {
        return sort(c, TIMESTAMP_COMPARATOR);       
    }
    
    @DSSIntrinsic
    public List<DSSValue> sortData(Collection<DSSValue> c) {
        return sort(c, VALUE_COMPARATOR);       
    }    
    
    private List<DSSValue> sort(Collection<DSSValue> v, Comparator<DSSValue> c) {
        List<DSSValue> sorted = new ArrayList<DSSValue>();
        sorted.addAll(v);
        Collections.sort(sorted, c);
        return sorted;                
    }
    
    private static final Comparator<DSSValue> TIMESTAMP_COMPARATOR =
            new Comparator<DSSValue>() {
        public int compare(DSSValue t, DSSValue t1) {
            return t.getTimeStamp().compareTo(t1.getTimeStamp());
        }                
    };    
    
    private static final Comparator<DSSValue> VALUE_COMPARATOR =
            new Comparator<DSSValue>() {
        public int compare(DSSValue t, DSSValue t1) {
            if (t.lessthan(t1)) {
                return -1;
            } else if (t1.lessthan(t)) {
                return 1;
            } else {
                return 0;
            }
        }                
    };    
}
