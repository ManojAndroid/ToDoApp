myApp.controller( 'signupController',function($scope, $state,signupService)
{
	$scope.isOtp=true;
	$scope.isResendBtn=false;
	
	
	
	
/*	var resentotpemail=null;
	*//******************user reset password Resendotp*****************//*
		$scope.ResendOtp = function()
	{
		
		console.log("resentotpemail",resentotpemail)
		var httpObj = signupService.forgetPassword(email);
		httpObj.then(function(response)
				{
						if (response.data.status ==1)
						{
							console.log(response);
							$scope.userid=response.data.user.id;
							$state.go('otppage');
						} 
						else
						{
							console.log(response);
						    $scope.errmsg="Enter Valid Email!";
							$state.go('otppage');
						}
					})
	}*/
	/******************user reset password otp*****************/
	$scope.otpNumber = function()
	{
		console.log("inside signupcontroller");
		$scope.isOtp=false;
		var otpdata={};
		otpdata.otpnumber = $scope.otpnumber;
		var httpObj = signupService.otpNumber(otpdata);
		httpObj.then(function(response)
				{
						if (response.data.status ==1)
						{
						   console.log(response.data);
							$state.go('resetpassword');
							
						} 
						else
						{
							console.log(response.data);
							$scope.isResendBtn=true;
							$scope.errmsg="Entered otp is  invalid ..Try Again!!!";
							$state.go('otppage');
						}
					})
	}
	/******************user Forget Password******************/
	
	$scope.forgetPassword = function()
	{
		var email={};
		email.email = $scope.email;
		var httpObj = signupService.forgetPassword(email);
		httpObj.then(function(response)
				{
						if (response.data.status ==1)
						{
							console.log(response);
							$scope.userid=response.data.user.id;
							$scope.user=response.data.user.email;
							$state.go('otppage');
						} 
						else
						{
							console.log(response);
						    $scope.errmsg="Enter Valid Email!";
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
							console.log("Password Reset Sucessfully!");
							$state.go('signin');
						} 
						else
						{
						$scope.errmsg="Password Reset Faild ... Try Again";
							console.log(response.data);
							$state.go('resetpassword');
						}
					})
    	   }
       else
	{
    	   $scope.errmsgpswd="Password is not Matching..Enter Same Password!!";
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