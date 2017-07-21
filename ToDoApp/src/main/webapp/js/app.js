var myApp = angular.module('toDoApp', ['ui.router']).config(function ($stateProvider, $urlRouterProvider) {
	
	console.log("inside APPPJS");
	
$stateProvider
 .state("signin",{
   url:"/signin",
   templateUrl:"template/SignIn.html",
   controller:"signinController"
 })
 .state('signup',{
   url:'/signup',
   templateUrl:"template/SignUp.html",
   controller:"signupController"
 })

.state('home',{
	   url:'/home',
	   templateUrl:"template/Home.html",
	   controller:"homeController"
	 });

 $urlRouterProvider.otherwise('/signin');

});