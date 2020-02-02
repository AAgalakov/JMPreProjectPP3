<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Update User</title>
</head>
<body>
<table style=" width: 50%; border: 4px double black;">
    <tr>
        <th> User id</th>
        <th> User name</th>
        <th> User age</th>
        <th> Action</th>
    </tr>
    <tr>
        <form action="/userUpdatedToBD" method="post">
            <td style="text-align: center"> ${user.id}</td>
            <td style="text-align: center"><input name="name" value="${user.name}"/></td>
            <td style="text-align: center"><input name="age" value="${user.age}" type="number" min=1/></td>
            <td style="text-align: center">
                <button name="id" value="${user.id}">Submit</button>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
