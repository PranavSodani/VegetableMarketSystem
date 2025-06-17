<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="domain.CartItemWithProduct" %>
<%@ include file="component/navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/vegetable-card.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/cart-card.css">
<script src="${pageContext.request.contextPath}/script/removeNotification.js"></script>
</head>
<body>

<!-- Custom confirmation popup -->
<div id="confirm-popup" class="confirm-popup">
    <div class="confirm-popup-content">
        <p>Are you sure you want to remove this item from your cart?</p>
        <div class="confirm-popup-buttons">
            <button id="confirm-popup-cancel" class="confirm-popup-button cancel">Cancel</button>
            <button id="confirm-popup-confirm" class="confirm-popup-button confirm">Remove</button>
        </div>
    </div>
</div>

<%-- Notification for remove/add/update actions --%>
<%
    String notification = (String) session.getAttribute("notification");
    if (notification != null) {
%>
    <div id="cart-notification" class="cart-notification show">
        <%= notification %>
    </div>
<%
        session.removeAttribute("notification");
    }
%>


<h1 style="text-align:center;">Your Cart</h1>

<%
    java.util.List<CartItemWithProduct> cartItems = (java.util.List<CartItemWithProduct>) request.getAttribute("cartItems");
    if (cartItems != null && !cartItems.isEmpty()) {
%>
<div class="container-flex">
    <%
        for (CartItemWithProduct item : cartItems) {
            int quantity = item.getQuantity();
    %>
    <div class="card">
        <img src="<%= item.getImageAddress() %>" alt="<%= item.getProductName() %>" class="card-image">
        <div class="card-content">
            <p class="card-title"><%= item.getProductName() %></p>
            <p class="card-subtitle"><%= item.getProductDescription() %></p>
            <p class="card-price">&#8377;<%= item.getTotalPrice() %></p>
            <p class="card-subtitle">Quantity: <%= quantity %></p>
            <p class="card-subtitle">Quantity Per Unit: <%= item.getQuantityPerUnit() %> gm</p>

            <div class="button-group">
                <%
                    if (quantity > 1) {
                %>
                <!-- Reduce Quantity Form -->
                <form action="updateCartQuantity" method="post">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <input type="hidden" name="action" value="decrease">
                    <button type="submit" class="reduce-button">Reduce Quantity</button>
                </form>
                <%
                    }
                %>

                <!-- Remove Button Form (always shown) -->
                <form action="removeFromCart" method="post">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <button type="submit" class="remove-button">Remove</button>
                </form>
            </div>
        </div>
    </div>
    <% 
        }
    %>
</div>
<%
    } else {
%>
<div class="empty-cart-container" style="text-align:center; margin-top:60px;">
    <img src="${pageContext.request.contextPath}/assets/img/cart-is-empty.png" alt="Cart is empty" style="max-width:300px; width:100%; height:auto; margin-bottom:20px;"/>
    <p class="empty-cart-message" style="font-size:1.25rem; color:#555; font-style:italic;">Your cart is empty.</p>
</div>
<%
    }
%>
<div style="margin: 20px; text-align: center;">
    <a href="${pageContext.request.contextPath}/products" class="back-button">← Back to Products</a>
</div>


</body>
</html>
