myApp.controller('trashCtrl', function($scope,$controller) 
		{
	$controller('homeController',{$scope:$scope})
	{
	
		$scope.homepage=false;
		$scope.trashpage=true;
		$scope.pin=false;
		$scope.archivepage=false;
		$scope.reminderpage=false;
		$scope.todoname="Trash";
		$scope.notecolor = "trash";
	}
});