myApp.controller('reminderCtrl', function($scope,$controller) 
		{
	$controller('homeController',{$scope:$scope})
	{
	
		$scope.homepage=false;
		$scope.trashpage=false;
		$scope.pinpage=false;
		$scope.archivepage=false;
		$scope.reminderpage=true;
		$scope.todoname="Reminders";
		$scope.notecolor = "reminder";
		$scope.notecard=true;
		$scope.navbarcolor="navareminder";
	}
});