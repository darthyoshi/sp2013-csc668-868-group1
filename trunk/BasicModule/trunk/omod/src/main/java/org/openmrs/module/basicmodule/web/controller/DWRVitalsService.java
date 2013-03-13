package org.openmrs.module.basicmodule.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.openmrs.Encounter;
import org.openmrs.api.EncounterService;
import org.openmrs.api.context.Context;

/**
 * Responsible for supporting requests of the Vitals tab.
 * @author woeltjen
 */
public class DWRVitalsService {
    public Collection<Encounter> getPatientEncounters(String patientName, int count) {
        Collection<Encounter> encounters = new ArrayList<Encounter>();
        
        EncounterService encounterService = Context.getEncounterService();
        
        if (encounterService != null) {
            // TODO: Can we get a smaller list in the initial request?
            List<Encounter> allEncounters = 
                    encounterService.getEncountersByPatient(patientName);
            Collections.sort(allEncounters, RECENT_ENCOUNTER_COMPARATOR);
            for (int i = 0; i < Math.min(allEncounters.size(), count); i++) {
                encounters.add(allEncounters.get(i));
            }
        }
        
        return encounters;
    }
    
    /**
     * Comparator to sort encounters. Most recent encounters should appear at 
     * the front of the list.
     */
    private static final Comparator<Encounter> RECENT_ENCOUNTER_COMPARATOR = 
            new Comparator<Encounter>() {
        public int compare(Encounter a, Encounter b) {
            return b.getEncounterDatetime().compareTo(a.getEncounterDatetime());
        }                
    };
}
