<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>User Info</title>
</head>
<body>
<style>
    table {
        border-collapse: collapse;
    }

    th, td {
        text-align: center;
        padding: 8px;
    }
    tr:nth-child(even) {background-color: #f2f2f2;}
</style>
<table>
    <tr>
        <th> User id</th>
        <th> User name</th>
        <th> User age</th>
        <th colspan="2"> Action</th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>
                <form action="/userUpdate" method="post">
                    <button name="id" value="${user.id}" type="submit">Edit</button>
                </form>
            </td>
            <td>
                <form action="/delete" method="post">
                    <button name="id" value="${user.id}" type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <form action="/" method="post">
            <td></td>
            <td><input name="name" placeholder="Your Name"/></td>
            <td><input name="age" placeholder="Your Age" type="number" min=1/></td>
            <td colspan="2">
                <button>Add new user</button>
            </td>
        </form>
    </tr>
</table>
<form action="/userDeleteAll" method="post">
    Delete all Users
    <input type="submit" value="Delete all">
</form>
</body>
</html>
