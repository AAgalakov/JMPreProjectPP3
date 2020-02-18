<%--
  Created by IntelliJ IDEA.
  User: andreyagalakov
  Date: 06.02.2020
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>

Hello, ${user.name}!

<form action="/logout" method="GET">
    <input type="submit" value="Log out">
</form>

</body>
</html>
