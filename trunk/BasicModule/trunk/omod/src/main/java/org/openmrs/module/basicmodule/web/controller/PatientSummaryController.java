/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.Encounter;
import org.openmrs.Person;

@Controller
@RequestMapping(value = "module/basicmodule/patientsummarylink.form")
public class PatientSummaryController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	/** Success form view name */
	private final String SUCCESS_FORM_VIEW = "/module/basicmodule/patientsummarylink";

    @RequestMapping(method = RequestMethod.GET)
	public String showForm() {
        System.out.println("PatientSummaryController showForm method***************");
		return SUCCESS_FORM_VIEW;
	}
    
    public String getHTML(String patientID) {
        Patient p = Context.getPatientService().getPatient(Integer.parseInt(patientID));
        List<Encounter> encounters = Context.getEncounterService().getEncountersByPatient(p);
        //Person person = p.getPerson(); Patient is-a person
        
        // TODO: Maybe move this to jsp and just prepare variable names here?
        
        String html = "<table width=100%>\n"+
                "    <tr bgcolor='4D9999'>\n"+
                "        <td colspan=2><font color='white'>Patient Information</font></td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td width=30% ><b>Name:</b> "+p.getPersonName().getFullName()+"</td>\n"+
                "        <td width=70% align='right'><b>Clinic Location:</b> "+"%patientCity%"+"</td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td>"+p.getGender()+", "+p.getAge()+" yrs<td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td colspan=2><center><b>Address:</b> "+"%patientAddress%"+"</center>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td colspan=2><center><b>Last seen on:</b> "+"%LastSeen%"+"</center>\n"+
                "    </tr>\n"+
                "</table>\n"+
                "\n"+
                "<p>\n"+
                "\n"+
                "<table width=100%>\n"+
                "    <tr bgcolor='4D9999'>\n"+
                "        <td colspan=2><font color='white'>Patient Summary</font></td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td width=30% ><b>WHO stage:</b> "+"%patientWHOStage%"+"</td>\n"+
                "        <td width=70% ><b>Current art regimen drugs:</b> </td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td width=30% ><b>TB Status:</b> "+"%TBStatus%"+"</td>\n"+
                "        <td width=70% rowspan='2'><b>"+"%ARTRegimen%"+"</b> </td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td width=30% ><b>Allergies:</b> "+"%patientAllergies%"+"</td>\n"+
                "    </tr>\n"+
                "</table>\n"+
                "\n"+
                "<p>\n"+
                "<br><br>\n"+
                "\n"+
                "<table width='100%' >\n"+
                "    <tr bgcolor='4D9999'>\n"+
                "        <td colspan='5' width='100%'><font color='white'>Patient Vitals</font></td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>\n"+
                "        <td ><b>Weight (KG)</b></td>\n"+
                "        <td ><b>Temperature (C)</b></td>\n"+
                "        <td ><b>Blood Pressure</b></td>\n"+
                "        <td ><b>Karnofsky Score</b></td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td >Enrollment: "+"%enroll%"+"</td>\n"+
                "        <td >"+"%enrollWt%"+"</td>\n"+
                "        <td >"+"%enrollTemp%"+"</td>\n"+
                "        <td >"+"%enrollBP%"+"</td>\n"+
                "        <td >"+"%enrollKS%"+"</td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td >Last Visit: "+"%lastVisit%"+"</td>\n"+
                "        <td >"+"%lastVisitWt%"+"</td>\n"+
                "        <td >"+"%lastVisitTemp%"+"</td>\n"+
                "        <td >"+"%lastVisitBP%"+"</td>\n"+
                "        <td >"+"%lastVisitKS%"+"</td>\n"+
                "    </tr>\n"+
                "</table>\n"+
                "\n"+
                "<p>\n"+
                "<br><br>\n"+
                "\n"+
                "<table width='100%' >\n"+
                "    <tr bgcolor='4D9999'>\n"+
                "        <td colspan=3 width='100%'><font color='white'>Laboratory Results</font></td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td ><b>CD4</b></td>\n"+
                "        <td ><b>Hemoglobin</b></td>\n"+
                "        <td ><b>Viral Load</b></td>\n"+
                "    </tr>\n"+
                "    <tr>\n"+
                "        <td >"+"%LabCD4%"+"</td>\n"+
                "        <td >"+"%LabHemoglobin%"+"</td>\n"+
                "        <td >"+"%LabViral%"+"</td>\n"+
                "    </tr>\n"+
                "</table>\n";

        return html;
    }
}
