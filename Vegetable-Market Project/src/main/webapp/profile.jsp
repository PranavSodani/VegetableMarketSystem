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
    profileImage = "assets/default_profile.png";
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style.css">
</head>
<body>
  <%@ include file="component/navbar.jsp" %>
  <div class="container">
    <h2>Profile</h2>
    <div style="margin-bottom: 20px;">
      <img src="${pageContext.request.contextPath}/<%= profileImage %>" alt="Profile Image" width="100" height="100" style="border-radius: 50%;">
    </div>
    <form action="${pageContext.request.contextPath}/updateProfile" method="post" enctype="multipart/form-data">
      <label for="profileImage">Update Profile Image:</label>
      <input type="file" id="profileImage" name="profileImage" accept="image/*">
      <input type="submit" value="Update Image">
    </form>
  </div>
</body>
</html>
