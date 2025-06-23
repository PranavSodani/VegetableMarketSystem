<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="domain.CartItemWithProduct"%>
<%@ include file="component/navbar.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/component/vegetable-card.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/component/cart-card.css">
<script
	src="${pageContext.request.contextPath}/script/removeNotification.js"></script>
</head>
<body>

	<!-- Custom confirmation popup -->
	<div id="confirm-popup" class="confirm-popup">
		<div class="confirm-popup-content">
			<p>Are you sure you want to remove this item from your cart?</p>
			<div class="confirm-popup-buttons">
				<button id="confirm-popup-cancel"
					class="confirm-popup-button cancel">Cancel</button>
				<button id="confirm-popup-confirm"
					class="confirm-popup-button confirm">Remove</button>
			</div>
		</div>
	</div>

	<%-- Notification for remove/add/update actions --%>
	<%
	String notification = (String) session.getAttribute("notification");
	if (notification != null) {
	%>
	<div id="cart-notification" class="cart-notification show">
		<%=notification%>
	</div>
	<%
	session.removeAttribute("notification");
	}
	%>

	<h1 style="text-align: center;">Your Cart</h1>

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
			<img src="<%=item.getImageAddress()%>"
				alt="<%=item.getProductName()%>" class="card-image">
			<div class="card-content">
				<p class="card-title"><%=item.getProductName()%></p>
				<p class="card-subtitle"><%=item.getProductDescription()%></p>
				<p class="card-price">
					&#8377;<%=item.getTotalPrice()%></p>
				<p class="card-subtitle">
					Quantity:
					<%=quantity%></p>
				<p class="card-subtitle">
					Quantity Per Unit:
					<%=item.getQuantityPerUnit()%>
					gm
				</p>

				<div class="button-group">
					<%
					if (quantity > 1) {
					%>
					<!-- Reduce Quantity Form -->
					<!-- Update Quantity Form -->
					<form action="updateCartQuantity" method="post"
						class="update-quantity-form">
						<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
						<input type="number" name="quantity"
							value="<%=item.getQuantity()%>" min="1" max="999"
							class="quantity-input">
						<button type="submit" class="update-button">Update</button>
					</form>

					<%
					}
					%>

					<!-- Remove Button Form (always shown) -->
					<form action="removeFromCart" method="post">
						<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
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
	<div class="empty-cart-container"
		style="text-align: center; margin-top: 60px;">
		<img
			src="${pageContext.request.contextPath}/assets/img/cart-is-empty.png"
			alt="Cart is empty"
			style="max-width: 300px; width: 100%; height: auto; margin-bottom: 20px;" />
		<p class="empty-cart-message"
			style="font-size: 1.25rem; color: #555; font-style: italic;">Your
			cart is empty.</p>
	</div>
	<%
	}
	%>

	<div style="margin: 20px; text-align: center;">
		<a href="${pageContext.request.contextPath}/products"
			class="back-button">← Back to Products</a>
	</div>

	<div style="text-align: center; margin: 20px;">
		<button id="show-summary-btn" class="btn"
			style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer;">
			Show Summary</button>
	</div>

	<!-- Order Summary Modal -->
	<div id="summary-modal" class="modal"
		style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5); justify-content: center; align-items: center; z-index: 3000;">
		<div
			style="background: #fff; padding: 20px; border-radius: 8px; max-width: 500px; width: 90%; max-height: 80vh; overflow-y: auto;">
			<h2>Order Summary</h2>
			<ul style="list-style: none; padding: 0;">
				<%-- Loop through cart items and display name & quantity --%>
				<%
				if (cartItems != null) {
					for (CartItemWithProduct item : cartItems) {
				%>
				<li style="margin-bottom: 10px;"><strong><%=item.getProductName()%></strong>
					— Quantity: <%=item.getQuantity()%> — Price: &#8377;<%=item.getTotalPrice()%>
				</li>
				<%
				}
				}
				%>
			</ul>

			<%-- Calculate totals --%>
			<%
			double subtotal = 0;
			if (cartItems != null) {
				for (CartItemWithProduct item : cartItems) {
					subtotal += item.getTotalPrice();
				}
			}
			double taxRate = 0.05; // 5% tax example
			double taxAmount = subtotal * taxRate;
			double grandTotal = subtotal + taxAmount;
			%>

			<p>
				<strong>Subtotal:</strong> &#8377;
				<%=String.format("%.2f", subtotal)%></p>
			<p>
				<strong>Tax (5%):</strong> &#8377;
				<%=String.format("%.2f", taxAmount)%></p>
			<hr>
			<p style="font-weight: bold;">
				<strong>Grand Total:</strong> &#8377;
				<%=String.format("%.2f", grandTotal)%></p>

			<div style="margin-top: 20px; text-align: center;">
				<a href="billing.jsp" class="btn"
					style="padding: 10px 20px; background: #007bff; color: #fff; border: none; border-radius: 5px; cursor: pointer; text-decoration: none;">
					Proceed to Billing </a>
				<button id="close-summary-btn"
					style="padding: 10px 20px; margin-left: 10px; border: none; border-radius: 5px; cursor: pointer;">
					Cancel</button>
			</div>
		</div>
	</div>

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const showBtn = document.getElementById('show-summary-btn');
			const modal = document.getElementById('summary-modal');
			const closeBtn = document.getElementById('close-summary-btn');

			showBtn.addEventListener('click', function() {
				modal.style.display = 'flex';
			});

			closeBtn.addEventListener('click', function() {
				modal.style.display = 'none';
			});

			// Optional: close modal if clicking outside content
			modal.addEventListener('click', function(e) {
				if (e.target === modal) {
					modal.style.display = 'none';
				}
			});
		});
	</script>

</body>
</html>
