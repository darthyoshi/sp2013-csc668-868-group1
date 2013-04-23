/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.dsscompiler.intrinsics;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSLibrary;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValueFactory;

/**
 *
 * @author steven
 */


public class DSSListLibrary implements DSSLibrary{
   private static final Map<String, DSSFunction> Map  = new HashMap<String, DSSFunction>();;
   
    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
       if(Map.isEmpty()){
           //Map.put("Merge", new DSSListLibrary.DSSListMerge());
           //Map.put("SortTime", new DSSListLibrary.DSSListSortTime());
           //Map.put("GetData", new DSSListLibrary.DSSListGetData());
           //Map.put("GetLast", new DSSListLibrary.DSSListGetLast());
           //Map.put("GetFirst", new DSSListLibrary.DSSListGetFirst());
       }
       return Map;
    }
    /*
     * 
     */
    private static class DSSList extends DSSFunction{
        //do something
        public DSSValue call(DSSValue... args){
            
            return DSSValueFactory.getDSSValue(args[0].length());
        }
    }
    
    //private static class DSSLibrary extends DSSFunction{}
    //within function here
    /*
     * 
     */
}