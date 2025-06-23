<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/component/allCss.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style.css">
  <script src="${pageContext.request.contextPath}/script/passwordCheck.js"></script>
  <style>
    .error-message {
      color: red;
      font-size: 12px;
      margin-top: 4px;
      display: none;
    }
  </style>
</head>
<body>
  <%@ include file="component/navbar.jsp"%>
  <div class="login-container">
    <h2 class="login-header">
      <img src="assets/img/market-logo.avif" width="50" class="mr-2" alt="Market Logo">
      Register
    </h2>
    <% String error = (String) session.getAttribute("errorMessage"); %>
    <% if (error != null) { %>
        <div style="color:red; font-size:12px; margin-bottom:10px;"><%= error %></div>
        <% session.removeAttribute("errorMessage"); %>
    <% } %>
    <form action="RegisterServlet" method="post" onsubmit="return validateForm()">
      <div class="form-group">
        <label for="name">Full Name</label>
        <input type="text" class="form-control" id="name" name="name" required>
      </div>
      <div class="form-group">
        <label for="email">Email Address</label>
        <input type="email" class="form-control" id="email" name="email" required>
      </div>
      <!-- Add phone and address fields -->
      <div class="form-group">
        <label for="phone">Phone</label>
        <input type="text" class="form-control" id="phone" name="phone" required>
      </div>
      <div class="form-group">
        <label for="address">Address</label>
        <input type="text" class="form-control" id="address" name="address" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" required>
        <div id="password-error" class="error-message">
          Password must be at least 8 characters, with at least one uppercase, one lowercase, one digit, and one special character (@$!%*?&).
        </div>
      </div>
      <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
        <div id="confirm-password-error" class="error-message">
          Passwords do not match.
        </div>
      </div>
      <button type="submit" class="btn login-btn btn-block">Register</button>
      <div class="mt-2 text-center">
        <span>Already have an account? </span>
        <a href="login.jsp" class="register-link">Login here</a>
      </div>
    </form>
  </div>
</body>
</html>
