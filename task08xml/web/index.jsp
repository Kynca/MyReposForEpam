<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 8/15/2021
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!Doctype html>
<html>
<head>
    <title>Parser choose</title>
</head>
<body>
<form action="webXml" method="post">
    <input required="required" type="file" name="fileInput"><br>
    <input type="radio" id="DOM" name="parserChoice" value="DOM" required="required">
    <label for="DOM">DOM</label><br>
    <input type="radio" id="SAX" name="parserChoice" value="SAX">
    <label for="SAX">SAX</label><br>
    <input type="radio" id="STAX" name="parserChoice" value="STAX">
    <label for="STAX">STAX</label><br>
    <input type="submit" name="Submit">
</form>
</body>
</html>
