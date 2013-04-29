<%-- 
    Document   : dssUploadForm
    Created on : Apr 6, 2013, 12:15:15 PM
    Author     : bia
--%>


<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">

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
    
<form action="dssUpload.form" method="post" enctype="multipart/form-data">
    Upload a File: <br/>
    <input type="file" name="fileName"><br/>
    <input type="submit" name="button" value="Upload">
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>