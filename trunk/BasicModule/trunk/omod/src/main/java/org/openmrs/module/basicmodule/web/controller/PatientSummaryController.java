/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

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
        model.put("clinicLocation", Context.getLocationService().getDefaultLocation().getCityVillage());
        model.put("tbStatus", "N/A");
        model.put("ARTRegimen", "N/A");
        model.put("allergies", "N/A");
        model.put("whoStage", "N/A");
        model.put("enrollWt", "N/A");
        model.put("enrollTemp", "N/A");
        model.put("enrollBP", "N/A");
        model.put("enrollKS", "N/A");
        model.put("lastVisitWt", "N/A");
        model.put("lastVisitTemp", "N/A");
        model.put("lastVisitBP", "N/A");
        model.put("lastVisitKS", "N/A");
        model.put("LabCD4", "N/A");
        model.put("LabHemo", "N/A");
        model.put("LabViral", "N/A");
        model.put("alert", "");

        Date enrollDate = null, lastDate = null;

        Map<Integer, String> allergies = new HashMap<Integer, String>();
        allergies.put(6011, "PENICILLIN");
        allergies.put(6012, "SULFA");
        allergies.put(1083, "OTHER");

        Map<Integer, String> code = new HashMap<Integer, String>();
        code.put(6048, "whoStage");
        code.put(5475,"tbStatus");
  /*    code.put("","enrollWt");
        code.put("","enrollTemp");
        code.put("","enrollBP");
        code.put("","enrollKS");*/
        code.put(5089,"lastVisitWt");
        code.put(5088,"lastVisitTemp");
        code.put(5283,"lastVisitKS");
        code.put(5497,"LabCD4");
        code.put(1017,"LabHemo");
        code.put(856,"LabViral");

        Iterator<Encounter> encIter = allEncounters.iterator();
        Iterator<Obs> obsIter;
        Set<Obs> obs;
        Obs ob;
        Encounter enc;
        StringBuffer meds;
        Boolean isAlert = false;
        int conceptID, encUpdate;
        String systolic = "N/A", diastolic = "N/A", alert = "alert test";

        while (encIter.hasNext()) {
            encUpdate = 0;
            enc = encIter.next();

            //set dates for enrollment and most recent visits
            if (lastDate == null) {
                enrollDate = enc.getEncounterDatetime();
                lastDate = enc.getEncounterDatetime();
                model.put("lastVisit", lastDate.toString().split(" ")[0]);
                model.put("enroll", enrollDate.toString().split(" ")[0]);
                encUpdate = -1;     //flag to update last visit vitals results
            }
            else if (enc.getEncounterDatetime().before(enrollDate)) {
                enrollDate = enc.getEncounterDatetime();
                model.put("enroll", enrollDate.toString().split(" ")[0]);
                encUpdate = 1;      //flag to update enrollment vitals results
            }

            obs = enc.getAllObs();

            obsIter = obs.iterator();
            while (obsIter.hasNext()) {
                ob = obsIter.next();
                conceptID = ob.getConcept().getConceptId();

                //System.out.println(String.valueOf(conceptID) + ' ' + ob.getValueAsString(Locale.ENGLISH));

                //TODO: handle enrollment and most recent visit vitals updates
                if (conceptID == 1088){
                    meds = new StringBuffer(ob.getValueAsString(Locale.ENGLISH));
                    if (!model.get("ARTRegimen").equals("N/A")) {
                        meds.append(", ").append(model.get("ARTRegimen"));
                    }
                    model.put("ARTRegimen", meds.toString());
                }
                else if (conceptID == 5475){
                    if (Integer.parseInt(ob.getValueAsString(Locale.ENGLISH)) > 5) {
                        model.put("tbStatus", "positive");
                    }
                    else {
                        model.put("tbStatus", "negative");
                    }
                }
                else if (allergies.get(conceptID) != null){
                    meds = new StringBuffer(allergies.get(conceptID));
                    if (!model.get("allergies").equals("N/A")) {
                        meds.append(", ").append(model.get("allergies"));
                    }
                    model.put("allergies", meds.toString());
                }
                else if (conceptID == 5085) {
                    systolic = ob.getValueAsString(Locale.ENGLISH);
                }
                else if(conceptID == 5086) {
                    diastolic =  ob.getValueAsString(Locale.ENGLISH);
                }
                else {
                    model.put(code.get(conceptID), ob.getValueAsString(Locale.ENGLISH));
                }

                model.put("lastVisitBP", systolic + " / " + diastolic);
            }
        }

        //output lab results alert
        if (isAlert) {
            model.put("alert", alert);
        }

        return model;
    }
}
