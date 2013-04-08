/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.basicmodule.dsscompiler.compiler.DSSCompiler;
import org.springframework.stereotype.Controller;
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
    
    @RequestMapping(method = RequestMethod.POST)   
    public String handleRequest(@RequestParam("filename") String fileName, 
                                @RequestParam("dss_code") String code) 
    {
        System.out.println("handleRequest method DSSController*************");
        try
        {
            File file = new File(System.getProperty("user.home")
                        + System.getProperty("file.separator")
                        + fileName);
            file.setWritable(true);
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(code);
            writer.close();
            
            (new DSSCompiler(file.getPath())).compileProgram(); 
        }
        catch(Exception e) { System.out.println(e.getMessage());}
        
        return SUCCESS_FORM_VIEW;
    }
}
