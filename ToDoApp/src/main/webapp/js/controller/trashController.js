myApp.controller('trashCtrl', function($scope,$controller) 
		{
	$controller('homeController',{$scope:$scope})
	{
	
		$scope.homepage=false;
		$scope.trashpage=true;
		$scope.pinpage=false;
		$scope.archivepage=false;
		$scope.reminderpage=false;
		$scope.todoname="Trash";
		$scope.notecolor = "trash";
		$scope.notecard=false;
		$scope.notecardmgtop="top";
		$scope.navbarcolor="navatrash";
	}
});