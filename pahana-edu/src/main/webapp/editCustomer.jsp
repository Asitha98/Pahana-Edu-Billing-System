<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Edit Customer — Pahana Billing</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/styles.css'/>">
</head>
<body>
  <jsp:include page="header.jsp"/>
  <div class="container">
    <h1>Edit Customer</h1>
    <form method="post" action="<c:url value='/customer/edit'/>" class="card narrow">
      <input type="hidden" name="id" value="${customer.customerId}">
      <label>Account No <input name="account_no" value="${customer.accountNo}" required></label>
      <label>Name <input name="name" value="${customer.name}" required></label>
      <label>Address <input name="address" value="${customer.address}"></label>
      <label>Phone <input name="phone" value="${customer.phone}"></label>
      <label>Units <input type="number" name="units" min="0" value="${customer.units}"></label>
      <button type="submit">Update</button>
    </form>
  </div>
  <jsp:include page="footer.jsp"/>
</body>
</html>
