<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="component/allCss.jsp" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Veggie Market - Home</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/component/style2.css">
  
</head>
<body>
  <%@ include file="component/navbar.jsp" %>

  <!-- Hero Section with Image -->
  <section class="hero">
    <div class="container hero-content">
      <h1>Welcome to Veggie Market</h1>
      <p>Discover fresh, organic vegetables delivered to your doorstep. Enjoy farm-to-table quality with every order.</p>
      <img src="${pageContext.request.contextPath}/assets/img/vegetable-image1.webp" alt="Fresh Vegetables" class="hero-image">
      <p>Shop our selection of seasonal produce, handpicked for quality and freshness.</p>
      <div>
        <a href="products.jsp" class="btn">Shop Now</a>
        <a href="#about" class="btn" style="background: rgba(255,255,255,0.2); color: white; border: 2px solid white;">Learn More</a>
      </div>
    </div>
  </section>

  <!-- Products Section -->
  <section id="products" class="section">
    <h2>Our Products</h2>
    <div class="container" style="display: flex; flex-wrap: wrap; justify-content: center; align-items: center; gap: 40px;">
      <div class="section-content">
        <p>Explore our wide range of organic vegetables and fruits, handpicked for quality and freshness. From crisp lettuce and juicy tomatoes to exotic seasonal offerings, we ensure every item meets our high standards. Our team works closely with trusted local farmers to bring you the best selection year-round.</p>
      </div>
      <img src="${pageContext.request.contextPath}/assets/img/products.png" alt="Our Products" class="section-image">
    </div>
  </section>

  <!-- About Section -->
  <section id="about" class="section">
    <h2>About Us</h2>
    <div class="container" style="display: flex; flex-wrap: wrap; justify-content: center; align-items: center; gap: 40px;">
      <img src="${pageContext.request.contextPath}/assets/img/about-us.jfif" alt="About Us" class="section-image">
      <div class="section-content">
        <p>Veggie Market is committed to providing the best quality organic produce, sourced directly from local farmers. We believe in sustainable farming and supporting our community. Our mission is to connect you with fresh, healthy food while fostering relationships with the people who grow it. Every purchase supports local agriculture and helps build a healthier planet.</p>
      </div>
    </div>
  </section>

  <!-- Contact Section -->
  <section id="contact" class="section">
    <h2>Contact Us</h2>
    <div class="container" style="display: flex; flex-wrap: wrap; justify-content: center; align-items: center; gap: 40px;">
      <div class="section-content">
        <p>Have questions? Contact us at support@veggiemarket.com or visit our contact page. We're here to help! Our friendly customer service team is available to answer your questions, take your feedback, and ensure your shopping experience is smooth and enjoyable. Connect with us today and let us know how we can serve you better.</p>
      </div>
      <img src="${pageContext.request.contextPath}/assets/img/contact-us.avif" alt="Contact Us" class="section-image">
    </div> 	
  </section>

  <!-- Back to Top Button -->
  <a href="#" class="back-to-top">↑</a>
</body>
</html>
