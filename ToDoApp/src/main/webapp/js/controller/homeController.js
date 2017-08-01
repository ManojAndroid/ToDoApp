myApp.controller('homeController', function($scope, $state, homeService) {
	console.log("insidehomecontroller");

	/** ********List And GridView Toggeling****** */
	$scope.listview = true;
	$scope.gridview = false;

	$scope.showlist = function() {
		$scope.listview = true;
		$scope.gridview = false;
		$scope.gridview1 = false;
		$scope.listview1 = true;

		localStorage.setItem("view", "list");
	}

	$scope.showgrid = function() {
		$scope.listview = false;
		$scope.gridview = true;
		$scope.gridview1 = true;
		$scope.listview1 = false;

		localStorage.setItem("view", "grid");
	}

	if (localStorage.view == "list") {
		$scope.showlist();
	} else {
		$scope.showgrid();
	}

	$scope.noteInput = function() {
		$scope.myVarheader = true;
		$scope.myVarfooter = true;
	}
	/** ************get All Notes*********************** */
	$scope.getNote = function() {
		var records = homeService.noteGetAll();
		records.then(function(resp) 
	{
			$scope.email = resp.data[0].user.email;
			$scope.name = resp.data[0].user.firstname;
		   var name1=	resp.data[0].user.firstname;
		    $scope.firstletter=name1.charAt(0);

			console.log(resp.data[0].user.email);
			$scope.records = resp.data.reverse();

		});
	}

	
	/** ******************Note Delete************* */
	$scope.deleteNote = function(taskid) {
		console.log("inside delete method")
		console.log("note id" + taskid);

		var httpObj = homeService.noteDelete(taskid);
		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log(response.data);
				console.log(" note Sucessfullly Deleted!!");
				$scope.getNote();
			} else {
				console.log(" note Deletion Faild!!");
				console.log(response.data.status);

			}
		});
	}
	/**************Log Out****************************/
	$scope.logout = function() {
		var httpObj = homeService.logoutUser();
		httpObj.then(function(response)
		{
			if (response.status == 200)
			{
				console.log(response.data);
				console.log(" user Sucessfullly loggedout!!");
				$state.go('signin');
			}
			else 
			{
				console.log(" user loggedout faield");
				console.log(response.data.status);

			}
		});
	}
	
	/** **************Model PopUp************** */
	/*
	 * $scope.openPopUp=function(x) { var modelInstance=$uBmODEL.OPEN({
	 * templateUrl:"template/PopUp.html", controller:function($uibModelInstance) { } }) }
	 */

	/** **************Note Update************** */
	$scope.updateNote = function(taskid) {

		console.log("inside update  method")
		console.log("note id" + taskid);

		var httpObj = homeService.noteUpdate(taskid);
		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log(response.data);
				console.log(" note Sucessfullly Deleted!!");
				$scope.getNote();
			} else {
				console.log(" note Deletion Faild!!");
				console.log(response.data.status);

			}
		});
	}

	$scope.hideTitle = function() {

		$scope.myVarheader = !$scope.myVarheader;
		$scope.myVarfooter = !$scope.myVarfooter;

		var note = {};
		note.title = $scope.title;
		note.description = $scope.description;
		console.log(note);
		$scope.title = "";
		$('#contentcard').text("");

		var httpObj = homeService.noteCreate(note);
		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log(response.data);
				console.log("note  Sucessfully Created!!! ");
				$scope.getNote();

			} else {
				console.log(" note Creation Faild!!");
				console.log(response.data.status);

			}

		});

	};
	$scope.getNote();
});
