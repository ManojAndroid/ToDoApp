myApp.service('signinService', function($http) {

	console.log("in signinService")
	this.signin = function(user) {
		return $http({
			url : "signin",
			method : "post",
			data : user
		})
	};
});