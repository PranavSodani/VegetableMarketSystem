<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="allCss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vegetable Market</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-success sticky-top">
		<div class="container">
			<!-- Logo and Brand -->
			<a class="navbar-brand d-flex align-items-center" href="index.jsp">
				<img src="assets/img/market-logo.avif" width="50"
				alt="Vegetable Market" class="mr-2"> <span
				class="text-white font-weight-bold">Veggie Market</span>
			</a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- Navbar links -->
			<div class="collapse navbar-collapse justify-content-center"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link text-white"
						href="index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="${pageContext.request.contextPath}/products">Products</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="${pageContext.request.contextPath}/index.jsp#about">About</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="${pageContext.request.contextPath}/index.jsp#contact">Contact</a></li>
				</ul>
			</div>

			<!-- Dynamic Login/Logout buttons -->
			<%
			Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
			%>
			<div class="ml-auto d-flex">
				<%
				if (loggedIn != null && loggedIn) {
				%>
				<a class="btn btn-danger" href="LogoutServlet">Logout</a>
				<%
				} else {
				%>
				<a class="btn btn-success mr-2" href="login.jsp">Login</a> <a
					class="btn btn-warning" href="register.jsp">Register</a>
				<%
				}
				%>
			</div>

			<%
			Boolean isLogged = (Boolean) session.getAttribute("loggedIn");
			if (isLogged != null && isLogged) {
			%>
			<!-- Cart button (optional) -->
			<div class="ml-2">
				<a class="btn btn-warning text-dark" href="viewCart"> <i class="fas fa-shopping-cart"></i> Cart </a>
			</div>
			<%
			}
			%>


		</div>
	</nav>
</body>
</html>
