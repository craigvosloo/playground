angular.module('payroll', ['ngAnimate',
                           'ngCookies',
                           'ngResource',
                           'ngRoute',
                           'ngTouch',
                           'ngSanitize',
                           'ngTable',
                           'ui.bootstrap',
                           'angular-loading-bar',
                           'toaster',
                           'servicesModule',
                           'employeeModule',
                           'payslipModule',
                           'localDate'])

.config(['$httpProvider', '$resourceProvider', '$routeProvider', function($httpProvider, $resourceProvider, $routeProvider) {
	
	$routeProvider.otherwise({redirectTo:'/'}); 
	
	$resourceProvider.defaults.stripTrailingSlashes = false;
	
	$httpProvider.interceptors.push('globalErrorHandler');
}])

.run(function($location, $rootScope) {
	
	var history = [];

    $rootScope.$on('$routeChangeSuccess', function() {
        history.push($location.$$path);
    });

    $rootScope.back = function () {    	    	 
        var prevUrl = history.length > 1 ? history.splice(-2)[0] : "/";
        $location.path(prevUrl);
    };
})

.controller('mainController',	function($scope, $http, $window, $rootScope) {
	
})

.controller('navController', function ($scope, $location, $http) {
	
	$scope.getActiveClass = function (viewLocation) {
		if(viewLocation.length > 1) {
			if ($location.path().substr(0, viewLocation.length) === viewLocation) {
					return 'active';
			} else {
			    return '';
			}
		} else {
			if ($location.path() === viewLocation) {
				return 'active';
			} else {
			    return '';
			}
		}
    };
})

.filter('percentage', function() {
  return function(input, max) {
    if (isNaN(input)) {
      return input;
    }
    return Math.floor((input * 100) / max) + ' %';
  };
});
