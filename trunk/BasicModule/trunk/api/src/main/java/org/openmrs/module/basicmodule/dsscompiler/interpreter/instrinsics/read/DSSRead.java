package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.read;

/**
 *  @author Kay Choi
 */

import java.util.*;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.value.*;

public class DSSRead extends DSSFunction {
    /**
     * Call this function. The arguments provided are as observed by the
     * interpreter.
     * @param args the arguments to the function
     * @return a list containing all observations for a given concept
     *   associated with a patient
     * @return DSSNullValue for argument mismatch
     */
    public DSSValue call(DSSValue... args) {
        //check number of arguments
        if(args.length == 2) {
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
                List<DSSValue> validObs = new ArrayList<DSSValue>();
                DSSValue val;

                while (encIter.hasNext()) {
                    enc = encIter.next();
                    obs = enc.getAllObs();

                    obsIter = obs.iterator();
                    while (obsIter.hasNext()) {
                        ob = obsIter.next();

                        if (!ob.isVoided() && ob.getConcept().getName().getName().equals(conceptName)) {
                            val = DSSValueFactory.getDSSValue(conceptName); //TODO: use different value
                            val.setTimeStamp(enc.getEncounterDateTime());
                            validObs.add(ob);
                        }
                    }
                }

                return DSSValueFactory.getDSSValue(validObs);
            }
            catch(Exception e) {
                return DSSValueFactory.getDSSValue();
            }
        }
        else {
            return DSSValueFactory.getDSSValue();
        }
    }
}
