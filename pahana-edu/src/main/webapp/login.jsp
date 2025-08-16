<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Pahana Edu</title>
</head>
<body>
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
  <input type="text"     name="username" required />
  <input type="password" name="password" required />
  <button type="submit">Login</button>
  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
</form>
    
    
</body>
</html>