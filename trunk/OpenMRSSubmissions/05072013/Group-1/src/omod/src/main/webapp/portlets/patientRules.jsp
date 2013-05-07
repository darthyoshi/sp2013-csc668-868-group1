<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:htmlInclude file="/scripts/jquery/jquery.min.js" />
<openmrs:htmlInclude file="/dwr/engine.js" ></openmrs:htmlInclude>
<openmrs:htmlInclude file="/dwr/util.js" ></openmrs:htmlInclude>

<script src="<openmrs:contextPath/>/dwr/interface/DWRRuleService.js"></script>





<h2><spring:message code="Patient Rules" /></h2>


<script>
function $_GET(q,s) {
    s = (s) ? s : window.location.search;
    var re = new RegExp('&amp;'+q+'=([^&amp;]*)','i');
    return (s=s.replace(/^\?/,'&amp;').match(re)) ?s=s[1] :s='';
}
var patientID = $_GET("patientId");
DWRRuleService.runRules(patientID, "patientSummary",ruleResults)

function ruleResults(alerts) {
    resultString="Results:<br/>\n";
    for (var i = 0; i < alerts.length; i++) {
        console.log("Alert " + i + " was: " + alerts[i]);
        resultString += alerts[i] + "<br/>\n";
    }
    document.getElementById("results").innerHTML=resultString;
}
// 5089 WEIGHT
// 5085 systolic pressure
// 5086 diastolic pressure
// 5088 WHO stage
</script>

<p id ="results">
</p>
