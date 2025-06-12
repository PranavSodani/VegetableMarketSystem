document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('productSearch');
    const productCards = document.querySelectorAll('.container-flex .card');
    const noResultsImage = document.getElementById('noResultsImage');

    function updateSearchResults() {
        const filter = searchInput.value.toLowerCase();
        let anyVisible = false;

        productCards.forEach(card => {
            const title = card.querySelector('.card-title').textContent.toLowerCase();
            if (title.includes(filter)) {
                card.style.display = '';
                anyVisible = true;
            } else {
                card.style.display = 'none';
            }
        });

        // Show or hide the "no results" image
        if (!anyVisible && filter.length > 0) {
            noResultsImage.style.display = 'block';
        } else {
            noResultsImage.style.display = 'none';
        }
    }

    searchInput.addEventListener('input', updateSearchResults);
});
