<html ng-app="toDoApp">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"href="bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/signIn.css" />
<link rel="stylesheet" type="text/css" href="css/signUp.css" />
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" href="bower_components/angular-tooltips/dist/angular-tooltips.css" />
<link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="bower_components/angularjs-datepicker/dist/angular-datepicker.css">
</head>
<body>
	<ui-view> </ui-view>
</body>
<script type="text/javascript"src="bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"src="bower_components/angular/angular.min.js"></script>
<script type="text/javascript"src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<script type="text/javascript" src="bower_components/angular-sanitize/angular-sanitize.js"></script>
<script type="text/javascript"src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script  type="text/javascript" src="bower_components/angular-tooltips/dist/angular-tooltips.js"></script>
<script type="text/javascript" src="bower_components/angularjs-datepicker/dist/angular-datepicker.js"></script>
<script type="text/javascript" src="bower_components/draggabilly/dist/draggabilly.pkgd.min.js"></script>
<script type="text/javascript" src="bower_components/packery/dist/packery.pkgd.min.js"></script>
<script type="text/javascript" src="bower_components/packery-angular/dist/packery-angular.min.js"></script>

<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/controller/signinController.js"></script>
<script type="text/javascript" src="js/controller/signupController.js"></script>
<script type="text/javascript" src="js/service/signinservice.js"></script>
<script type="text/javascript" src="js/service/signupService.js"></script>
<script type="text/javascript" src="js/controller/homeController.js"></script>
<script type="text/javascript" src="js/service/homeService.js"></script>
</html>