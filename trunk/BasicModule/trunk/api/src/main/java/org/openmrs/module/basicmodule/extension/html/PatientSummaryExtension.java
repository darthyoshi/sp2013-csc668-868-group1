/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.extension.html;

import java.util.*;
import org.openmrs.module.basicmodule.dsscompiler.compiler.DSSRuleService;
import java.util.Map;
import org.openmrs.module.Extension;

public class PatientSummaryExtension extends Extension {

    	private String patientId = "";

    public Extension.MEDIA_TYPE getMediaType() {
        return Extension.MEDIA_TYPE.html;
    }

    	@Override
	public String getOverrideContent(final String bodyContent) {
            DSSRuleService service = DSSRuleService.getRuleService();
            
            List<String> rules = 
                    service.runRules(Integer.parseInt(patientId), "patientDashboard");
            String alerts = "";
            for(int i = 0; i < rules.size(); i++)
                alerts += rules.get(i) + "\n";
          
            return " &nbsp;<a href='module/basicmodule/patientsummarylink.form?patientId=" + patientId + "' target='_blank'>View Summary</a> <br/> "
                    + "<font color = red><b>" + alerts + "</b></font>";
        }

    	@Override
	public void initialize(final Map<String, String> parameters) {
		patientId = parameters.get("patientId");
	}

}