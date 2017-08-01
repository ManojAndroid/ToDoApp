myApp.controller('homeController', function($scope, $state, homeService,$uibModel) {
	console.log("insidehomecontroller");
	$scope.noteInput = function()
	{
		$scope.myVarheader = true;
		$scope.myVarfooter = true;
	}
 /**************get All Notes************************/
	$scope.getNote=function()
	{
    var records=  homeService.noteGetAll();
    records.then(function(resp)
    {
    	console.log(resp.data);
    	$scope.records=resp.data.reverse();
    	
    });
	}
	/********************Note Delete**************/
	$scope.deleteNote=function(taskid)
	{
      console.log("inside delete method")
		console.log("note id"+taskid);
		
		var httpObj = homeService.noteDelete(taskid);
		httpObj.then(function(response)
				{
			if (response.status == 200) 
			{
				console.log(response.data);
				console.log(" note Sucessfullly Deleted!!");
				$scope.getNote();
			}
			else
			{
				console.log(" note Deletion Faild!!");
				console.log(response.data.status);

			}
		});
	}
	/****************Model PopUp***************/
	$scope.openPopUp=function(x)
	{
		var modelInstance=$uBmODEL.OPEN({
			templateUrl:"template/PopUp.html",
			controller:function($uibModelInstance)
			{
				
			}
		})
		
		
		
	}
	
	
	
	/****************Note Update***************/
	$scope.updateNote=function(taskid)
	{
		
		
		
      console.log("inside update  method")
	  console.log("note id"+taskid);
		
		var httpObj = homeService.noteUpdate(taskid);
		httpObj.then(function(response)
				{
			if (response.status == 200) 
			{
				console.log(response.data);
				console.log(" note Sucessfullly Deleted!!");
				$scope.getNote();
			}
			else
			{
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
		httpObj.then(function(response)
				{
			if (response.status == 200) 
			{
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
