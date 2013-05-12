/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.dssmodule.web.controller;

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
@RequestMapping(value = "module/dssmodule/patientsummarylink.form")
public class PatientSummaryController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	/** Success form view name */
	private final String SUCCESS_FORM_VIEW = "/module/dssmodule/patientsummary";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showForm(@RequestParam("patientId") int patientId) {
        System.out.println("PatientSummaryController showForm method***************");
        return new ModelAndView(SUCCESS_FORM_VIEW, getModel(patientId));
    }

    public Map<String,String> getModel(int patientId) {
        List<Encounter> allEncounters = Context.getEncounterService().getEncountersByPatientId(patientId);
        Patient p = Context.getPatientService().getPatient(patientId);

        Map<String, String> model = new HashMap<String, String>();
        model.put("patientName",    p.getPersonName().getFullName());
        model.put("patientGender",  p.getGender());
        model.put("patientAge",     p.getAge().toString());
        model.put("patientAddress", p.getPersonAddress().getAddress1());
        model.put("clinicLocation", Context.getLocationService().getDefaultLocation().getCityVillage());
        model.put("tbStatus",       "N/A");
        model.put("ARTRegimen",     "N/A");
        model.put("allergies",      "N/A");
        model.put("whoStage",       "N/A");
        model.put("enrollWt",       "N/A");
        model.put("enrollTemp",     "N/A");
        model.put("enrollBP",       "N/A / N/A");
        model.put("enrollKS",       "N/A");
        model.put("lastVisitWt",    "N/A");
        model.put("lastVisitTemp",  "N/A");
        model.put("lastVisitBP",    "N/A / N/A");
        model.put("lastVisitKS",    "N/A");
        model.put("LabCD4",         "N/A");
        model.put("LabHemo",        "N/A");
        model.put("LabViral",       "N/A");
        model.put("alert",          "");

        Map<Integer, String> allergyMap = new HashMap<Integer, String>();
        allergyMap.put(6011, "PENICILLIN");
        allergyMap.put(6012, "SULFA");
        allergyMap.put(1083, "OTHER");

        Map<Integer, String> vitalsMap = new HashMap<Integer, String>();
        vitalsMap.put(5089, "Wt");
        vitalsMap.put(5088, "Temp");
        vitalsMap.put(5283, "KS");

        Map<Integer, String> labResultMap = new HashMap<Integer, String>();
        labResultMap.put(6048, "whoStage");
        labResultMap.put(5475, "tbStatus");
        labResultMap.put(5497, "LabCD4");
        labResultMap.put(1017, "LabHemo");
        labResultMap.put(856,  "LabViral");

        Date enrollDate = null, lastDate = null;
        Iterator<Encounter> encIter = allEncounters.iterator();
        Encounter enc;
        Set<Obs> obs;
        Iterator<Obs> obsIter;
        Obs ob;
        StringBuffer meds;
        Boolean isAlert = false;
        int conceptID, encUpdate;
        String systolic = "N/A", diastolic = "N/A", alert = "alert test";

        while (encIter.hasNext()) {
            encUpdate = 0;
            enc = encIter.next();

            //set dates for enrollment and most recent visits
            if (lastDate == null || lastDate.before(enc.getEncounterDatetime())) {
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

                //TODO: need to generate lab result alert
                if (conceptID == 1088){
                    meds = new StringBuffer(ob.getValueAsString(Locale.ENGLISH));
                    if (!model.get("ARTRegimen").equals("N/A")) {
                        meds.append(", ").append(model.get("ARTRegimen"));
                    }
                    model.put("ARTRegimen", meds.toString());
                }
                else if (conceptID == 5085) {
                    systolic = ob.getValueAsString(Locale.ENGLISH);
                }
                else if (conceptID == 5086) {
                    diastolic =  ob.getValueAsString(Locale.ENGLISH);
                }
                else if (allergyMap.get(conceptID) != null) {
                    meds = new StringBuffer(allergyMap.get(conceptID));
                    if (!model.get("allergies").equals("N/A")) {
                        meds.append(", ").append(model.get("allergies"));
                    }
                    model.put("allergies", meds.toString());
                }
                else if (vitalsMap.get(conceptID) != null) {
                    if (encUpdate < 0) {
                        model.put("lastVisit" + vitalsMap.get(conceptID), ob.getValueAsString(Locale.ENGLISH));
                    }
                    else if (encUpdate > 0) {
                        model.put("enroll" + vitalsMap.get(conceptID), ob.getValueAsString(Locale.ENGLISH));
                    }
                }
                else if (labResultMap.get(conceptID) != null){
                    if (encUpdate < 0 || model.get(labResultMap.get(conceptID)).equals("N/A")) {
                        if (conceptID == 5475) {
                            //assumption: TB skin test result > 5mm is positive
                            if (Integer.parseInt(ob.getValueAsString(Locale.ENGLISH)) > 5) {
                                model.put("tbStatus", "positive");
                            }
                            else {
                                model.put("tbStatus", "negative");
                            }
                        }
                        else {
                            model.put(labResultMap.get(conceptID), ob.getValueAsString(Locale.ENGLISH));
                        }
                    }
                }

                if (systolic != null && diastolic != null) {
                    if (encUpdate < 0) {
                        model.put("lastVisitBP", systolic + " / " + diastolic);
                    }
                    if (encUpdate > 0 || model.get("enrollBP").equals("N/A / N/A")) {
                        model.put("enrollBP", systolic + " / " + diastolic);
                    }
                }
            }
        }

        //output lab results alert
        if (isAlert) {
            model.put("alert", alert);
        }

        return model;
    }
}
