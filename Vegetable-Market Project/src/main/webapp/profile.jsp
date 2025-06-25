<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="domain.User" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
  }
  String profileImage = user.getProfileImage();
  if (profileImage == null || profileImage.isEmpty()) {
    profileImage = "assets/img/default-profile-image.webp";
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/profile.css">
</head>
<body>
  <%@ include file="component/navbar.jsp" %>

  <div class="back-btn-container">
    <button class="back-btn" onclick="history.back()">&larr; Back</button>
  </div>

  <div class="profile-section">
    <div class="profile-header">
      <h2>Profile</h2>
    </div>
    <div class="profile-image-wrapper">
      <img src="${pageContext.request.contextPath}/<%= profileImage %>" alt="Profile Image">
    </div>
    <div class="user-details">
      <p><strong>Name:</strong> <%= user.getName() != null ? user.getName() : "Not provided" %></p>
      <p><strong>Email:</strong> <%= user.getEmail() != null ? user.getEmail() : "Not provided" %></p>
      <p><strong>Address:</strong> <%= user.getAddress() != null ? user.getAddress() : "Not provided" %></p>
    </div>
    <div class="profile-form">
      <form action="${pageContext.request.contextPath}/updateProfile" method="post" enctype="multipart/form-data">
        <label for="profileImage">Update Profile Image</label>
        <input type="file" id="profileImage" name="profileImage" accept="image/*">
        <input type="submit" value="Update Image">
      </form>
    </div>
  </div>
</body>
</html>
