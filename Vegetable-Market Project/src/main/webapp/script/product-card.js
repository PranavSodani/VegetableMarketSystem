function updatePrice(card) {
    var units = parseInt(card.querySelector('.card-units').value) || 1;
    var quantityPerUnit = parseInt(card.querySelector('.card-quantity-select').value) || 100;
    var basePrice = parseFloat(card.getAttribute('data-base-price'));
    var totalQuantity = units * quantityPerUnit;
    var price = basePrice * (totalQuantity / 100);
    card.querySelector('.card-price').textContent = '₹' + price.toFixed(2);
}

window.onload = function() {
    document.querySelectorAll('.card').forEach(function(card) {
        card.querySelector('.card-units').addEventListener('input', function() { updatePrice(card); });
        card.querySelector('.card-quantity-select').addEventListener('change', function() { updatePrice(card); });
        updatePrice(card);
    });
};
