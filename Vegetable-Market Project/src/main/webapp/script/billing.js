document.addEventListener('DOMContentLoaded', function() {
  const showBtn = document.getElementById('show-summary-btn');
  const modal = document.getElementById('summary-modal');
  const closeBtn = document.getElementById('close-summary-btn');

  showBtn.addEventListener('click', function() {
    modal.style.display = 'flex';
  });

  closeBtn.addEventListener('click', function() {
    modal.style.display = 'none';
  });

  // Optional: close modal if clicking outside content
  modal.addEventListener('click', function(e) {
    if (e.target === modal) {
      modal.style.display = 'none';
    }
  });
});