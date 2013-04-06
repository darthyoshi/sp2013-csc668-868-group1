/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

import javax.servlet.http.HttpServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bia
 */

@Controller
@RequestMapping(value = "module/basicmodule/dssUpload.form")
public class DSSUploadController extends HttpServlet
{
    protected final Log log = LogFactory.getLog(getClass());
    
    private final String SUCCESS_FORM_VIEW = "/module/basicmodule/dssUploadForm";
	
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(){
        System.out.println("GET method DSSUploadController***************");
	return SUCCESS_FORM_VIEW;
    }
    
}
