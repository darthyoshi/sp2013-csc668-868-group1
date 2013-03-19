<%--
    Document   : patientsummarylink
    Created on : Mar 18, 2013, 10:39:06 PM
    Author     : Kay Choi
--%>
<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<script src="<openmrs:contextPath/>/dwr/interface/DWRPatientService.js"></script>
<script src="<openmrs:contextPath/>/dwr/interface/DWRVitalsService.js"></script>

<script>
function $_GET(q,s) {
    s = (s) ? s : window.location.search;
    var re = new RegExp('&amp;'+q+'=([^&amp;]*)','i');
    return (s=s.replace(/^\?/,'&amp;').match(re)) ?s=s[1] :s='';
}
var patientID = $_GET("patientId");

//TODO: retrieve patient data
</script>

<%@ include file="/WEB-INF/template/footer.jsp"%>