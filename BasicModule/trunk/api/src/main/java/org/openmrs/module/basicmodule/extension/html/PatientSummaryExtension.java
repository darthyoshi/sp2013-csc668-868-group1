/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.basicmodule.extension.html;

import java.util.Map;
import org.openmrs.module.Extension;

public class PatientSummaryExtension extends Extension {

    	private String patientId = "";

    public Extension.MEDIA_TYPE getMediaType() {
        return Extension.MEDIA_TYPE.html;
    }

    	@Override
	public String getOverrideContent(final String bodyContent) {
            return " &nbsp;<a href='module/basicmodule/patientsummarylink.form?patientId=" + patientId + "' target='_blank'>View Summary</a> ";
            //return "<b>OUTPUT ALERTS HERE FOR PATIENT ID: &nbsp;" + patientId + " <i>&nbsp;&nbsp;      CHECK IN URL FOR PATIENT NUMBER</i> </b>";
	}

    	@Override
	public void initialize(final Map<String, String> parameters) {
		patientId = parameters.get("patientId");
	}

}