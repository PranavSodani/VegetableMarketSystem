<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/component/allCss.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.ProductVegie" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>My Products</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style.css">
</head>
<body>
  <%@ include file="component/navbar.jsp" %>
  <div class="container">
    <h2>My Products</h2>
    <a href="new" class="btn">Add New Product</a>
    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Description</th>
          <th>Price</th>
          <th>Quantity (kg)</th>
          <th>Image</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <%
          List<domain.ProductVegie> products = (List<domain.ProductVegie>) request.getAttribute("products");
          if (products != null) {
            for (domain.ProductVegie product : products) {
        %>
          <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getQuantity_kg() %></td>
            <td><img src="assets/img/market-logo.avif" width="50"></td>
            <td>
              <a href="edit?id=<%= product.getId() %>">Edit</a>
              <a href="delete?id=<%= product.getId() %>">Delete</a>
            </td>
          </tr>
        <%
            }
          } else {
        %>
          <tr><td colspan="7">No products found.</td></tr>
        <%
          }
        %>
      </tbody>
    </table>
  </div>
</body>
</html>
