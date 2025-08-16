<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Bills — Pahana Billing</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/styles.css'/>">
</head>
<body>
  <jsp:include page="header.jsp"/>
  <div class="container">
    <h1>Bills</h1>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>ID</th><th>Customer ID</th><th>Units</th><th>Amount</th><th>Created</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="b" items="${bills}">
            <tr>
              <td>${b.billId}</td>
              <td>${b.customerId}</td>
              <td>${b.units}</td>
              <td>${b.amount}</td>
              <td>${b.createdAt}</td>
            </tr>
          </c:forEach>
          <c:if test="${empty bills}">
            <tr><td colspan="5" class="muted">No bills yet.</td></tr>
          </c:if>
        </tbody>
      </table>
    </div>

    <h2>Generate Bill (Quick)</h2>
    <form method="post" action="<c:url value='/billing/generate'/>" class="card narrow">
      <label>Customer ID <input type="number" name="customer_id" required></label>
      <label>Units <input type="number" name="units" min="0" required></label>
      <label>Rate Per Unit <input type="number" step="0.01" name="rate_per_unit" required></label>
      <button type="submit">Generate</button>
    </form>
  </div>
  <jsp:include page="footer.jsp"/>
</body>
</html>
