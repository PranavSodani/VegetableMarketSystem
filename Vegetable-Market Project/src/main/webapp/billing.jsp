<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="component/navbar.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Billing Information</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style.css">
  <style>
    .back-button {
      display: inline-block;
      margin-top: 20px;
      padding: 10px 25px;
      background-color: #6c757d;
      color: white;
      border: none;
      border-radius: 5px;
      text-decoration: none;
      font-size: 1em;
      cursor: pointer;
      text-align: center;
    }
    .back-button:hover {
      background-color: #5a6268;
      text-decoration: none;
      color: white;
    }
  </style>
</head>
<body>
  <div style="max-width: 600px; margin: 40px auto;">
    <h2>Billing Information</h2>
    <form action="PlaceOrderServlet" method="post">
      <div class="form-group">
        <label for="fullName">Full Name</label>
        <input type="text" id="fullName" name="fullName" required class="form-control">
      </div>
      <div class="form-group">
        <label for="address">Shipping Address</label>
        <textarea id="address" name="address" required class="form-control"></textarea>
      </div>
      <div class="form-group">
        <label for="paymentMethod">Payment Method</label>
        <select id="paymentMethod" name="paymentMethod" required class="form-control">
          <option value="">Select</option>
          <option value="creditCard">Credit Card</option>
          <option value="debitCard">Debit Card</option>
          <option value="cod">Cash on Delivery</option>
        </select>
      </div>
      <!-- Add more fields as needed -->
      <button type="submit" class="btn btn-primary">Place Order</button>
    </form>

    <!-- Back to Cart Button -->
    <a href="${pageContext.request.contextPath}/viewCart" class="back-button">← Back to Cart</a>

  </div>
</body>
</html>
