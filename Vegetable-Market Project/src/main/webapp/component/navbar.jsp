<%@ include file="allCss.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vegetable Market</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/notification.css">

<style>
  /* Navbar background with subtle gradient */
  .navbar.bg-success {
    background: linear-gradient(90deg, #2ecc71, #27ae60);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  }

  /* Brand styling */
  .navbar-brand {
    font-weight: 700;
    font-size: 1.5rem;
    color: #fff !important;
  }
  .navbar-brand img {
    border-radius: 8px;
    margin-right: 10px;
  }

  /* Navbar links */
  .navbar-nav .nav-link {
    color: #e0f7e9 !important;
    font-weight: 600;
    padding: 0.5rem 1rem;
    transition: color 0.3s ease;
  }
  .navbar-nav .nav-link:hover,
  .navbar-nav .nav-link:focus {
    color: #ffffff !important;
    text-shadow: 0 0 5px rgba(255, 255, 255, 0.7);
  }

  /* Right aligned buttons container */
  .ml-auto.d-flex.align-items-center a.btn {
    border-radius: 25px;
    font-weight: 600;
    padding: 8px 20px;
    box-shadow: 0 2px 6px rgba(0,0,0,0.15);
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
  }

  /* Profile button */
  .btn-primary {
    background-color: #3498db;
    border-color: #2980b9;
    color: #fff;
  }
  .btn-primary:hover {
    background-color: #2980b9;
    border-color: #1c5980;
    box-shadow: 0 4px 12px rgba(41, 128, 185, 0.6);
  }

  /* Logout button */
  .btn-danger {
    background-color: #e74c3c;
    border-color: #c0392b;
    color: #fff;
  }
  .btn-danger:hover {
    background-color: #c0392b;
    border-color: #922b21;
    box-shadow: 0 4px 12px rgba(192, 57, 43, 0.6);
  }

  /* Login button */
  .btn-success {
    background-color: #2ecc71;
    border-color: #27ae60;
    color: #fff;
  }
  .btn-success:hover {
    background-color: #27ae60;
    border-color: #1e8449;
    box-shadow: 0 4px 12px rgba(39, 174, 96, 0.6);
  }

  /* Register button */
  .btn-warning {
    background-color: #f39c12;
    border-color: #d68910;
    color: #fff;
  }
  .btn-warning:hover {
    background-color: #d68910;
    border-color: #b9770e;
    box-shadow: 0 4px 12px rgba(214, 137, 16, 0.6);
  }

  /* Cart button */
  .btn-warning.text-dark {
    color: #2c3e50 !important;
    font-weight: 700;
    position: relative;
  }
  .btn-warning.text-dark:hover {
    background-color: #d68910;
    color: #fff !important;
  }

  /* Cart badge */
  .cart-badge {
    position: absolute;
    top: -6px;
    right: -10px;
    font-size: 0.75rem;
    padding: 3px 6px;
    border-radius: 50%;
    background-color: #e74c3c;
    color: white;
    font-weight: 700;
    box-shadow: 0 0 4px rgba(0,0,0,0.3);
  }

  /* Navbar toggler */
  .navbar-toggler {
    border: none;
    outline: none;
  }
  .navbar-toggler-icon {
    background-image: url("data:image/svg+xml;charset=utf8,%3Csvg viewBox='0 0 30 30' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath stroke='rgba%28255, 255, 255, 0.85%29' stroke-width='2' stroke-linecap='round' stroke-miterlimit='10' d='M4 7h22M4 15h22M4 23h22'/%3E%3C/svg%3E");
  }
</style>

</head>
<body>
	<%
	Integer cartItemCount = (Integer) session.getAttribute("cartItemCount");
	if (cartItemCount == null)
		cartItemCount = 0;
	String userType = (String) session.getAttribute("userType");
	Boolean isVendor = userType != null && "vendor".equalsIgnoreCase(userType);
	Boolean isLogged = (Boolean) session.getAttribute("loggedIn");
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
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/products">Products</a></li>
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
