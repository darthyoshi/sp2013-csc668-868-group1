<%-- 
    Document   : dssForm
    Created on : Apr 5, 2013, 9:33:42 PM
    Author     : bia
--%>

<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<script src="<openmrs:contextPath/>/dwr/interface/DWRRuleService.js"></script>
<script type="text/javascript">
        DWRRuleService.listRules(getRuleNames);

        function getRuleNames(names)
        {
            var list = document.getElementById("ruleNames");
            list.options.add(new Option());
            for(i = 0; i < names.length; i++)
            {
                list.options.add(new Option(names[i]));
            }
        }
        function loadRule()
        {
            var form = document.getElementById("ruleNames");
            var ruleName = form.options[form.selectedIndex].text;
            document.getElementById("input_rulename").value = ruleName;
            DWRRuleService.load(ruleName, changeCode);
        }
        
        function changeCode(str)
        {
            document.getElementById("input_code").value = str;
        }
</script>    


<c:set var="stat" value ="${status}"/>
    <c:set var="error" value="err"/>
    <c:set var="success" value="s"/>
    <c:choose>
        <c:when test="${stat == error}">
            <table width="100%" style ="border-style:dashed;border-width:1px" bgcolor="#FFADAD">
                <tr><td><b> ${message}</b></td></tr>
            </table>
        </c:when>
        <c:when test="${stat == success}">
            <table width="100%" style ="border-style:dashed;border-width:1px" bgcolor="#FFFFBD">
                <tr><td> ${message}</td></tr>
            </table>
        </c:when>
    </c:choose>

    
    <select id="ruleNames"></select>
    <button onclick="loadRule()">Load Rule</button>
    
<form method="post" action="dssRules.form" enctype="multipart/form-data">
        Rule Name: 
        <input type="text" id="input_rulename" name="rule_name"/><br/>
        DSS Code: <br/>
        <textarea type ="text" id="input_code" name="dss_code" style="white-space:pre-wrap; height:500px;width:600px"></textarea><br/>
        <input type="submit" value="Save"/>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
