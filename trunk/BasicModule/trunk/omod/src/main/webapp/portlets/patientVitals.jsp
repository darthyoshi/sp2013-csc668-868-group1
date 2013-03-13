<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:htmlInclude file="/scripts/jquery/jquery.min.js" />
<openmrs:htmlInclude file="/dwr/engine.js" ></openmrs:htmlInclude>
<openmrs:htmlInclude file="/dwr/util.js" ></openmrs:htmlInclude>
<script src="<openmrs:contextPath/>/dwr/interface/DWRMyNotesService.js"></script>
<script src="<openmrs:contextPath/>/dwr/interface/DWRVitalsService.js"></script>
<script src="<openmrs:contextPath/>/dwr/interface/DWRPatientService.js"></script>




<h2><spring:message code="Patient Vitals" /></h2>


<script>
DWRVitalsService.getPatientEncounters("John", 2, encountersFound);
function encountersFound(encounters) {
    console.log("Found " + encounters.length + "encounters");
    for (var i = 0; i < encounters.length; i++) {        
        console.log("Visiting encounter " + i + ", obs count " + encounters[i].obs.length);
        var r = 0;
        for (var j = 0; j < encounters[i].obs.length; j++) {
            console.log("Visiting obs " + j);
            var obs = encounters[i].obs[j];
            // Is this an observation of vitals?
            if (obs.concept.conceptId >= 5085 &&
                obs.concept.conceptId <= 5089) {
                var prefix = "e" + i + "r" + r;
                document.getElementById(prefix + "c0").innerHTML=obs.concept.displayString;
                document.getElementById(prefix + "c1").innerHTML=obs.valueNumeric;
                console.log(obs.concept.displayString + "=" + obs.valueNumeric);
                r++;
            }
        }
    }
}
// 5089 WEIGHT
// 5085
// 5086
// 5088
</script>

<table id = "encounter0">
    <tr>
        <td id ="e0r0c0"></td>
        <td id ="e0r0c1"></td>
    </tr>
    <tr>
        <td id ="e0r1c0"></td>
        <td id ="e0r1c1"></td>
    </tr>
    <tr>
        <td id ="e0r2c0"></td>
        <td id ="e0r2c1"></td>
    </tr>
    <tr>
        <td id ="e0r3c0"></td>
        <td id ="e0r3c1"></td>
    </tr>    
</table>

<table id = "encounter1">
    <tr>
        <td id ="e1r0c0"></td>
        <td id ="e1r0c1"></td>
    </tr>
    <tr>
        <td id ="e1r1c0"></td>
        <td id ="e1r1c1"></td>
    </tr>
    <tr>
        <td id ="e1r2c0"></td>
        <td id ="e1r2c1"></td>
    </tr>
    <tr>
        <td id ="e1r3c0"></td>
        <td id ="e1r3c1"></td>
    </tr>    
</table>


<br> <br>


