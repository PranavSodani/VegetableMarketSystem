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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/vendor.css">
  <style>
    .btn-add {
      margin-top: 20px;
      margin-bottom: 20px;
      background-color: #6c8cff;
      padding: 12px 24px;
      color: white;
      font-weight: bold;
      border-radius: 8px;
      text-decoration: none;
      box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      transition: background-color 0.3s ease;
    }
    .btn-add:hover {
      background-color: #8da6ff;
      color: white;
      transform: translateY(-2px);
      box-shadow: 0 4px 8px rgba(0,0,0,0.15);
    }
    .table {
      width: 100%;
      border-collapse: collapse;
    }
    .table th, .table td {
      padding: 12px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }
    .table th {
      background-color: #f2f2f2;
    }
    .table tr:hover {
      background-color: #f9f9f9;
    }
  </style>
</head>
<body>
  <%@ include file="component/navbar.jsp" %>
  <div class="container">
    <h2>My Products</h2>
    <a href="new" class="btn btn-add">Add New Product</a>
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
          List<ProductVegie> products = (List<ProductVegie>) request.getAttribute("products");
          if (products != null && !products.isEmpty()) {
            for (ProductVegie product : products) {
        %>
          <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getQuantity_kg() %></td>
            <td><img src="${pageContext.request.contextPath}/<%= product.getImage_address() %>" width="50"></td>
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
