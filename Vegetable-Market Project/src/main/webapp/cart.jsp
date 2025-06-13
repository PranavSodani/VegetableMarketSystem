<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="domain.CartItemWithProduct" %>
<%@ include file="component/navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/vegetable-card.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/cart-card.css">
</head>
<body>

    <div style="margin: 20px; text-align: center;">
        <a href="products.jsp" class="back-button">← Back to Products</a>
    </div>

    <h1 style="text-align:center;">Your Cart</h1>

    <%
        java.util.List<CartItemWithProduct> cartItems = (java.util.List<CartItemWithProduct>) request.getAttribute("cartItems");
        if (cartItems != null && !cartItems.isEmpty()) {
    %>
    <div class="container-flex">
        <%
            for (CartItemWithProduct item : cartItems) {
        %>
        <div class="card">
            <img src="<%= item.getImageAddress() %>" alt="<%= item.getProductName() %>" class="card-image">
            <div class="card-content">
                <p class="card-title"><%= item.getProductName() %></p>
                <p class="card-subtitle"><%= item.getProductDescription() %></p>
                <p class="card-price">&#8377;<%= item.getPrice() %></p>
                <p class="card-subtitle">Quantity: <%= item.getQuantity() %></p>
                <p class="card-subtitle">Quantity Per Unit: <%= item.getQuantityPerUnit() %> gm</p>
                <form action="removeFromCart" method="post" style="margin-top: 10px;">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <button type="submit" class="remove-button">Remove</button>
                </form>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <%
        } else {
    %>
    <div class="empty-message">Your cart is empty.</div>
    <%
        }
    %>
</body>
</html>
