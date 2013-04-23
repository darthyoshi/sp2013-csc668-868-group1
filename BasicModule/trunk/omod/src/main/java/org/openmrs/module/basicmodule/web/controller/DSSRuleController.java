/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.basicmodule.dsscompiler.compiler.DSSRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bia
 */

@Controller
@RequestMapping(value = "module/basicmodule/dssRules.form")
public class DSSRuleController
{
    protected final Log log = LogFactory.getLog(getClass());
    
    private final String SUCCESS_FORM_VIEW = "/module/basicmodule/dssRuleForm";
	
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(){
        System.out.println("GET method DSSController***************");
	return SUCCESS_FORM_VIEW;
    }
    
     /**
     * Method is called when the submit button is hit. The file
     * is copied on to the server (saved at user's home directory). If the
     * file already exists, it will be overwritten.
     * File is automatically compiled and any errors reported back to webpage.
     * 
     */
    @RequestMapping(method = RequestMethod.POST)   
    public ModelAndView handleRequest(@RequestParam("rule_name") String ruleName, 
                                @RequestParam("dss_code") String code) 
    {
        Map model;
        
        System.out.println("handleRequest method DSSRuleController*************");
        try
        {
            DSSRuleService ruleService = DSSRuleService.getRuleService();
            ruleService.store(ruleName, code);
        }
        catch(Exception e) { 
                model = getModel("err", e.getMessage());
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
