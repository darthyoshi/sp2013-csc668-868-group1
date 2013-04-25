package org.openmrs.module.basicmodule.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.Note;
import org.openmrs.module.basicmodule.NoteService;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PatientNotesPortletController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelMap model = new ModelMap();
        //Integer patientId = (Integer) request.getAttribute("patientId");
        String patientIdStr = (String) request.getParameter("patientId");
        Integer patientId = Integer.valueOf(patientIdStr);
        Patient p = Context.getPatientService().getPatient(patientId);
        System.out.println("PatientNotesPortletController handleRequest **************************\n"
                + "Patient id: " + patientIdStr);
        model.put("patient", p);
        List<Note> nots = Context.getService(NoteService.class).getNotesByPatient(p);
        ArrayList<Note> notes = new ArrayList<Note>();
        if (nots.size() > 4) {
            for (int i = 0; i < 4; i++) {
                notes.add(nots.get(i));
            }
        } else {
            notes.addAll(nots);
        }
        model.put("notes", notes);

        String filepath;
        // Put any files for reading in the following directory - unix/windows
        if (OpenmrsConstants.UNIX_BASED_OPERATING_SYSTEM) {
            filepath = System.getProperty("user.home") + File.separator + ".OpenMRS";
        } else {
            filepath = System.getProperty("user.home") + File.separator + "Application Data" + File.separator
                    + "OpenMRS";
        }

        filepath = filepath + File.separator;
        File folder = new File(filepath);

        if (!folder.exists()) {
            model.put("prop", "NO SUCH FILE");
            return new ModelAndView("/module/basicmodule/portlets/patientNotes", model);
        }
        BufferedReader r;
        try {
            String line = "";
            r = new BufferedReader(new FileReader(filepath + "openmrs-runtime.properties.txt"));
            for (int i = 0; i < 3; i++) {
                line += r.readLine() + "<br>";
            }
            System.out.println("Property file: " + filepath + "openmrs-runtime.properties.txt" + "   Line: " + line);
            model.put("prop", line);
            r.close();
        } catch (Exception ex) {
            Logger.getLogger(PatientNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }


        return new ModelAndView("/module/basicmodule/portlets/patientNotes", model);
    }

    /**
     * WHY DOESN'T THE FOLLOWING MAPPING WORK????
     *
     * @param patient
     * @param title
     * @param text
     * @param returnUrl
     * @return
     */
    @RequestMapping(value = "/module/basicmodule/addAnotherNote", method = RequestMethod.POST)
    public String addAnotherNote(
            @RequestParam("patient_id") Patient patient,
            @RequestParam("title") String title,
            @RequestParam("text") String text,
            @RequestParam(value = "returnUrl", required = false) String returnUrl) {

        Note note = new Note();
        note.setPatient(patient);
        note.setTitle(title);
        note.setText(text);
        Context.getService(NoteService.class).saveNote(note);
        System.out.println("addNote method***************");
        if (returnUrl == null) {
            returnUrl = "notes.form?patient_id=" + patient.getPatientId();
        }

        // this goes to /openmrs/module/basicmodule/notes.form typically
        return "redirect:" + returnUrl;
    }
}
