myApp.service('signupService', function($http) {

	console.log("in signupService")
	this.signup = function(signupdata) {
		return $http({
			url : "signup",
			method : "post",
			data : signupdata
		})
	};
	this.forgetPassword = function(email) {
		return $http({
			url : "gatemail",
			method : "post",
			data : email
		})
	};
	
	
	this.resetPassword = function(data) {
		return $http({
			url : "resetpassword",
			method : "post",
			data : data
		})
	};
});