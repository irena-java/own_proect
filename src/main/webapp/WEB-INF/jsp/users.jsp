<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="user" type="com.datascience.shop.entity.User"--%>
<html>
<head>
    <title>user </title>
</head>
<body>
 <a href="items">view all items</a>

<c:forEach items="${users}" var="user">
    <br>
    <c:out value="${user.id}"/>
    <c:out value="${user.name}"/>
    <c:out value="${user.country}"/>
    <a href="deleteUser?userId=${user.id}">delete this bad user</a>
</c:forEach>

</body>
</html>