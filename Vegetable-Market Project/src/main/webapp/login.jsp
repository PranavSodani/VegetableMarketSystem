<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/component/allCss.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style.css">
</head>
<body>
  <%@ include file="component/navbar.jsp"%>
  <div class="login-container">
    <h2 class="login-header">
      <img src="assets/img/market-logo.avif" width="50" class="mr-2" alt="Market Logo">
      Login
    </h2>
    <% String error = (String) session.getAttribute("errorMessage"); %>
    <% if (error != null) { %>
        <div style="color:red; font-size:12px; margin-bottom:10px;"><%= error %></div>
        <% session.removeAttribute("errorMessage"); %>
    <% } %>
    <form action="LoginServlet" method="post">
      <div class="form-group">
        <label for="email">Email Address</label>
        <input type="email" class="form-control" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" required>
      </div>

      <!-- User Type Selection -->
      <div class="form-group user-type-group">
        <label>User Type</label><br>
        <input type="radio" id="user" name="userType" value="user" checked>
        <label for="user">User</label>
        <input type="radio" id="vendor" name="userType" value="vendor">
        <label for="vendor">Vendor</label>
      </div>

      <button type="submit" class="btn login-btn btn-block">Login</button>
      <div class="mt-3 text-center">
        <a href="forgot-password.jsp" class="forgot-password">Forgot Password?</a>
      </div>
      <div class="mt-2 text-center">
        <span>Don't have an account? </span>
        <a href="register.jsp" class="register-link">Register here</a>
      </div>
    </form>
  </div>
</body>
</html>
