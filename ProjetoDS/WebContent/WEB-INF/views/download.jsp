<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Download arquivo Excel</title>
</head>
<body>


<form name="formDownload" action="/ProjetoDS/download" method="post" enctype="multipart/form-data">
<input type="hidden" name="pathArquivo" value="${pathArquivo}">
<h3>Arquivo excel gerado:</h3> <br>
<a href="javascript:formDownload.submit();">Download</a>

</form>

</body>
</html>