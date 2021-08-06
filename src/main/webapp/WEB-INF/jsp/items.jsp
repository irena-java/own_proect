<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="item" type="com.datascience.shop.entity.Item"--%>
<html>
<head>
    <title>Profile </title>
</head>
<body>
<a href="basket">view my basket</a>
<a href="rates">RATES</a>

<c:forEach items="${items}" var="item">
    <br>
    <c:out value="${item.id}"/>
    <c:out value="${item.dataScienceSection}"/>
    <c:out value="${item.dataScienceDirection}"/>
    <c:out value="${item.jobType}"/>
    <c:out value="${item.startDate}"/>
    <c:out value="${item.deadline}"/>
    <c:out value="${item.price}"/>
    <a href="addToBasket?itemId=${item.id}">Buy these good products</a>
</c:forEach>

</body>
</html>