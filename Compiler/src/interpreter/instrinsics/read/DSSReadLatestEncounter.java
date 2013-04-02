package interpreter.instrinsics.read;

/**
 *  @author Kay Choi
 */

import interpreter.DSSFunction;
import interpreter.DSSValue;
import java.util.*;

public class DSSReadLatestEncounter extends DSSFunction {
    /**
     * Call this function. The arguments provided are as observed by the
     * interpreter.
     * @param args the arguments to the function
     * @return a list containing all observations for a given concept
     *   belonging to the last encounter associated with a patient
     * @return null for argument mismatch
     */
    public DSSValue call(DSSValue... args) {
        //check number of arguments
        if(args.length != 2) {
            return null;
        }
        else {
            //parse arguments
            try {
                String patientId = args[0].toInt();
                String conceptName = args[1].toString();
            }
            catch(Exception e) {
                return null;
            }

            List<Encounter> allEncounters = Context.getEncounterService().getEncountersByPatientId(Integer.parseInt(patientId));
            Iterator<Encounter> encIter = allEncounters.iterator();
            Encounter enc;
            Set<Obs> obs;
            Iterator<Obs> obsIter;
            Obs ob;
            List<Obs> validObs = new ArrayList<Obs>();
            Date lastDate = null;
            
            while (encIter.hasNext()) {
                enc = encIter.next();
                
                //runs during first iteration and if a later encounter is found
                if (lastDate == null || !lastDate.after(enc.getEncounterDatetime())) {
                    validObs.clear();
                    lastDate = enc.getEncounterDatetime();
                
                    obs = enc.getAllObs();

                    obsIter = obs.iterator();
                    while (obsIter.hasNext()) {
                        ob = obsIter.next();
                        
                        if (!ob.isVoided() && ob.getConcept.getName().getName().equals(conceptName)) {
                            validObs.add(ob);
                        }
                    }
                }
            }
            
            return new DSSValueList(validObs);
        }
    }
}