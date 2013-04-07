/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.basicmodule.dsscompiler.compiler.DSSCompiler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bia
 */

@Controller
@RequestMapping(value = "module/basicmodule/dssUpload.form")
public class DSSUploadController
{
    protected final Log log = LogFactory.getLog(getClass());
    
    private final String SUCCESS_FORM_VIEW = "/module/basicmodule/dssUploadForm";
	
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(){
        System.out.println("GET method DSSUploadController***************");
	return SUCCESS_FORM_VIEW;
    }
    
    @RequestMapping(method = RequestMethod.POST)   
    public String handleRequest(@RequestParam("fileName") CommonsMultipartFile file) 
    {
        System.out.println("handleRequest method DSSUploaderController*************");
        if (!file.isEmpty())
        {   
            try{
                FileItem fileItem = file.getFileItem();
                File uploadFile = new File(System.getProperty("user.home")
                        + System.getProperty("file.separator")
                        + fileItem.getName());
                uploadFile.setWritable(true);
                fileItem.write(uploadFile);
                (new DSSCompiler(uploadFile.getPath())).compileProgram();  
            }
            catch(Exception e){System.out.println("!!!!! EXCEPTION THROWN !!!!" + e.getMessage());}
            
        }
        else
            System.out.println("File is Empty");
                
        return SUCCESS_FORM_VIEW;
    }
}
