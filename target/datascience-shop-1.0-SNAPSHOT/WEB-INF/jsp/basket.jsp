<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%--@elvariable id="item" type="com.datascience.shop.entity.Item"--%>
<%--@elvariable id="basket" type="com.datascience.shop.entity.Basket"--%>
<html>
<head>
    <title>Profile </title>
</head>
<body>
<c:forEach items="${basket.items}" var="item">
    <br>
    <c:out value="${item.id}"/>
    <c:out value="${item.dataScienceSection}"/>
    <c:out value="${item.dataScienceDirection}"/>
    <c:out value="${item.jobType}"/>
    <c:out value="${item.startDate}"/>
    <c:out value="${item.deadline}"/>
    <c:out value="${item.price}"/>
    <a href="deleteFromBasket?itemId=${item.id}">Удалить из корзины these good products</a>
    <br>
</c:forEach>

<a href="items">view all гуд продуктс</a>

</body>
</html>