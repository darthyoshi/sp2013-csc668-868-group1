/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.web.controller;

/**
 *
 * @author bia
 */
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "module/basicmodule/downloadRule.form")

public class FileDownload {
    private static final int BUFFER_SIZE = 5120; // 5KB
    private String filePath;
    private final String SUCCESS_FORM_VIEW = "/module/basicmodule/dssRuleForm";
    
    @RequestMapping(method = RequestMethod.POST)  
    public String downloadRule(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("ruleList") String ruleName)
    {
        try {  
            System.out.println("downloadRule method DSSRuleController*************");
            ruleName = ruleName.replaceAll(" ", "");
            File dir = OpenmrsUtil.getDirectoryInApplicationDataDirectory("dssModule");
            filePath = dir.getAbsolutePath() + File.separator +
                    ruleName + "Source-Dss.txt";
            File file = new File(filePath);
            ServletOutputStream outStream = response.getOutputStream();

            response.setContentLength((int)file.length());
            
            response.setHeader("Content-Disposition", "attachment; filename=\"" + ruleName + ".dss\"");
           
            byte[] byteBuffer = new byte[BUFFER_SIZE];
            DataInputStream in = new DataInputStream(new FileInputStream(file));
           
            int length;
            while (((length = in.read(byteBuffer)) != -1))
            {
                outStream.write(byteBuffer,0,length);
            }
           
            in.close();
            outStream.close();
        } catch (IOException ex) {
            Logger.getLogger(DSSRuleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS_FORM_VIEW;
    }
}
