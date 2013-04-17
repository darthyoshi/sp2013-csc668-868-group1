package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.read;

/**
 *  @author Kay Choi
 */

import java.util.*;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.*;

public class DSSReadInitialEncounter extends DSSFunction {
    /**
     * Call this function. The arguments provided are as observed by the
     * interpreter.
     * @param args the arguments to the function
     * @return a list containing all observations for a given concept
     *   belonging to the first encounter associated with a patient
     * @return DSSNullValue for argument mismatch
     */
    public DSSValue call(DSSValue... args) {
        //check number of arguments
  /*      if(args.length != 2){
            return null;
        }
        else {
            List<Obs> validObs = null;

            try {
                //parse arguments
                int patientId = args[0].toInt();
                String conceptName = args[1].toString();

                List<Encounter> allEncounters = Context.getEncounterService().getEncountersByPatientId(patientId);
                Iterator<Encounter> encIter = allEncounters.iterator();
                Encounter enc;
                Set<Obs> obs;
                Iterator<Obs> obsIter;
                Obs ob;
                validObs = new ArrayList<Obs>();
                Date initDate = null;

                while (encIter.hasNext()) {
                    enc = encIter.next();

                    //runs during first iteration and if an earlier encounter is found
                    if (initDate == null || !initDate.before(enc.getEncounterDatetime())) {
                        validObs.clear();
                        initDate = enc.getEncounterDatetime();

                        obs = enc.getAllObs();

                        obsIter = obs.iterator();
                        while (obsIter.hasNext()) {
                            ob = obsIter.next();

                            if (!ob.isVoided() && ob.getConcept().getName().getName().equals(conceptName)) {
                                validObs.add(ob);
                            }
                        }
                    }
                }
            }
            catch(Exception e) { }
*/
            return null;
            //return DSSValueFactory.getDSSValue(validObs);
   //     }
    }
}