package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.read;

/**
 *  @author Kay Choi
 */

import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.DSSValue;

public class DSSRead extends DSSFunction {
    /**
     * Call this function. The arguments provided are as observed by the
     * interpreter. Note that the actual number of arguments may not match
     * the number of parameters expected; it is ultimately the function's
     * responsibility to handle this situation.
     * @param args the arguments to the function
     * @return the return value of the represented function
     */
    public DSSValue call(DSSValue... args) {
//        //DSSValue result = new DSSValue();
//        
//        //TODO: parse args and retrieve patientId, conceptName
//        String patientID;
//        String conceptName;
//
//        List<Encounter> allEncounters = Context.getEncounterService().getEncountersByPatientId(Integer.parseInt(patientId));
//        Iterator<Encounter> encIter = allEncounters.iterator();
//        Encounter enc;
//        Set<Obs> obs;
//        Iterator<Obs> obsIter;
//        Obs ob;
//        Set<Obs> validObs = new Set<Obs>();
//
//        while (encIter.hasNext()) {
//            enc = encIter.next();
//            obs = enc.getAllObs();
//            
//            obsIter = obs.iterator();
//            while (obsIter.hasNext()) {
//                ob = obsIter.next();
//                
//                if (!ob.isVoided() && ob.getConcept.getName().getName().equals(conceptName)) {
//                    validObs.add(ob);
//                }
//            }
//        }
        
        //TODO: convert Set to DSSValue
        
        //return result;
        return null;
    }
}