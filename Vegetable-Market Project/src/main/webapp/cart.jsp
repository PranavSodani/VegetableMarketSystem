<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="domain.CartItemWithProduct" %>
<%@ include file="component/navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/vegetable-card.css">
<style>
    .back-button {
        display: inline-block;
        margin: 20px auto;
        padding: 10px 25px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        text-decoration: none;
        font-size: 1em;
        cursor: pointer;
        text-align: center;
    }
    .empty-message {
        text-align: center;
        font-style: italic;
        color: #777;
        margin-top: 50px;
        font-size: 1.2em;
    }
    .remove-button {
        background: #ff4d4d;
        color: white;
        border: none;
        border-radius: 4px;
        padding: 8px 16px;
        cursor: pointer;
        margin-top: 10px;
        width: 100%;
        font-weight: bold;
    }
</style>
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
