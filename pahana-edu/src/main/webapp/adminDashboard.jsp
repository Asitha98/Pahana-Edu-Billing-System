<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Dashboard — Pahana Billing</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/styles.css'/>">
</head>
<body>
  <jsp:include page="header.jsp"/>
  <div class="container">
    <h1>Dashboard</h1>
    <div class="grid">
      <a class="card link" href="<c:url value='/customers'/>">Manage Customers</a>
      <a class="card link" href="<c:url value='/items'/>">Manage Items</a>
      <a class="card link" href="<c:url value='/bills'/>">View Bills</a>
    </div>
  </div>
  <jsp:include page="footer.jsp"/>
</body>
</html>
