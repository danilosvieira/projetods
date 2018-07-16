<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enviar arquivo para processamento</title>
</head>
<body>

<form action="/ProjetoDS/upload" method="post" enctype="multipart/form-data">
  <p>
    <h3><label for="file">Selecione um arquivo:</label></h3>
    <input type="file" name="file" />
    <br><br>
    <input type="submit" name="submit" value="Enviar" />
  </p>
</form>

</body>
</html>