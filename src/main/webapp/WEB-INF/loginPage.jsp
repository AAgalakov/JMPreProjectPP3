<%--
  Created by IntelliJ IDEA.
  User: andreyagalakov
  Date: 04.02.2020
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<fieldset>
    <legend>Вход на сайт</legend>
    <form action="/" method="POST">
        <p><strong>Логин:</strong>
            <input maxlength="25" size="40" name="name"></p>
        <p><strong>Пароль:</strong>
            <input type="password" maxlength="25" size="40" name="password"></p>
        <input type="submit" value="OK">
    </form>
</fieldset>
</body>
</html>
