<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add New Product</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style.css">
</head>
<body>
  <%@ include file="component/navbar.jsp" %>
  <div class="container">
    <h2>Add New Product</h2>
    <form action="${pageContext.request.contextPath}/vendor/products/add" method="post">
      Name: <input type="text" name="name" required><br>
      Description: <input type="text" name="description" required><br>
      Price: <input type="number" name="price" required><br>
      Quantity (kg): <input type="number" name="quantity_kg" required><br>
      Image Address: <input type="text" name="image_address" required><br>
      <input type="submit" value="Add Product">
    </form>
  </div>
</body>
</html>
