package org.openmrs.module.basicmodule.extension.html;

    import org.openmrs.module.Extension;
    import org.openmrs.module.web.extension.PatientDashboardTabExt;

    public class VitalsPatientDashboardTab extends PatientDashboardTabExt {

       public Extension.MEDIA_TYPE getMediaType() {
          return Extension.MEDIA_TYPE.html;
       }
       
       @Override
       public String getPortletUrl() {
          return "patientVitals";
       }

       @Override
       public String getRequiredPrivilege() {
          return "Patient Dashboard - View Vitals";
       }

       @Override
       public String getTabId() {
          return "PatientVitals";
       }

       @Override
       public String getTabName() {
          return "Vitals";
       }
       
    }