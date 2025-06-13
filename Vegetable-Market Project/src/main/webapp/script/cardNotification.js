function showCartNotification() {
    const notification = document.getElementById('cart-notification');
    notification.classList.add('show');
    setTimeout(() => {
        notification.classList.remove('show');
    }, 5000); // Hide after 3 seconds
}

// Wait for the DOM to load, then attach click listeners to all add-to-cart buttons
document.addEventListener('DOMContentLoaded', function() {
    const addToCartButtons = document.querySelectorAll('.card-buy-button');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault(); // Prevent default link behavior
            // Here you can add your logic to actually add the product to the cart (e.g., AJAX call)
            showCartNotification();
        });
    });
});
