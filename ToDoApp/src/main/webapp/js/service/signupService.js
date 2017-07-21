myApp.service('signupService', function($http) {

	console.log("in signinService")
	this.signup = function(signupdata) {
		return $http({
			url : "signup",
			method : "post",
			data : signupdata
		})
	};
});