<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar">
  <div class="brand">Pahana Billing</div>
  <nav>
    <a href="<c:url value='/admin'/>">Dashboard</a>
    <a href="<c:url value='/customers'/>">Customers</a>
    <a href="<c:url value='/items'/>">Items</a>
    <a href="<c:url value='/bills'/>">Bills</a>
    <a href="<c:url value='/logout'/>">Logout</a>
  </nav>
</div>
