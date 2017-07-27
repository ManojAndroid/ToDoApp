myApp.controller('homeController', function($scope, $state, homeService) {
	console.log("insidehomecontroller");
	$scope.noteInput = function() {

		$scope.myVarheader = true;
		$scope.myVarfooter = true;

	}

	$scope.hideTitle = function() {

		$scope.myVarheader = !$scope.myVarheader;
		$scope.myVarfooter = !$scope.myVarfooter;

		var note = {};
		note.title = $scope.title;
		note.description = $scope.description;
		;
		console.log(note);
		$scope.title = "";
		$('#contentcard').html("");

		var httpObj = homeService.noteCreate(note);

		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log(response.data);

				/*$scope.records=homeService.noteGetAll().then(function());*/

				console.log(records);
				
			} else {
				console.log(" note Creation Faild!!");
				console.log(response.data.status);

			}

		});

	};
});