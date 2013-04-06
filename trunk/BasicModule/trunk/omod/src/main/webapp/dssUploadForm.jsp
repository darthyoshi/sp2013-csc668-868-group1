<%-- 
    Document   : dssUploadForm
    Created on : Apr 6, 2013, 12:15:15 PM
    Author     : bia
--%>


<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<form action="DSSUpload" method="post" enctype="multipart/form-data">
    Upload a File: <br/>
    <input type="file" name="fileName"><br/>
    <input type="submit" name="button" value="DSSUpload">
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>