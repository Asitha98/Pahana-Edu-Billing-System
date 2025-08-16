<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Items — Pahana Billing</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/styles.css'/>">
</head>
<body>
  <jsp:include page="header.jsp"/>
  <div class="container">
    <div class="between">
      <h1>Items</h1>
      <a class="button" href="<c:url value='/item/add'/>">+ Add Item</a>
    </div>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>ID</th><th>SKU</th><th>Name</th><th>Unit Price</th><th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="it" items="${items}">
            <tr>
              <td>${it.itemId}</td>
              <td>${it.sku}</td>
              <td>${it.name}</td>
              <td>${it.unitPrice}</td>
              <td class="actions">
                <a href="<c:url value='/item/edit?id=${it.itemId}'/>">Edit</a>
                <form method="post" action="<c:url value='/item/delete'/>" style="display:inline">
                  <input type="hidden" name="id" value="${it.itemId}">
                  <button type="submit" class="danger">Delete</button>
                </form>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty items}">
            <tr><td colspan="5" class="muted">No items found.</td></tr>
          </c:if>
        </tbody>
      </table>
    </div>
  </div>
  <jsp:include page="footer.jsp"/>
</body>
</html>
