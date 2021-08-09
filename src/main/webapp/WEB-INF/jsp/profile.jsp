<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="user" type="com.datascience.shop.entity.User"--%>

<html>
<head>
    <title>Profile </title>
</head>
<body>
  <p>Id : ${user.id}</p>
  <p>Name : ${user.name}</p>

  <a href="items">view all items</a>
  <a href="basket">view my basket</a>

</body>
</html>