myApp.controller('pinCtrl', function($scope,$controller) 
		{
	$controller('homeController',{$scope:$scope})
	{
	
		$scope.homepage=false;
		$scope.trashpage=false;
		$scope.pinpage=true;
		$scope.pin=false;
		$scope.archivepage=false;
		$scope.reminderpage=false;
		$scope.todoname="Trash";
		$scope.notecolor = "trash";
		$scope.notecard=false;
		$scope.notecardmgtop="top";
	}
});