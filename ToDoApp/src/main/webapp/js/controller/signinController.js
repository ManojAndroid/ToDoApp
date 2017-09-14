myApp.controller('signinController', function($scope, $state,signinService) 
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
			
			if (response.data.status ==-1)
			{
				$scope.errmsg=response.data.message;
				$state.go('login');
				
			} 
			if(response.data.status ==1)
			{
				localStorage.setItem("accesstoken",response.data.token.accesstoken);
				
				$state.go('home');
				console.log(response.data.status);
			}
		})
	}
	/*Login with gmail
	$scope.loginWithGoogle = function() 
	{
		var httpObj = signinService.loginWithGoogle();

		httpObj.then(function(response)
	{
			
			if (response.data.status ==-1)
			{
				$scope.errmsg=response.data.message;
				$state.go('login');
				
			} 
			if(response.data.status ==1)
			{
				localStorage.setItem("accesstoken",response.data.token.accesstoken);
				
				$state.go('home');
				console.log(response.data.status);
			}
		})
	}
	
	
	*/
	
	
	
	
	
	
	

})
