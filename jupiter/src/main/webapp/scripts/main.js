(function() {
	/**
	 * Variables
	 */
	var user_id = '1111';
	var user_fullname = 'John';
	var lng = -122.08;
	var lat = 37.38;
	
	function init() {
		document.querySelector('#login-form-btn').addEventListener('click',
				onSessionInvalid);
		document.querySelector('#register-form-btn').addEventListener('click',
				showRegisterForm);
		document.querySelector('#register-btn').addEventListener('click',
				register);


		validateSession();
	}
	
	function register() {
		var username = document.querySelector('#register-username').value;
		var password = document.querySelector('#register-password').value;
		var firstName = document.querySelector('#register-first-name').value;
		var lastName = document.querySelector('#register-last-name').value;

		if (username === "" || password == "" || firstName === ""
				|| lastName === "") {
			showRegisterResult('Please fill in all fields');
			return;
		}

		if (username.match(/^[a-z0-9_]+$/) === null) {
			showRegisterResult('Invalid username');
			return;
		}
		password = md5(username + md5(password));

		// The request parameters
		var url = './register';
		var req = JSON.stringify({
			user_id : username,
			password : password,
			first_name : firstName,
			last_name : lastName,
		});

		ajax('POST', url, req,
		// successful callback
		function(res) {
			var result = JSON.parse(res);
			// successfully logged in
			if (result.status === 'OK') {
				showRegisterResult('Succesfully registered');
			} else {
				showRegisterResult('User already existed');
			}
		},
		// error
		function() {
			showRegisterResult('Failed to register');
		});
	}

	function showRegisterResult(registerMessage) {
		document.querySelector('#register-result').innerHTML = registerMessage;
	}

	function clearRegisterResult() {
		document.querySelector('#register-result').innerHTML = '';
	}



		function validateSession() {
			onSessionInvalid();
			// The request parameters
			var url = './login';
			var req = JSON.stringify({});

			// display loading message
			showLoadingMessage('Validating session...');

			// make AJAX call
			ajax('GET', url, req,
			// session is still valid
			function(res) {
				var result = JSON.parse(res);
				if (result.status === 'OK') {
					onSessionValid(result);
				}
			}, function() {
				console.log('login error')
			});
		}

	function onSessionInvalid() {
		var loginForm = document.querySelector('#login-form');
		var registerForm = document.querySelector('#register-form');
		var itemNav = document.querySelector('#item-nav');
		var itemList = document.querySelector('#item-list');
		var avatar = document.querySelector('#avatar');
		var welcomeMsg = document.querySelector('#welcome-msg');
		var logoutBtn = document.querySelector('#logout-link');
		hideElement(itemNav);
		hideElement(itemList);
		hideElement(avatar);
		hideElement(logoutBtn);
		hideElement(welcomeMsg);
		hideElement(registerForm);
		clearLoginError();
		showElement(loginForm);
	}

		function hideElement(element) {
			element.style.display = 'none';
		}
		
		function showRegisterForm() {
			var loginForm = document.querySelector('#login-form');
			var registerForm = document.querySelector('#register-form');
			var itemNav = document.querySelector('#item-nav');
			var itemList = document.querySelector('#item-list');
			var avatar = document.querySelector('#avatar');
			var welcomeMsg = document.querySelector('#welcome-msg');
			var logoutBtn = document.querySelector('#logout-link');

			hideElement(itemNav);
			hideElement(itemList);
			hideElement(avatar);
			hideElement(logoutBtn);
			hideElement(welcomeMsg);
			hideElement(loginForm);

			clearRegisterResult();
			showElement(registerForm);
		}
		function hideElement(element) {
			element.style.display = 'none';
		}
		
		
		function login() {
			var username = document.querySelector('#username').value;
			var password = document.querySelector('#password').value;
			password = md5(username + md5(password));

			// The request parameters
			var url = './login';
			var req = JSON.stringify({
				user_id : username,
				password : password,
			});

			ajax('POST', url, req,
			// successful callback
			function(res) {
				var result = JSON.parse(res);
				// successfully logged in
				if (result.status === 'OK') {
					onSessionValid(result);
				}
			},
			// error
			function() {
				showLoginError();
			}, true);
		}

		function showLoginError() {
			document.querySelector('#login-error').innerHTML = 'Invalid username or password';
		}

		function clearLoginError() {
			document.querySelector('#login-error').innerHTML = '';
		}
		function showElement(element, style) {
			var displayStyle = style ? style : 'block';
			element.style.display = displayStyle;
		}


		function ajax(method, url, data, successCallback, errorCallback) {
			var xhr = new XMLHttpRequest();

			xhr.open(method, url, true);

			xhr.onload = function() {
				if (xhr.status === 200) {
					successCallback(xhr.responseText);
				} else {
					errorCallback();
				}
			};

			xhr.onerror = function() {
				console.error("The request couldn't be completed.");
				errorCallback();
			};

			if (data === null) {
				xhr.send();
			} else {
				xhr.setRequestHeader("Content-Type",
						"application/json;charset=utf-8");
				xhr.send(data);
			}
		}


	init();
})();
