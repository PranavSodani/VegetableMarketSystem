function validateForm() {
	const password = document.getElementById("password").value;
	const confirmPassword = document.getElementById("confirmPassword").value;
	const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	
	// Reset error messages
	document.getElementById("password-error").style.display = "none";
	document.getElementById("confirm-password-error").style.display = "none";
	
	let isValid = true;
	
	if (!pattern.test(password)) {
	  document.getElementById("password-error").style.display = "block";
	  isValid = false;
	}
	if (password !== confirmPassword) {
	  document.getElementById("confirm-password-error").style.display = "block";
	  isValid = false;
	}
    return isValid;
  }
  