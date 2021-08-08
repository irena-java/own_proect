<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>



<%ArrayList<Rates> rates = new ArrayList<Rates>();%>
  rates.add((Rates)session.getAttribute("rates"));
    for(int i=0;i<rates.size();i++)
      {%>
          <tr>
              <td><% out.println(i+1); %></td>
              <td><% rates.g; %></td>
              <td><% cartobj.get(i).getPrice(); %></td>
              <td><% cartobj.get(i).getSales_Address(); %></td>
              <td><% cartobj.get(i).getOrder_Date(); %></td>
              <td><% cartobj.get(i).getQuantity(); %></td>
          </tr>



      <% } %>



<html>
<head>
    <title> курсы </title>
</head>
<body>
<c:forEach items="${rates}" var="rate">
    <br>
    <c:out value="${rate.getCurrencyName}"/>
    <c:out value="${rate.getCurrencyRate}"/>
    <br>
</c:forEach>

<a href="items">view all гуд продуктс</a>

</body>
</html>