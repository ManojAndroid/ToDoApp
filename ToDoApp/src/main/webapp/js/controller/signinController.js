myApp.controller('signinController', function($scope, $state,signinService) 
		{
	console.log("inside the controller");
	$scope.userLogin = function() 
	{
		var user = {};

		user.email = $scope.email;
		user.password = $scope.password;

		var httpObj = signinService.signin(user);
		console.log("service response",httpObj);

		httpObj.then(function(response)
	{
			console.log(response.status);
			if (response.status == 200)
			{
				localStorage.setItem("accesstoken",response.data.token.accesstoken);
				$state.go('home');
				console.log(response);
			} 
			else
			{
				console.log("login unsuccessfull");
				console.log(response);
				$state.go('login');
			}
		})
	}
	

})
