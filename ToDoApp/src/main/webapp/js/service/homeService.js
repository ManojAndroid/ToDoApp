myApp.service('homeService', function($http) {

	console.log("in homeService")
	this.noteCreate = function(notedata) {
		return $http({
			url : "rest/todocreate",
			method : "post",
			data : notedata,
			headers: {'accToken': localStorage.getItem("accesstoken")}
			
		})
	};
	/***************Note Update******************/
	
	this.noteUpdate = function(notedata) {
		return $http({
			url : "rest/todoupdate",
			method : "put",
			data : notedata,
			headers: {'accToken': localStorage.getItem("accesstoken")}
			
		})
	};
	
	
	/***************Note Delete******************/
	
	
	this.noteDelete = function(taskid) {
		return $http({
			url : "rest/tododelete",
			method : "put",
			data : taskid,
			headers: {'accToken': localStorage.getItem("accesstoken")}
			
		})
	};
	
	
	
	
	this.noteGetAll = function() 
	{
		return $http({
			url : "rest/getlist",
			method : "GET",
			headers: {'accToken': localStorage.getItem("accesstoken")}
			
		})
	};
	
});