myApp.controller('homeController', function($scope, $state, homeService,$uibModal) 
		{
	console.log("insidehomecontroller");
	
		$scope.homepage=true;
		$scope.archivepage=false;
		$scope.trashpage=false;
		$scope.archivepage=false;

		console.log("inside homeshow");
		
		/****************Restore notes method***************/
		$scope.refresh=function($window){
			$state.reload();
		}
		
		
/****************Restore notes method***************/
		
		$scope.restoreNotes=function(x)
		{
			console.log("inside restore")
			if (x.trash==true)
			{
				x.trash=false;
				var httpObj = homeService.noteUpdate(x);

				httpObj.then(function(response)
			{
					if (response.status == 200) 
				{
						console.log(response.data);
						console.log("Restore sucess");
						$state.reload();
				}
					else
				{
						console.log("Restore fld");
						console.log(response.data.status);

					}
				});
				
			}
		}
		
		
/****************trash notes method***************/
		
		$scope.trashNotes=function(x)
		{
			if (x.trash==false)
			{
				x.trash=true;
				var httpObj = homeService.noteUpdate(x);

				httpObj.then(function(response)
			{
					if (response.status == 200) 
				{
						console.log(response.data);
						console.log("trash sucess");
						$state.reload();
				}
					else
				{
						console.log("trashin fld");
						console.log(response.data.status);

					}
				});
				
			}
		}
	
	/** ********Set Archive****** */
		
	
	$scope.archiveNotes=function(x)
	{
		if (x.archive==false)
		{
			x.archive=true;
			var httpObj = homeService.noteUpdate(x);

			httpObj.then(function(response)
		{
				if (response.status == 200) 
			{
					console.log(response.data);
					console.log("Archive sucess");
					$state.reload();
			}
				else
			{
					console.log("archive fld");
					console.log(response.data.status);

				}
			});
			
		}
	}
	

	/** ********Set UnArchiveNotes****** */
	
	$scope.unArchiveNotes=function(x)
	{
		if (x.archive==true)
			{
			x.archive=false;
			
			var httpObj = homeService.noteUpdate(x);

			httpObj.then(function(response)
			{
				if (response.status == 200) 
				{
					console.log(response.data);
					console.log("UnArchive sucess");
					$state.reload();
				}
				else
				{
					console.log("UnArchive fld");
					console.log(response.data.status);

				}
			});
			
			}
	}
	
	/** ********Set Reminder****** */
	$scope.setReminder = function(x, days) {
		var reminderdate = new Date();
		console.log(reminderdate);
		if (days == 'today') 
		{
			x.reminder = (reminderdate.setHours(20, 00, 00));
		}
		else if (days == 'tomorrow')
		{
			var tomorrow = new Date();
			tomorrow.setDate(tomorrow.getDate() + 1);
			tomorrow.setHours(8, 00, 00);
			x.reminder = tomorrow;
		} 
		else if (days == 'weekday')
		{
			var weekday = new Date();
			weekday.setDate(weekday.getDate() + 7);
			weekday.setHours(8, 00, 00);
			x.reminder = weekday;
		}
		var httpObj = homeService.noteUpdate(x);

		httpObj.then(function(response)
		{
			if (response.status == 200) 
			{
				console.log(response.data);
				console.log("Reminder set");
				$scope.getNote();
			}
			else
			{
				console.log("Reminder updation faield");
				console.log(response.data.status);

			}
		});
	}

	/** ********DElete Reminder****** */
	$scope.deleteReminder = function(x) {
		x.reminder =null ;
		var httpObj = homeService.noteUpdate(x);

		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log("Reminder Deleted!!!!");
				$state.reload();
			} else {
				console.log("Reminder Deletion faield");
				console.log(response.data.status);

			}
		});
	}

	/** ********List And GridView Toggeling****** */

	$scope.changeColor = function(x, colordata) {
		x.notecolor = colordata;
		console.log(colordata);
		var httpObj = homeService.noteUpdate(x);

		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log(response.data);
				console.log("Note Sucessfullly Updated!!");
				$state.reload();
			} else {
				console.log("updation faield");
				console.log(response.data.status);

			}
		});
	}

	$scope.showlist = function() {
		$scope.gridview1 = false;
		$scope.listview1 = true;
		$scope.listgridtoggle = "col-lg-9 col-md-10 col-sm-12 col-xs-12 list "
		localStorage.setItem("view", "list");
	}

	$scope.showgrid = function() {

		$scope.gridview1 = true;
		$scope.listview1 = false;
		$scope.listgridtoggle = "col-lg-3 col-md-6 col-sm-12 col-xs-12 grid "
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
		        	console.log("notes response"+resp.data[0].reminder);
		        	$scope.reminder=resp.data[0].reminder;
					$scope.email = resp.data[0].user.email;
					$scope.name = resp.data[0].user.firstname;
					var name1 = resp.data[0].user.firstname;
					$scope.firstChar = name1.charAt(0);
					$scope.weekday = [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu','Fri', 'Sat' ][new Date().getDay()];
					console.log(resp.data[0].user.email);
					$scope.records = resp.data.reverse();
					$scope.archive=resp.data[0].archive;
					

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
				console.log(" note Deletion Fld!!");
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
				this.notecolor = x.notecolor;
				this.user = x.user;
				console.log("idddddd: " + this.title);
				console.log( "archive status"+x.archive);
				
				/** **************Note Update************** */
				this.update = function() {
					var $ctrl = this;
					var editNotedata = {};
					editNotedata.id = $ctrl.id;
					editNotedata.title = $ctrl.title;
					editNotedata.description = $ctrl.description;
					editNotedata.notecolor = $ctrl.notecolor;
					editNotedata.user = $ctrl.user;
					var httpObj = homeService.noteUpdate(editNotedata);

					httpObj.then(function(response) {
						if (response.status == 200) {
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
