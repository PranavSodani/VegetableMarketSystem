<%@ include file="allCss.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vegetable Market</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/notification.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/nav-css.css">
</head>
<body>
	<%
	Integer cartItemCount = (Integer) session.getAttribute("cartItemCount");
	if (cartItemCount == null)
		cartItemCount = 0;
	String userType = (String) session.getAttribute("userType");
	Boolean isVendor = userType != null && "vendor".equalsIgnoreCase(userType);
	Boolean isLogged = (Boolean) session.getAttribute("loggedIn");

	// Dynamically set Products URL based on userType
	String productsUrl = request.getContextPath() + "/products";
	if (isVendor != null && isVendor) {
	    productsUrl = request.getContextPath() + "/vendor/products/list";
	}
	%>
	<nav class="navbar navbar-expand-lg navbar-light bg-success sticky-top">
		<div class="container">
			<!-- Logo and Brand -->
			<a class="navbar-brand d-flex align-items-center" href="index.jsp">
				<img src="<%=request.getContextPath()%>/assets/img/market-logo.avif" width="50" alt="Vegetable Market" class="mr-2 rounded">
				<span class="text-white">Veggie Market</span>
			</a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- Navbar links -->
			<div class="collapse navbar-collapse justify-content-center" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
					<li class="nav-item">
						<a class="nav-link" href="<%=productsUrl%>">Products</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/index.jsp#about">About</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/index.jsp#contact">Contact</a></li>
				</ul>
			</div>

			<!-- Right aligned buttons -->
			<div class="ml-auto d-flex align-items-center">
				<%
				if (isLogged != null && isLogged) {
				%>
				<a class="btn btn-primary mr-2" href="${pageContext.request.contextPath}/profile.jsp">Profile</a>
				<a class="btn btn-danger mr-2" href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
				<%
				if (!isVendor) {
				%>
				<a class="btn btn-warning text-dark position-relative" href="viewCart">
					<i class="fas fa-shopping-cart"></i>
					<span class="badge badge-danger cart-badge"><%=cartItemCount%></span>
				</a>
				<%
				}
				} else {
				%>
				<a class="btn btn-success mr-2" href="login.jsp">Login</a>
				<a class="btn btn-warning" href="register.jsp">Register</a>
				<%
				}
				%>
			</div>
		</div>
	</nav>

  <!-- Include Bootstrap JS and dependencies if not already included -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
