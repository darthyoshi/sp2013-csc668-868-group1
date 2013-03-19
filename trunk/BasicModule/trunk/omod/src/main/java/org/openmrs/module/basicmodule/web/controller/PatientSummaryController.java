/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.Encounter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "module/basicmodule/patientsummarylink.form")
public class PatientSummaryController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	/** Success form view name */
	private final String SUCCESS_FORM_VIEW = "/module/basicmodule/patientsummary";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(@RequestParam("patientId") int patientId) {
        System.out.println("PatientSummaryController showForm method***************");
        return new ModelAndView(SUCCESS_FORM_VIEW, getModel(patientId));
    }
    
    public Map<String,String> getModel(int patientId) {
        Map<String,String> model = new HashMap<String,String>();
        List<Encounter> allEncounters = Context.getEncounterService().getEncountersByPatientId(patientId);
        Patient p = Context.getPatientService().getPatient(patientId);
        
        
        model.put("patientName", p.getPersonName().getFullName());
        model.put("patientGender", p.getGender());
        model.put("patientAge", p.getAge().toString());
        model.put("patientAddress", p.getPersonAddress().getAddress1());
        model.put("patientLastSeen", );
        model.put("clinicLocation", Context.getLocationService().getDefaultLocation().getCityVillage());
        model.put("whoStage", );
        model.put("tbStatus", );
        model.put("allergies", );
        model.put("ARTRegimen", );
        model.put("enroll", );
        model.put("enrollWt", );
        model.put("enrollTemp", );
        model.put("enrollBP", );
        model.put("enrollKS", );
        model.put("lastVisit", );
        model.put("lastVisitWt", );
        model.put("lastVisitTemp", );
        model.put("lastVisitBP", );
        model.put("lastVisitKS", );
        model.put("LabCD4", );
        model.put("LabHemo", );
        model.put("LabViral", );
        
        return model;
    }
}
