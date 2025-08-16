<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Login — Pahana Billing</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/styles.css'/>">
</head>
<body>
  <div class="auth-wrap">
    <h1>Pahana Billing</h1>
    <form method="post" action="<c:url value='/login'/>" class="card narrow">
      <label>Username
        <input type="text" name="username" required>
      </label>
      <label>Password
        <input type="password" name="password" required>
      </label>
      <button type="submit">Sign in</button>

      <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
    </form>
  </div>
</body>
</html>
