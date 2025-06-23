function showCartNotification() {
    const notification = document.getElementById('cart-notification');
    notification.classList.add('show');
    setTimeout(() => {
        notification.classList.remove('show');
    }, 5000); 
}

document.addEventListener('DOMContentLoaded', function() {
    const addToCartButtons = document.querySelectorAll('.card-buy-button');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault(); 
            showCartNotification();
        });
    });
});
