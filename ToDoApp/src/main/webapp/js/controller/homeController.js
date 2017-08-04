myApp.controller('homeController', function($scope, $state, homeService,
		$uibModal) {
	console.log("insidehomecontroller");

	/** ********List And GridView Toggeling****** */
	  $scope.changeColor=function(x,colordata)
	  {
		  x.color=colordata;
			var httpObj = homeService.noteUpdate(x);
			
			httpObj.then(function(response) 
			{
				if (response.status == 200) 
				{
					console.log(response.data);
					console.log("Note Sucessfullly Updated!!");
					$state.reload();
				} else {
					console.log(" user loggedout faield");
					console.log(response.data.status);

				}
			});
	  }

	$scope.showlist = function()
	{
		$scope.gridview1 = false;
		$scope.listview1 = true;
		/*$scope.myStyle={"width":"70%"};*/
		$scope.listgridtoggle="col-lg-12 col-md-10 col-sm-12 col-xs-12 list "
		localStorage.setItem("view", "list");
	}

	$scope.showgrid = function() {
	
		$scope.gridview1 = true;
		$scope.listview1 = false;
	/*	$scope.myStyle={"width":"100%"};*/
		$scope.listgridtoggle="col-lg-3 col-md-6 col-sm-12 col-xs-12 grid"
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
		records.then(function(resp) {
			$scope.email = resp.data[0].user.email;
			$scope.name = resp.data[0].user.firstname;
			var name1 = resp.data[0].user.firstname;
			$scope.firstletter = name1.charAt(0);

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
	/** ************Log Out*************************** */
	$scope.logout = function() {
		var httpObj = homeService.logoutUser();
		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log(response.data);
				console.log(" user Sucessfullly loggedout!!");
				$state.go('signin');
			} else {
				console.log(" user loggedout faield");
				console.log(response.data.status);

			}
		});
	}

	/** **************Model PopUp************** */
	$scope.openModal = function(x) {
		$scope.modalInstance = $uibModal.open({
			templateUrl : 'template/PopUp.html',
			controller : function($scope, $uibModalInstance) {
				this.id = x.id;
				
				this.title = x.title;
				this.description = x.description;
				this.user = x.user;
				console.log("idddddd: "+    this.title);
				/** **************Note Update************** */
				this.update = function() 
				{
					var $ctrl = this;
					var editNotedata={};
					editNotedata.id=$ctrl.id;
					editNotedata.title=$ctrl.title;
					editNotedata.description=$ctrl.description;
					editNotedata.user=$ctrl.user;
					var httpObj = homeService.noteUpdate(editNotedata);
					
					httpObj.then(function(response) 
					{
						if (response.status == 200) 
						{
							console.log(response.data);
							console.log("Note Sucessfullly Updated!!");
							$state.reload();
						} else {
							console.log(" user loggedout faield");
							console.log(response.data.status);

						}
					});
					
					
					$uibModalInstance.close();
					
				}

			},
			controllerAs : '$ctrl',
			size : 'md',
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
