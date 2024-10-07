<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form action="/FastjsonJndi_war_exploded/json" method="post" >
  pleasr input json data: <input type="text" name="str"><br>
  <input type="submit" value="提交">
</form>
</body>
</html>