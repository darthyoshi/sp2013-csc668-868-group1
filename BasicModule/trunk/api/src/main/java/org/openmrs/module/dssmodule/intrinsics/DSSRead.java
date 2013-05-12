package org.openmrs.module.dssmodule.intrinsics;

/**
 *  @author Kay Choi
 */

import org.openmrs.module.dssmodule.value.DSSValueFactory;
import org.openmrs.module.dssmodule.value.DSSValue;
import java.util.*;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.dssmodule.interpreter.DSSFunction;

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
                Stack<DSSValue> validObs = new Stack<DSSValue>();

                while (encIter.hasNext()) {
                    enc = encIter.next();
                    obs = enc.getAllObs();

                    obsIter = obs.iterator();
                    while (obsIter.hasNext()) {
                        ob = obsIter.next();

                        if (!ob.isVoided() && ob.getConcept().getName().getName().equals(conceptName)) {
                            validObs.add(getVal(ob));
                        }
                    }
                }

                return DSSValueFactory.getDSSValue(validObs);
            }
            catch(Exception e) { }
        }

        return DSSValueFactory.getDSSValue();
    }

    /**
     * Creates a DSSValue from an observation. The value of the DSSValue shall
     * be the value of observation itself, and the DSSValue time stamp shall be
     * the date and time of the observation.
     * @param ob observation to convert
     * @return DSSValue equivalent of ob
     */
    protected DSSValue getVal(Obs ob) {
        DSSValue val;
        ConceptDatatype type = ob.getConcept().getDatatype();

        if(type.isBoolean()) {
            val = DSSValueFactory.getDSSValue(ob.getValueBoolean());
        }
        else if(type.isDate() || type.isDateTime() || type.isTime()) {
            val = DSSValueFactory.getDSSValue(ob.getValueDatetime());
        }
        else if(type.isNumeric()) {
            val = DSSValueFactory.getDSSValue(ob.getValueNumeric());
        }
        else {
            val = DSSValueFactory.getDSSValue(ob.getValueAsString(Locale.ENGLISH));
        }

        val.setTimeStamp(ob.getObsDatetime());

        return val;
    }
}
