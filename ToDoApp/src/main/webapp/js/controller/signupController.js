myApp.controller( 'signupController',function($scope, $state,signupService)
{
	
	/******************user Forget Password******************/
	$scope.forgetPassword = function()
	{
		console.log("inside signupcontroller");

		var email={};
		email.email = $scope.email;
		var httpObj = signupService.forgetPassword(email);
		httpObj.then(function(response)
				{
						if (response.data.status ==1)
						{
							
							$scope.userid=response.data.user.id;
							console.log($scope.userid);
							console.log("Email is Available");
							$state.go('resetpassword');
						} 
						else
						{
						$scope.errmsg="This Email is Not Exist......Enter Existing Email";
							console.log(response.data);
							$state.go('forgetpassword');
						}
					})
	}
	/******************user reset Password******************/
	$scope.resetPassword = function()
	{
		console.log("inside signupcontroller");
       if($scope.password==$scope.password1)
    	   {
		var data={};
		data.password = $scope.password;
		data.id       =$scope.userid;
		var httpObj = signupService.resetPassword(data);
		httpObj.then(function(response)
				{
						if (response.data.status ==1)
						{
							console.log(response.data);
							console.log("Email is Available");
							$state.go('signin');
						} 
						else
						{
						$scope.errmsg="This Email is Not Exist......Enter Existing Email";
							console.log(response.data);
							$state.go('resetpassword');
						}
					})
    	   }
       else
	{
    	   $scope.errmsgpswd="Password is not Matching";
    	   $state.go('resetpassword');
    	   
	}
	
	}
	
	
	
	/******************user Registration******************/
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