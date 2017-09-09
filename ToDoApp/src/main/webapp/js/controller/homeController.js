myApp.controller('homeController', function($scope, $state, homeService,$uibModal,fileReader,$window) {
	console.log("insidehomecontroller");
	
	$scope.notecard = true;
	$scope.homepage = true;
	$scope.pinpage = true;
	$scope.archivepage = false;
	$scope.trashpage = false;
	$scope.archivepage = false;
	$scope.reminderpage = false;
	$scope.todoname = "Google Keep";
	$scope.checkreminder = new Date();
	
	
	/** **************Restore notes method************** */
	$scope.refresh = function() {
		window.location.reload();
	}
	
	/** **************addprofile************** */
	$scope.addprofile = function() {
		document.getElementById("addprof").click();
	}
	$scope.uploadeprofile = function() {
		document.getElementById("addprof").click();
		
		var userprofile= {};
	    userprofile.profile = $scope.profile;
		userprofile.id=$scope.userId
		userprofile.profile==$scope.profileimag;
	    var httpObj = homeService.uploadProfile(userprofile);
	      httpObj.then(function(response)
       {
		if (response.status ==200)
		{
			console.log(response.data);
			console.log("Image uploaded");
			$scope.getNote();
		} 
		else
		{
			console.log("Image uploading fld");
			console.log(response.status);
			
		}
	})
	}
	/** **************addimage  method************** */
	$scope.addImage = function() {
		document.getElementById("addImg").click();
	}
	/** **************Share on face book method************** */
	$scope.facebookshare = function(x) {
		FB.init({
			appId : '689518927923824',
			status : true,
			xfbml : true,
			version : 'v2.7',
		});

		FB.ui({
			method : 'share_open_graph',
			action_type : 'og.shares',
			action_properties : JSON.stringify({
				object : {
					'og:title' : x.title.replace(/<[^>]+>/gm, ' ').replace(
							/&nbsp;/g, ''),
					'og:description' : x.description.replace(/<[^>]+>/gm, ' ')
							.replace(/&nbsp;/g, ''),
				}
			})
		});
	};
	/** **************pin method************** */

	$scope.pinNote = function(x)
	{
		if (x.pin == false) {
			x.pin = true;
			var httpObj = homeService.noteUpdate(x);
			httpObj.then(function(response) {
				if (response.status == 200) {
					$scope.getNote();
				} 
			});
		}
	}
	/** **************pin method************** */

	$scope.unPinNote = function(x) {
		console.log("inside unpinnote")
			x.pin = false;
			var httpObj = homeService.noteUpdate(x);

			httpObj.then(function(response) {
				if (response.status == 200) {
					$scope.getNote();
				} else {
					console.log("Pinned fld");
					console.log(response.data.status);

				}
			});

	}

	/** **************Restore notes method************** */

	$scope.restoreNotes = function(x) {
		if (x.trash == true) {
			x.trash = false;
			var httpObj = homeService.noteUpdate(x);

			httpObj.then(function(response) {
				if (response.status == 200) {
					console.log(response.data);
					$scope.getNote();
				} else {
					console.log(response.data.status);

				}
			});

		}
	}

	/** **************trash notes method************** */

	$scope.trashNotes = function(x) {
		if (x.trash == false) {
			x.trash = true;
			var httpObj = homeService.noteUpdate(x);

			httpObj.then(function(response) {
				if (response.status == 200) {
					console.log(response.data);
					$scope.getNote();
				} else {
					console.log(response.data.status);

				}
			});

		}
	}

	/** ********Set Archive****** */

	$scope.archiveNotes = function(x) {
		if (x.archive == false) {
			x.archive = true;
			var httpObj = homeService.noteUpdate(x);

			httpObj.then(function(response) {
				if (response.status == 200) {
					console.log(response.data);
					$scope.getNote();
				} else {
					console.log(response.data.status);

				}
			});

		}
	}

	/** ********Set UnArchiveNotes****** */

	$scope.unArchiveNotes = function(x) {
		if (x.archive == true) {
			x.archive = false;

			var httpObj = homeService.noteUpdate(x);

			httpObj.then(function(response) {
				if (response.status == 200) {
					$scope.getNote();
				} else {
					console.log(response.data.status);

				}
			});

		}
	}

	/** ********Set Reminder****** */
	$scope.setReminder = function(x, days) {
		var reminderdate = new Date();

		console.log(reminderdate);
		if (days == 'today') {
			x.reminder = (reminderdate.setHours(20, 00, 00));
		} else if (days == 'tomorrow') {
			var tomorrow = new Date();
			tomorrow.setDate(tomorrow.getDate() + 1);
			tomorrow.setHours(8, 00, 00);
			x.reminder = tomorrow;
		} else if (days == 'weekday') {
			var weekday = new Date();
			weekday.setDate(weekday.getDate() + 7);
			weekday.setHours(8, 00, 00);
			x.reminder = weekday;
		}
		var httpObj = homeService.noteUpdate(x);

		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log(response.data);
				console.log("Reminder set");
				$scope.getNote();
			} else {
				console.log("Reminder updation faield");
				console.log(response.data.status);

			}
		});
	}

	/** ********DElete Reminder****** */
	$scope.deleteReminder = function(x) {
		x.reminder = null;
		var httpObj = homeService.noteUpdate(x);

		httpObj.then(function(response) {
			if (response.status == 200) {
				$scope.getNote();
			} else {
				console.log(response.data.status);

			}
		});
	}

	/** ********List And GridView Toggeling****** */

	$scope.changeColor = function(x, colordata)
	{
		x.notecolor = colordata;
		var httpObj = homeService.noteUpdate(x);

		httpObj.then(function(response) {
			if (response.status == 200) {
				console.log(response.data);
				console.log("Note Sucessfullly Updated!!");
				$scope.getNote();
			} else {
				console.log("updation faield");
				console.log(response.data.status);

			}
		});
	}

	$scope.showlist = function() {
		$scope.gridview1 = false;
		$scope.listview1 = true;
		$scope.listgridtoggle = "col-lg-12 col-md-10 col-sm-12 col-xs-12 list item"
		localStorage.setItem("view", "list");
	}

	$scope.showgrid = function() {

		$scope.gridview1 = true;
		$scope.listview1 = false;
		$scope.listgridtoggle = "col-lg-3 col-md-6 col-sm-12 col-xs-12 grid item "
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
		records
				.then(function(resp) {
					$scope.records = resp.data.reverse();
					$scope.reminder = resp.data[0].reminder;
					$scope.email = resp.data[0].user.email;
					$scope.name = resp.data[0].user.firstname;
					$scope.profileimag = resp.data[0].user.profile;
					$scope.userId=resp.data[0].user.id;
					$scope.weekday = [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu',
							'Fri', 'Sat' ][new Date().getDay()];
					$scope.archive = resp.data[0].archive;

				});
	}

	/** ******************Note Delete************* */
	$scope.deleteNote = function(taskid) {
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
				this.image = x.image;
				this.webscripingtitle = x.webscripingtitle;
				this.webscripingimage = x.webscripingimage;
				this.webscripinghost = x.webscripinghost;
				this.user = x.user;
				console.log("idddddd: " + this.title);
				console.log("archive status" + x.archive);

				/** **************Note Update************** */
				this.update = function() {
					var $ctrl = this;
					var editNotedata = {};
					editNotedata.id = $ctrl.id;
					editNotedata.title = $ctrl.title;
					editNotedata.description = $ctrl.description;
					editNotedata.notecolor = $ctrl.notecolor;
					editNotedata.image = $ctrl.image;
					editNotedata.webscripingtitle = $ctrl.webscripingtitle;
					editNotedata.webscripingimage = $ctrl.webscripingimage;
					editNotedata.webscripinghost = $ctrl.webscripinghost;
					editNotedata.user = $ctrl.user;
                     console.log("update data"+editNotedata);
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
	
	/** ************** collaborator Model PopUp************** */
	$scope.collaboratorPopup = function(x) {
		console.log("inside collaborator");
		$scope.modalInstance = $uibModal.open({
			templateUrl : 'template/Collaborator.html',
			controller : function($scope, $uibModalInstance) {
				this.firstname = x.user.firstname;
				this.lastname = x.user.lastname;
				this.email = x.user.email;
				this.profile = x.user.profile;
				this.sharenoteid=x.id;
				console.log( "user"+x.id);
				/** **************Note Update************** */
				this.collaborate = function() {
					var $ctrl = this;
					var collabprateNotedata = {};

					collabprateNotedata.shareEmail =  $ctrl.shareEmail;
					console.log( "share email",collabprateNotedata.shareEmail);
					collabprateNotedata.sharenoteid = $ctrl.sharenoteid;
					console.log( "note id"+collabprateNotedata.sharenoteid);
					console .log("object"+collabprateNotedata);
				var httpObj = homeService.collaborate(collabprateNotedata);

					httpObj.then(function(response) {
						
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
		note.image=$scope.imageSrc;
		$scope.title = "";
		$('#contentcard').text("");

		var httpObj = homeService.noteCreate(note);
		
		httpObj.then(function(response) {
            console.log(response.data.status);
			
			 if (response.data.status ==-4)
			{
			
				console.log("inside generate new token");
				var httpObj = homeService.generatNewAccestoken();
			}
			if (response.data.status == 1) {
				console.log("created");
				console.log("createdsdafadsf",response.data.status);
				$scope.getNote();

			}
			

		});

	};
	$scope.getNote();
});
