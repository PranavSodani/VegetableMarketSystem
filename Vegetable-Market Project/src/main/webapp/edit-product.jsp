<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="domain.ProductVegie"%>
<%
ProductVegie product = (ProductVegie) request.getAttribute("product");
if (product == null) {
	response.sendRedirect("vendor/products/list");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Product</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/edit-product.css">
</head>
<body>
	<%@ include file="component/navbar.jsp"%>
	<div class="container">
		<h2>Edit Product</h2>
		<form action="${pageContext.request.contextPath}/vendor/products/edit"
			method="post">
			<input type="hidden" name="id" value="<%=product.getId()%>" /> <label
				for="name">Name:</label> <input type="text" id="name" name="name"
				value="<%=product.getName()%>" required> <label
				for="description">Description:</label> <input type="text"
				id="description" name="description"
				value="<%=product.getDescription()%>" required> <label
				for="price">Price:</label> <input type="number" id="price"
				name="price" value="<%=product.getPrice()%>" required min="0"
				step="0.01"> <label for="quantity_kg">Quantity (kg):</label>
			<input type="number" id="quantity_kg" name="quantity_kg"
				value="<%=product.getQuantity_kg()%>" required min="0"> <label
				for="image_address">Image Address:</label> <input type="text"
				id="image_address" name="image_address"
				value="<%=product.getImage_address()%>" required>

			<div class="btn-group">
				<input type="submit" value="Update Product">
				<button type="button"
					onclick="window.location.href='${pageContext.request.contextPath}/vendor/products/list'">Cancel</button>
			</div>

		</form>
	</div>
</body>
</html>
