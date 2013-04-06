<%-- 
    Document   : dssForm
    Created on : Apr 5, 2013, 9:33:42 PM
    Author     : bia
--%>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>


<form method="post" action="saveDSSFile.form">
        File Name: <input type="text" name="filename"/><br/>
        DSS Code: <br/>
        <textarea type ="text" id="dss_code" style="white-space:pre-wrap; height:500px;width:600px"></textarea><br/>
        <input type="submit" value="Save File"/>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
