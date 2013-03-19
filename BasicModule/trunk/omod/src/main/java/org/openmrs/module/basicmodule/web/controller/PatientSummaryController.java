/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "module/basicmodule/patientsummarylink.form")
public class PatientSummaryController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	/** Success form view name */
	private final String SUCCESS_FORM_VIEW = "/module/basicmodule/patientsummarylink";

    @RequestMapping(method = RequestMethod.GET)
	public String showForm(){
            System.out.println("PatientSummaryController showForm method***************");
		return SUCCESS_FORM_VIEW;
	}
}
