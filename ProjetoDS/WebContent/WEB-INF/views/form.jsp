<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload</title>
</head>
<body>

<form action="/ProjetoDS/upload" method="post" enctype="multipart/form-data">
  <p>
    <label for="file">Arquivo para fazer upload</label>
    <input type="file" name="file" />
    <input type="submit" name="submit" value="Upload" />
  </p>
</form>

</body>
</html>