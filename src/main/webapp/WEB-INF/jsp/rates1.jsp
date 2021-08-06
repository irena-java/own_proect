<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="rates" type="com.datascience.shop.Rates"--%>
<html>
<head>
    <title>Rates </title>
</head>
<body>
<c:forEach items="${rates}" var="rate">
    <br>
    <c:out value="${rate.currencyName}"/>
    <c:out value="${rate.currencyRate}"/>
    <br>
</c:forEach>

<a href="items">view all гуд продуктс</a>

</body>
</html>