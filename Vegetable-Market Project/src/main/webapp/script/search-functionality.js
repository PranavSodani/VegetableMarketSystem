document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.getElementById('productSearch');
	    const productCards = document.querySelectorAll('.container-flex .card');
	    const noResultsImage = document.getElementById('noResultsImage');

	    console.log("Number of product cards found:", productCards.length);

	    if (typeof productNames === 'undefined') window.productNames = [];
	    if (typeof synonymToProduct === 'undefined') window.synonymToProduct = {};

    // Levenshtein distance function
    function levenshteinDistance(a, b) {
        const matrix = [];
        for (let i = 0; i <= b.length; i++) matrix[i] = [i];
        for (let j = 0; j <= a.length; j++) matrix[0][j] = j;
        for (let i = 1; i <= b.length; i++) {
            for (let j = 1; j <= a.length; j++) {
                if (b.charAt(i - 1) === a.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    matrix[i][j] = Math.min(
                        matrix[i - 1][j - 1] + 1,
                        matrix[i][j - 1] + 1,
                        matrix[i - 1][j] + 1
                    );
                }
            }
        }
        return matrix[b.length][a.length];
    }

	function updateSearchResults() {
	        console.log("updateSearchResults called");
	        const filter = searchInput.value.trim().toLowerCase();
	        console.log("Filter:", filter);
	        let anyVisible = false;

	        productCards.forEach(card => {
	            const title = card.querySelector('.card-title').textContent.trim().toLowerCase();
	            console.log("Checking card title:", title);

	            const mappedFilter = synonymToProduct[filter] || filter;
	            console.log("Mapped filter:", mappedFilter);

	            const distance = levenshteinDistance(mappedFilter, title);
	            const threshold = 3;

	            if (title.includes(mappedFilter) || distance <= threshold) {
	                card.style.display = '';
	                anyVisible = true;
	            } else {
	                card.style.display = 'none';
	            }
	        });

	        noResultsImage.style.display = (!anyVisible && filter.length > 0) ? 'block' : 'none';
	    }

	    searchInput.addEventListener('input', function() {
	        console.log("Input event fired. Current value:", searchInput.value);
	        updateSearchResults();
	    });

	    updateSearchResults();
	});
