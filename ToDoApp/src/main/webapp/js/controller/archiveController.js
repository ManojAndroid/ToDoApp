myApp.controller('archiveCtrl', function($scope,$controller) 
		{
	$controller('homeController',{$scope:$scope})
	{
	
		$scope.homepage=false;
		$scope.trashpage=false;
		$scope.pin=false;
		$scope.archivepage=true;
		$scope.reminderpage=false;
		$scope.todoname="Archive";
		$scope.notecolor = "archive";
	}
});