document.addEventListener('DOMContentLoaded', function() {
  const buttons = document.querySelectorAll('.card-buy-button');

  buttons.forEach(button => {
    button.addEventListener('click', function(event) {
      event.preventDefault(); 
      const productId = this.getAttribute('data-product-id');
      const quantityInput = document.getElementById('units_' + productId);
      const quantity = quantityInput ? quantityInput.value : 1;
      const quantitySelect = document.getElementById('quantity_select_' + productId);
      const unit = quantitySelect ? quantitySelect.value : 100;
      const url = `addToCart?product_id=${productId}&quantity=${quantity}&quantity_per_unit=${unit}`;
      window.location.href = url;
    });
  });
});
