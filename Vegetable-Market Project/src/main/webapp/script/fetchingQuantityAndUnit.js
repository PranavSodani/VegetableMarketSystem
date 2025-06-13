document.addEventListener('DOMContentLoaded', function() {
  // Select all "Add to Cart" buttons
  const buttons = document.querySelectorAll('.card-buy-button');

  buttons.forEach(button => {
    button.addEventListener('click', function(event) {
      event.preventDefault(); // Prevent default anchor navigation

      // Get product ID from data attribute
      const productId = this.getAttribute('data-product-id');

      // Get quantity from the input field for this product
      const quantityInput = document.getElementById('units_' + productId);
      const quantity = quantityInput ? quantityInput.value : 1;

      // Get unit from the select field for this product
      const quantitySelect = document.getElementById('quantity_select_' + productId);
      const unit = quantitySelect ? quantitySelect.value : 100;

      // Build the URL to your servlet with query parameters
      // Note: '${pageContext.request.contextPath}' is available in JSP, but not in JS.
      // You need to either:
      // 1. Set a JS variable in your JSP, like:
      //    <script>const contextPath = "${pageContext.request.contextPath}";</script>
      //    and use it below, or
      // 2. Use a relative path if your app is at the root.

      // Example using a relative path (if your app is at root):
      const url = `addToCart?product_id=${productId}&quantity=${quantity}&quantity_per_unit=${unit}`;

      // Or, if you set a JS variable in your JSP:
      // const url = `${contextPath}/addToCart?product_id=${productId}&quantity=${quantity}&unit=${unit}`;

      // Redirect to the servlet URL
      window.location.href = url;
    });
  });
});
