document.addEventListener('DOMContentLoaded', function() {
    // Confirmation popup logic
    let currentForm = null;

    document.querySelectorAll('form[action="removeFromCart"]').forEach(form => {
        form.onsubmit = function(e) {
            e.preventDefault();
            currentForm = this;
            document.getElementById('confirm-popup').classList.add('show');
        };
    });

    const cancelBtn = document.getElementById('confirm-popup-cancel');
    const confirmBtn = document.getElementById('confirm-popup-confirm');

    if (cancelBtn) {
        cancelBtn.onclick = function() {
            document.getElementById('confirm-popup').classList.remove('show');
            currentForm = null;
        };
    }

    if (confirmBtn) {
        confirmBtn.onclick = function() {
            if (currentForm) {
                currentForm.submit();
            }
            document.getElementById('confirm-popup').classList.remove('show');
            currentForm = null;
        };
    }

    // Notification auto-hide logic
    const notif = document.getElementById('cart-notification');
    if (notif) {
        notif.style.display = 'block'; // ensure visible
        setTimeout(function() {
            notif.style.display = 'none';
        }, 3000);
    }
});
