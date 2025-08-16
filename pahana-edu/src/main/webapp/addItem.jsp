<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Add Item — Pahana Billing</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/styles.css'/>">
</head>
<body>
  <jsp:include page="header.jsp"/>
  <div class="container">
    <h1>Add Item</h1>
    <form method="post" action="<c:url value='/item/add'/>" class="card narrow">
      <label>SKU <input name="sku" required></label>
      <label>Name <input name="name" required></label>
      <label>Unit Price <input type="number" step="0.01" name="unit_price" required></label>
      <button type="submit">Save</button>
    </form>
  </div>
  <jsp:include page="footer.jsp"/>
</body>
</html>
