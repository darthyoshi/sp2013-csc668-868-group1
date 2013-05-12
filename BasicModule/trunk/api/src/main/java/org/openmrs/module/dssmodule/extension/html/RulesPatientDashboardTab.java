package org.openmrs.module.dssmodule.extension.html;

    import org.openmrs.module.Extension;
    import org.openmrs.module.web.extension.PatientDashboardTabExt;

    public class RulesPatientDashboardTab extends PatientDashboardTabExt {

       public Extension.MEDIA_TYPE getMediaType() {
          return Extension.MEDIA_TYPE.html;
       }
       
       @Override
       public String getPortletUrl() {
          return "patientRules";
       }

       @Override
       public String getRequiredPrivilege() {
          return "Patient Dashboard - View Vitals";
       }

       @Override
       public String getTabId() {
          return "PatientRules";
       }

       @Override
       public String getTabName() {
          return "Rules";
       }
       
    }