<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Customers — Pahana Billing</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/styles.css'/>">
</head>
<body>
  <jsp:include page="header.jsp"/>
  <div class="container">
    <div class="between">
      <h1>Customers</h1>
      <a class="button" href="<c:url value='/customer/add'/>">+ Add Customer</a>
    </div>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>ID</th><th>Account No</th><th>Name</th><th>Phone</th><th>Units</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="cst" items="${customers}">
            <tr>
              <td>${cst.customerId}</td>
              <td>${cst.accountNo}</td>
              <td>${cst.name}</td>
              <td>${cst.phone}</td>
              <td>${cst.units}</td>
              <td class="actions">
                <a href="<c:url value='/customer/edit?id=${cst.customerId}'/>">Edit</a>
                <form method="post" action="<c:url value='/customer/delete'/>" style="display:inline">
                  <input type="hidden" name="id" value="${cst.customerId}">
                  <button type="submit" class="danger">Delete</button>
                </form>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty customers}">
            <tr><td colspan="6" class="muted">No customers found.</td></tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
  <jsp:include page="footer.jsp"/>
</body>
</html>
