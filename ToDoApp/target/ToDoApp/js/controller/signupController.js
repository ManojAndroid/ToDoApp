myApp.controller( 'signupController',function($scope, $state,signupService)
{
	$scope.userSignup = function()
	{	
		var signupdata = {};
		signupdata.firstname = $scope.firstname;
		signupdata.lastname = $scope.lastname;
		signupdata.email = $scope.email;
		signupdata.mobile = $scope.mobile;
		signupdata.password = $scope.password;
		
	console.log(signupdata);

	var httpObj = signupService.signup(signupdata);

	httpObj.then(function(response)
{
		if (response.status ==200)
		{
			console.log(response.data);
			$state.go('signin');
		} 
		else
		{
			console.log("Registration unsuccessfull");
			console.log(response.status);
			$state.go('signin');
		}
	})

	}
})