myApp.service('homeService', function($http) {

	console.log("in homeService")
	this.generatNewAccestoken = function() {
		return $http({
			url : "newaccesstoken",
			method : "post",
			headers: {'accToken': localStorage.getItem("accesstoken")}
		})
	};
	this.noteCreate = function(notedata) {
		return $http({
			url : "rest/todocreate",
			method : "post",
			data : notedata,
			headers: {'accToken': localStorage.getItem("accesstoken")}
			
		})
	};
	
	/***************Note Update******************/
	
	this.noteUpdate = function(updatenote) {
		return $http({
			url : "rest/todoupdate",
			method : "put",
			data : updatenote,
			headers: {'accToken': localStorage.getItem("accesstoken")}
			
		})
	};
	/***************user Log Out******************/
	
	
	this.logoutUser = function() {
		
		return $http({
			url : "rest/logout",
			method : "post",
			headers: {'accToken': localStorage.getItem("accesstoken")}
			
		})
	};
	/***************Note Delete******************/
	
	
	this.noteDelete = function(taskid)
	{
		return $http({
			url : "rest/tododelete/"+taskid,
			method : "DELETE",
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