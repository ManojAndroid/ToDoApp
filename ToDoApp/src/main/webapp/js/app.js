var myApp = angular.module('toDoApp', ['ui.router','ngSanitize']).config(function ($stateProvider, $urlRouterProvider) {
	
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
	 })
	 
	console.log("inside APPPJSwwww");

 $urlRouterProvider.otherwise('/signin');

});
//this is to use ng-model on div
myApp.directive('contenteditable1', function() {
	return {
		restrict : 'A',
		require : '?ngModel',
		link : function(scope, element, attr, ngModel) {
			var read;
			if (!ngModel) {
				return;
			}
			ngModel.$render = function() {
				return element.html(ngModel.$viewValue);
			};
			element.bind('blur', function() {
				if (ngModel.$viewValue !== $.trim(element.html())) {
					return scope.$apply(read);
				}
			});
			return read = function() {
				console.log("read()");
				return ngModel.$setViewValue($.trim(element.html()));
			};
		}
	};
});

