package org.openmrs.module.dssmodule.web.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.dssmodule.DSSRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for dssUpload.form page which allows the user
 * to upload a .dss file from their machine. The file is automatically
 * compiled.
 * 
 * @author bia
 */

@Controller
@RequestMapping(value = "module/dssmodule/dssUpload.form")
public class DSSUploadController
{
    protected final Log log = LogFactory.getLog(getClass());
    
    private final String SUCCESS_FORM_VIEW = "/module/dssmodule/dssUploadForm";
	
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(){
        System.out.println("GET method DSSUploadController***************");
	return SUCCESS_FORM_VIEW;
        //return new ModelAndView(SUCCESS_FORM_VIEW, setModel("false", ""));
    }
    
    /**
     * Method is called when the submit button is hit. The uploaded file
     * is copied on to the server (saved at user's home directory). If the
     * file already exists, it will be overwritten.
     * File is automatically compiled and any errors reported back to webpage.
     * Errors 
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)   
    public ModelAndView handleRequest(@RequestParam("fileName") CommonsMultipartFile file) 
    {
        Map model;
        System.out.println("handleRequest method DSSUploaderController*************");
        if (!file.isEmpty())
        {   
            try{
                DSSRuleService service = DSSRuleService.getRuleService();
                FileItem fileItem = file.getFileItem();
                String fileName = fileItem.getName();
                fileName = fileName.replace(".dss", "");
                service.store(fileName, fileItem.getString());
            }
            catch(Exception e)
            {
                System.out.println("********* Exception occured in DSSUploadController handleRequest" 
                        + e.getMessage());
                model = getModel("err", e.getMessage());
                return new ModelAndView(SUCCESS_FORM_VIEW, model);
            }
        }
        else
        {       
                model = getModel("err", "Empty file.");
                return new ModelAndView(SUCCESS_FORM_VIEW, model);
        }
        
        model = getModel("s", "Succesfully uploaded file");
        return new ModelAndView(SUCCESS_FORM_VIEW, model);
    }
    
    private Map getModel(String status, String message)
    {
        Map <String, String> model = new HashMap<String, String>();
        model.put("status", status);
        model.put("message", message);
        return model;
    }
}
