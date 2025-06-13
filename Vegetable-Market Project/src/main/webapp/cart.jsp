<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<style>
    body { font-family: Arial, sans-serif; }
    table { width: 80%; margin: 20px auto; border-collapse: collapse; }
    th, td { padding: 10px; text-align: center; border: 1px solid #ccc; }
    th { background-color: #f2f2f2; }
</style>
</head>
<body>
    <h1>Your Cart</h1>
    <table>
        <tr>
            <th>Item ID</th>
            <th>Product ID</th>
            <th>Quantity</th>
            <th>Quantity Per Unit</th>
        </tr>
        <%
            // Retrieve cartItems list from request attribute
            java.util.List cartItems = (java.util.List) request.getAttribute("cartItems");
        	if(cartItems == null)
        		System.out.println("your cart is currently empty");
        	else
        		System.out.println("your cart is not empty");
            if (cartItems != null && !cartItems.isEmpty()) {
                for (Object obj : cartItems) {
                    domain.CartItem item = (domain.CartItem) obj;
        %>
        <tr>
            <td><%= item.getItemId() %></td>
            <td><%= item.getProductId() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getQuantityPerUnit() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">Your cart is empty.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
