/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author bia
 */

@Controller
@RequestMapping(value = "module/basicmodule/dssLink.form")
public class DSSController
{
    protected final Log log = LogFactory.getLog(getClass());
    
    private final String SUCCESS_FORM_VIEW = "/module/basicmodule/dssForm";
	
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(){
        System.out.println("GET method DSSController***************");
	return SUCCESS_FORM_VIEW;
    }
}
