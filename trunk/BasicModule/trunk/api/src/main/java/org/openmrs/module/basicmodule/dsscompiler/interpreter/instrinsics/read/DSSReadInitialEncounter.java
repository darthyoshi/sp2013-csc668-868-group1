package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics.read;

/**
 *  @author Kay Choi
 */

import java.util.*;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.dsscompiler.value.*;

public class DSSReadInitialEncounter extends DSSRead {
    /**
     * Call this function. The arguments provided are as observed by the
     * interpreter.
     * @param args the arguments to the function
     * @return a list containing all observations for a given concept
     *   belonging to the first encounter associated with a patient
     * @return DSSNullValue for argument mismatch
     */
    @Override
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
                Stack<DSSValue> validObs = new Stack<DSSValue>();
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
                                validObs.add(getVal(ob));
                            }
                        }
                    }
                }

                return DSSValueFactory.getDSSValue(validObs);
            }
            catch(Exception e) { }
        }

        return DSSValueFactory.getDSSValue();
    }
}