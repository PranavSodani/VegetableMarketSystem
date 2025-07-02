<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.ProductVegie"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<%
    // Defensive: Always initialize variables to avoid nulls in JSP/JS
    List<String> productNames = (List<String>) request.getAttribute("productNames");
    if (productNames == null) productNames = new java.util.ArrayList<>();

    Map<String, String> synonymToProduct = (Map<String, String>) request.getAttribute("synonymToProduct");
    if (synonymToProduct == null) synonymToProduct = new java.util.HashMap<>();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Card</title>

<!-- Output productNames and synonymToProduct as JS variables -->
<script>
const productNames = [
    <% for (int i = 0; i < productNames.size(); i++) { %>
        "<%= productNames.get(i).replace("\"", "\\\"") %>"<%= (i < productNames.size() - 1) ? "," : "" %>
    <% } %>
];

const synonymToProduct = {
    <% 
    int count = 0;
    int size = synonymToProduct.size();
    for (Map.Entry<String, String> entry : synonymToProduct.entrySet()) { %>
        "<%= entry.getKey().replace("\"", "\\\"") %>": "<%= entry.getValue().replace("\"", "\\\"") %>"<%= (++count < size) ? "," : "" %>
    <% } %>
};
console.log("productNames:", productNames);
console.log("synonymToProduct:", synonymToProduct);
</script>

<script src="${pageContext.request.contextPath}/script/product-card.js"></script>
<script src="${pageContext.request.contextPath}/script/search-functionality.js"></script>
<script src="${pageContext.request.contextPath}/script/fetchingQuantityAndUnit.js"></script>
<script src="${pageContext.request.contextPath}/script/cardNotification.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/component/vegetable-card.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/component/cart-card.css">
</head>
<body>
    <%@ include file="component/navbar.jsp"%>

    <div class="search-container">
        <input type="text" id="productSearch"
            placeholder="Search products by name..."
            aria-label="Search products by name" />
    </div>

    <div class="container-flex">
        <%
        List<ProductVegie> list = (List<ProductVegie>) request.getAttribute("products");
        if (list == null) {
            list = (List<ProductVegie>) session.getAttribute("products");
        }
        if (list != null && list.size() > 0) {
            for (ProductVegie product : list) {
        %>
        <div class="card" data-base-price="<%=product.getPrice()%>">
            <img src="<%=product.getImage_address()%>"
                alt="<%=product.getName()%>" class="card-image" />
            <div class="card-content">
                <p class="card-title"><%=product.getName()%></p>
                <p class="card-subtitle"><%=product.getDescription()%></p>
                <p class="card-price">
                    &#8377;<%=product.getPrice()%></p>
                <label for="units_<%=product.getId()%>">Number of Units:</label> <input
                    type="number" id="units_<%=product.getId()%>" name="units"
                    class="card-units" min="1" value="1"
                    aria-label="Enter number of units" /> <label
                    for="quantity_select_<%=product.getId()%>">Quantity per
                    Unit:</label> <select id="quantity_select_<%=product.getId()%>"
                    name="quantity_per_unit" class="card-quantity-select"
                    aria-label="Select quantity per unit">
                    <option value="100">100gm</option>
                    <option value="250">250gm</option>
                    <option value="500">500gm</option>
                    <option value="1000">1000gm</option>
                </select>

                <button type="button" class="card-buy-button"
                    data-product-id="<%=product.getId()%>">Add to Cart</button>
            </div>
        </div>
        <%
        }
        } else {
        %>
        <p>No products available.</p>
        <%
        }
        %>
    </div>
    <div id="noResultsImage"
        style="display: none; text-align: center; margin: 40px auto; max-width: 400px;">
        <img
            src="${pageContext.request.contextPath}/assets/img/search-not-found.avif"
            alt="No results found" style="width: 100%; max-width: 300px;">
        <p style="color: #555; font-style: italic; margin-top: 10px;">No
            products found matching your search.</p>
    </div>
    <div id="cart-notification" class="cart-notification">
        <span>Your item has been added to the cart successfully!</span>
    </div>
</body>
</html>
