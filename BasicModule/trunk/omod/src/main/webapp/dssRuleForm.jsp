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
        var allRuleNames;
        function getRuleNames(names)
        {
            allRuleNames = names;
            var list = document.getElementById("ruleNames");
            var list2 = document.getElementById("ruleNames2");
            list.options.add(new Option());
            for(i = 0; i < names.length; i++)
            {
                list.options.add(new Option(names[i]));
                list2.options.add(new Option(names[i]));
            }
            document.getElementById("loading").style.display = "none";
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
        
        function confirm_submit()
        {
            var name = document.getElementById("input_rulename").value;
            var exists = false;
            for(i = 0; i < allRuleNames.length; i++)
            {
                if(name === allRuleNames[i]){   exists = true; break; }
            }
          
            if(exists)
                return confirm("Overwrite \"" + name + "\" rule?");
        }
</script>

<div id="loading">
	<img id="loading-image" align="center" src="/openmrs/images/loading.gif" />
</div>

<style type="text/css">
#loading {
  width: 100%;
  height: 100%;
  top: 0px;
  left: 0px;
  position: fixed;
  display: block;
  opacity: 0.7;
  background-color: #fff;
  z-index: 99;
  text-align: center;
}

#loading-image {
  position: fixed;
  top: 50%;
  left: 50%;
  z-index: 100;
}
</style>


    <c:set var="stat" value ="${status}"/>
    <c:set var="error" value="err"/>
    <c:set var="success" value="s"/>
    <c:choose>
        <c:when test="${stat == error}">
            <c:set var="rules" value="${rule_name}"/>
            <c:set var="dss" value="${dssCode}"/>
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
    
<table width="100%">
    <tr >
        <td style="border-style:dotted;border-width:1px;border-color:#B3CFB3" bgcolor="#DDFFDD">
            <font color ="#589358"><b> LOAD AN EXISTING RULE</b></font>
        </td>
    </tr>
    <tr>
        <td bgcolor="#FFFFFF">
            <select id="ruleNames" style="border-style:solid;border-width:1px;border-color:#B3CFB3"></select>
            <button onclick="loadRule()">Load</button>
            <br/><br/>
        </td>
    </tr>
    <tr >
        <td style="border-style:dotted;border-width:1px;border-color:#B3CFB3" bgcolor="#DDFFDD">
            <font color ="#589358"><b> CREATE/EDIT RULES</b></font>
        </td>
    </tr>
    <tr>
        <td bgcolor="#FFFFFF">
            <form method="post" action="dssRules.form" enctype="multipart/form-data">
                Rule Name: 
                <input type="text" id="input_rulename" name="rule_name" style="border-style:solid;border-width:1px;border-color:#B3CFB3"
                       value="<c:out value="${initname}"/>"/>
                <br/>
                DSS Code: <br/>
                <textarea type ="text" id="input_code" name="dss_code" 
                          style="white-space:pre-wrap;height:500px;width:600px;border-style:solid;border-width:1px;border-color:#B3CFB3"><c:out value="${init}"/></textarea><br/>
                <input type="submit" name="save" onclick="return confirm_submit();" value="Save"/> 
            </form>
        </td>
    </tr>
    <tr >
        <td style="border-style:dotted;border-width:1px;border-color:#B3CFB3" bgcolor="#DDFFDD">
            <font color ="#589358"><b>SAVE AN EXISTING RULE</b></font>
        </td>
    </tr>
    <tr>
        <td bgcolor="#FFFFFF">
            
            <select id="ruleNames2" style="border-style:solid;border-width:1px;border-color:#B3CFB3" form="ruleDownload" name="ruleList"></select>
            
            <form action="downloadRule.form" id="ruleDownload" method="post">
                <input type="submit" value="Save Rule">
            </form>
            <br/><br/>
        </td>
    </tr>
</table>
    
<%@ include file="/WEB-INF/template/footer.jsp"%>