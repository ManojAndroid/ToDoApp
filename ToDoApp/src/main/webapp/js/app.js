var myApp = angular.module('toDoApp', ['ui.router','ngSanitize','ui.bootstrap','tooltips','720kb.datepicker']).config(function ($stateProvider, $urlRouterProvider) {
	
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
	 .state('archive',{
	   url:'/archive',
	   templateUrl:"template/archiveNotes.html",
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
/*************drag and drop************/
myApp.directive('testpackery', ['$rootScope', '$timeout',
	  function($rootScope, $timeout) {
	    return {
	      restrict: 'A',
	      link: function(scope, element, attrs) {
	        if ($rootScope.packery === undefined || $rootScope.packery === null) {
	          scope.element = element;
	          $rootScope.packery = new Packery(element[0].parentElement, {
	            isResizeBound: true,
	            /*rowHeight: 230,
	            columnWidth: 230,*/
	            itemSelector: '.item'
	          });
	          $rootScope.packery.bindResize();
	          var draggable1 = new Draggabilly(element[0]);
	          $rootScope.packery.bindDraggabillyEvents(draggable1);

	          draggable1.on('dragEnd', function(instance, event, pointer) {
	            $timeout(function() {
	              $rootScope.packery.layout();
	              $rootScope.packery.reloadItems();
	            }, 200);
	          });


	          // var orderItems = function() {
	          //   var itemElems = $rootScope.packery.getItemElements();
	          //   console.log(itemElems);
	          //   $(itemElems).each(function(i, itemElem) {
	          //     $(itemElem).text(i + 1);
	          //   });
	          // };

	          // $rootScope.packery.on('layoutComplete', orderItems);
	          // $rootScope.packery.on('dragItemPositioned', orderItems);


	        } else {
	        	
	          // console.log("else", element[0]);
	          var draggable2 = new Draggabilly(element[0]);
	          $rootScope.packery.bindDraggabillyEvents(draggable2);


	          draggable2.on('dragEnd', function(instance, event, pointer) {
	            $timeout(function() {
	              $rootScope.packery.layout();
	            }, 200);
	          });

	        }
	        $timeout(function() {
	          $rootScope.packery.reloadItems();
	          $rootScope.packery.layout();
	        }, 100);
	      }
	    };

	  }
	])
