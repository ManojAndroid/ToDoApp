var myApp = angular.module(
		'toDoApp',
		[ 'ui.router', 'ngSanitize', 'ui.bootstrap', 'tooltips',
				'720kb.datepicker' ]).config(
		function($stateProvider, $urlRouterProvider) {

			console.log("inside APPPJS");

			$stateProvider.state("signin", {
				url : "/signin",
				templateUrl : "template/SignIn.html",
				controller : "signinController"
			})
			
			.state('signup', {
				url : '/signup',
				templateUrl : "template/SignUp.html",
				controller : "signupController"
			})

			.state('home', {
				url : '/home',
				templateUrl : "template/Home.html",
				controller : "homeController"
			})
			
			.state('archive', {
				url : '/archive',
				templateUrl : "template/Home.html",
				controller : "archiveCtrl"
			})
			
			.state('trash', {
				url : '/trash',
				templateUrl : "template/Home.html",
				controller : "trashCtrl"
			})
			
			.state('reminder', {
				url : '/reminder',
				templateUrl : "template/Home.html",
				controller : "reminderCtrl"
			})
			
			.state('pin', {
				url : '/pin',
				templateUrl : "template/Home.html",
				controller : "pinCtrl"
			})
			
			.state('forgetpassword', {
				url : '/forgetpassword',
				templateUrl : "template/ForgottenPassword.html",
				controller : "signupController"
			})
			.state('resetpassword', {
				url : '/resetpassword',
				templateUrl : "template/ResetPassword.html",
				controller : "signupController"
			})

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
myApp.directive('testpackery', [
		'$rootScope',
		'$timeout',
		function($rootScope, $timeout) {
			return {
				restrict : 'A',
				link : function(scope, element, attrs) {
					if ($rootScope.packery === undefined
							|| $rootScope.packery === null) {
						scope.element = element;
						$rootScope.packery = new Packery(
								element[0].parentElement, {
									isResizeBound : true,
									/*rowHeight: 230,
									columnWidth: 230,*/
									itemSelector : '.item'
								});
						$rootScope.packery.bindResize();
						var draggable1 = new Draggabilly(element[0]);
						$rootScope.packery.bindDraggabillyEvents(draggable1);

						draggable1.on('dragEnd', function(instance, event,
								pointer) {
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

						draggable2.on('dragEnd', function(instance, event,
								pointer) {
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

		} ])
/*************** IMAGE FILE UPLOAD*****************/
myApp.directive("ngFileSelect", function(fileReader, $timeout) {
	return {
		scope : {
			ngModel : '='
		},
		link : function($scope, el) {
			function getFile(file) {
				fileReader.readAsDataUrl(file, $scope).then(function(result) {
					$timeout(function() {
						$scope.ngModel = result;
					});
				});
			}

			el.bind("change", function(e) {
				var file = (e.srcElement || e.target).files[0];
				getFile(file);
			});
		}
	};
});

myApp.factory("fileReader", function($q, $log) {
	var onLoad = function(reader, deferred, scope) {
		return function() {
			scope.$apply(function() {
				deferred.resolve(reader.result);
			});
		};
	};

	var onError = function(reader, deferred, scope) {
		return function() {
			scope.$apply(function() {
				deferred.reject(reader.result);
			});
		};
	};

	var onProgress = function(reader, scope) {
		return function(event) {
			scope.$broadcast("fileProgress", {
				total : event.total,
				loaded : event.loaded
			});
		};
	};

	var getReader = function(deferred, scope) {
		var reader = new FileReader();
		reader.onload = onLoad(reader, deferred, scope);
		reader.onerror = onError(reader, deferred, scope);
		reader.onprogress = onProgress(reader, scope);
		return reader;
	};

	var readAsDataURL = function(file, scope) {
		var deferred = $q.defer();

		var reader = getReader(deferred, scope);
		reader.readAsDataURL(file);

		return deferred.promise;
	};

	return {
		readAsDataUrl : readAsDataURL
	};
});
