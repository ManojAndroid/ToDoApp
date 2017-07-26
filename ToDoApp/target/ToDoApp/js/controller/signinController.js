myApp.controller('signinController', function($scope, $state,refreshTokenService) 
		{
	console.log("inside the controller");
	$scope.userLogin = function() 
	{
		var user = {};

		user.email = $scope.email;
		user.password = $scope.password;

		var httpObj = signinService.signin(user);

		httpObj.then(function(response)
	{
			if (response.status == 200)
			{
				console.log(response.data);
				$state.go('home');
			} 
			else
			{
				console.log("login unsuccessfull");
				console.log(response.data.status);
				$state.go('login');
			}
		})
	}
})
