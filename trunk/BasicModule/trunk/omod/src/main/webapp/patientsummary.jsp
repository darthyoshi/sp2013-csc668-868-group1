<%--
    Document   : patientsummarylink
    Created on : Mar 18, 2013, 10:39:06 PM
    Author     : Kay Choi
--%>
<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<!--
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
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>
<table width=100%>
    <tr bgcolor="4D9999">
        <td colspan=2><font color="white">Patient Information</font></td>
    </tr>
    <tr>
        <td width=30% ><b>NAME:</b> ${patientName}</td>
        <td width=70% align="right"><b>Clinic Location:</b> %patientCity%</td>
    </tr>
    <tr>
        <td>${patientGender}, ${patientAge} yrs<td>
    </tr>
    <tr>
        <td colspan=2><center><b>Address:</b> ${patientAddress}</center>
    </tr>
    <tr>
        <td colspan=2><center><b>Last seen on:</b> ${patientLastSeen}</center>
    </tr>
</table>
<p>
<table width=100%>
    <tr bgcolor="4D9999">
        <td colspan=2><font color="white">Patient Summary</font></td>
    </tr>
    <tr>
        <td width=30% ><b>WHO stage:</b> ${whoStage}</td>
        <td width=70% ><b>Current art regimen drugs:</b> </td>
    </tr>
    <tr>
        <td width=30% ><b>TB Status:</b> ${tbStatus}</td>
        <td width=70% rowspan="2"><b>${ARTRegimen}</b> </td>
    </tr>
    <tr>
        <td width=30% ><b>Allergies:</b> ${allergies}</td>
    </tr>
</table>
<p>
<br><br>
<table width="100%" >
    <tr bgcolor="4D9999">
        <td colspan="5" width="100%" style='border:solid black 1.0pt'><font color="white">Patient Vitals</font></td>
    </tr>
    <tr>
        <td ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
        <td ><b>Weight (KG)</b></td>
        <td ><b>Temperature (C)</b></td>
        <td ><b>Blood Pressure</b></td>
        <td ><b>Karnofsky Score</b></td>
    </tr>
    <tr>
        <td >Enrollment: ${enrollment}</td>
        <td >${enrollWt}</td>
        <td >${enrollTemp}</td>
        <td >${enrollBP}</td>
        <td >${enrollKS}</td>
    </tr>
    <tr>
        <td >Last Visit: ${lastVisit}</td>
        <td >${lastVisitWt}</td>
        <td >${lastVisitTemp}</td>
        <td >${lastVisitBP}</td>
        <td >${lastVisitKS}</td>
    </tr>
</table>
<p>
<br>
<br>
<table width="100%" >
    <tr bgcolor="4D9999">
        <td colspan=3 width="100%"><font color="white">Laboratory Results</font></td>
    </tr>
    <tr>
        <td ><b>CD4</b></td>
        <td ><b>Hemoglobin</b></td>
        <td ><b>Viral Load</b></td>
    </tr>
    <tr>
        <td >${LabCD4}</td>
        <td >${LabHemo}</td>
        <td >${LabViral}</td>
    </tr>
</table>

</body>
</html>


<%@ include file="/WEB-INF/template/footer.jsp"%>