<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:htmlInclude file="/scripts/jquery/jquery.min.js" />
<openmrs:htmlInclude file="/dwr/engine.js" ></openmrs:htmlInclude>
<openmrs:htmlInclude file="/dwr/util.js" ></openmrs:htmlInclude>
<script src="<openmrs:contextPath/>/dwr/interface/DWRMyNotesService.js"></script>
<script src="<openmrs:contextPath/>/dwr/interface/DWRVitalsService.js"></script>
<script src="<openmrs:contextPath/>/dwr/interface/DWRPatientService.js"></script>




<h2><spring:message code="Patient Vitals" /></h2>


<script>
function $_GET(q,s) {
    s = (s) ? s : window.location.search;
    var re = new RegExp('&amp;'+q+'=([^&amp;]*)','i');
    return (s=s.replace(/^\?/,'&amp;').match(re)) ?s=s[1] :s='';
}
var patientID = $_GET("patientId");

DWRVitalsService.getPatientEncounters(patientID, 2, encountersFound);
function encountersFound(encounters) {
    console.log("Found " + encounters.length + "encounters");
    var z = 0;
    //iterate through encounters
    for (var i = 0; i < encounters.length; i++) {
        console.log("Visiting encounter " + i + ", obs count " + encounters[i].obs.length);
        var r = 0;

        //iterate through observations
        for (var j = 0; j < encounters[i].obs.length; j++) {
            console.log("Visiting obs " + j);
            var obs = encounters[i].obs[j];
            // Is this an observation of vitals?
            if (obs.concept.conceptId >= 5085 &&
                obs.concept.conceptId <= 5089) {
                var prefix = "e" + i + obs.concept.conceptId;
                document.getElementById(prefix + "c0").innerHTML=obs.concept.displayString;
                document.getElementById(prefix + "c1").innerHTML=obs.valueNumeric;
                console.log(obs.concept.displayString + "=" + obs.valueNumeric);
                r++;
            }
        }

        //TODO: replace %encounter_date% with proper variable
        if (r > 0) {
            document.getElementById("date" + z).innerHTML = %encounter_date%;
            z++;
        }
    }
}
// 5089 WEIGHT
// 5085 systolic pressure
// 5086 diastolic pressure
// 5088 WHO stage
</script>

<table id = "encounter0">
    <tr>
        <td id = "date0"></td>
    </tr>
    <tr>
        <td id ="e05089c0"></td>
        <td id ="e05089c1"></td>
    </tr>
    <tr>
        <td id ="e05085c0"></td>
        <td id ="e05085c1"></td>
    </tr>
    <tr>
        <td id ="e05086c0"></td>
        <td id ="e05086c1"></td>
    </tr>
    <tr>
        <td id ="e05088c0"></td>
        <td id ="e05088c1"></td>
    </tr>
</table>

<br>

<table id = "encounter1">
    <tr>
        <td id = "date1"></td>
    </tr>
    <tr>
        <td id ="e15089c0"></td>
        <td id ="e15089c1"></td>
    </tr>
    <tr>
        <td id ="e15085c0"></td>
        <td id ="e15085c1"></td>
    </tr>
    <tr>
        <td id ="e15086c0"></td>
        <td id ="e15086c1"></td>
    </tr>
    <tr>
        <td id ="e15088c0"></td>
        <td id ="e15088c1"></td>
    </tr>
</table>
