<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add New Product</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/vendor.css">
</head>

<body>
  <%@ include file="component/navbar.jsp" %>
  <div class="container">
    <h2>Add New Product</h2>
    <form action="${pageContext.request.contextPath}/vendor/products/add" method="post" enctype="multipart/form-data">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" required>

      <label for="description">Description:</label>
      <input type="text" id="description" name="description" required>

      <label for="price">Price:</label>
      <input type="number" id="price" name="price" required min="0" step="0.01">

      <label for="quantity_kg">Quantity (kg):</label>
      <input type="number" id="quantity_kg" name="quantity_kg" required min="0">

      <label for="image">Product Image:</label>
      <input type="file" id="image" name="image" accept="image/*" required>

      <div class="btn-group">
        <input type="submit" value="Add Product">
        <button type="button" class="btn-back" onclick="window.location.href='${pageContext.request.contextPath}/vendor/products/list'">Back to Products</button>
      </div>
    </form>
  </div>
</body>
</html>
