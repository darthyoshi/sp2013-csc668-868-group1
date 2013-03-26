package interpreter;

/**
 *  @author Kay Choi
 */

import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class DSSReadInitialEncounter extends DSSFunction {
    /**
     * Call this function. The arguments provided are as observed by the
     * interpreter. Note that the actual number of arguments may not match
     * the number of parameters expected; it is ultimately the function's
     * responsibility to handle this situation.
     * @param args the arguments to the function
     * @return the return value of the represented function
     */
    public DSSValue call(DSSValue... args) {
        //DSSValue result = new DSSValue();
        
        //TODO: parse args and retrieve patientId, conceptName
        String patientID;
        String conceptName;

        List<Encounter> allEncounters = Context.getEncounterService().getEncountersByPatientId(Integer.parseInt(patientId));
        Iterator<Encounter> encIter = allEncounters.iterator();
        Encounter enc, initEnc;
        Set<Obs> obs;
        Iterator<Obs> obsIter;
        Obs ob;
        Set<Obs> validObs = new Set<Obs>();
        Date initDate = null;
        
        while (encIter.hasNext()) {
            enc = encIter.next();
            
            if (initDate == null || !initDate.before(enc.getEncounterDatetime())) {
                initDate = enc.getEncounterDatetime();
            
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
        
        //TODO: convert Set to DSSValue
        
        //return result;
        return null;
    }
}