<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action="/ProjetoDS/download" method="post" enctype="multipart/form-data">
<input type="hidden" name="pathArquivo" value="${pathArquivo}">
<input type="submit" value="Download">

</form>

</body>
</html>