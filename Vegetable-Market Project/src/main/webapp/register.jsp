<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/component/allCss.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style.css">
</head>
<body>
  <div class="login-container">
    <h2 class="login-header">
      <img src="assets/img/market-logo.avif" width="50" class="mr-2" alt="Market Logo">
      Register
    </h2>
    <form action="RegisterServlet" method="post">
      <div class="form-group">
        <label for="name">Full Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Your full name" required>
      </div>
      <div class="form-group">
        <label for="email">Email Address</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="example@domain.com" required>
      </div>
      <div class="form-group">
        <label for="phone">Phone Number</label>
        <input type="tel" class="form-control" id="phone" name="phone" placeholder="+1 234 567 8900" required>
      </div>
      <div class="form-group">
        <label for="address">Address</label>
        <textarea class="form-control" id="address" name="address" rows="3" placeholder="Your delivery address" required></textarea>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Create a password" required>
      </div>
      <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required>
      </div>
      <button type="submit" class="btn login-btn btn-block">Register</button>
      <div class="mt-3 text-center">
        <span>Already have an account? </span>
        <a href="login.jsp" class="register-link">Login here</a>
      </div>
    </form>
  </div>
</body>
</html>
