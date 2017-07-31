'use strict';

angular.module('employeeModule', ['ngRoute'])

.config(function($routeProvider) {
	
	$routeProvider.when('/', {
		templateUrl : 'js/module/employee/employees.html',
		controller : 'employeesController'
	})
	.when('/employee/:employeeId?', {
		templateUrl : 'js/module/employee/employee.html',
		controller : 'employeeController'
	})
	.when('/employee/:employeeId?/payslip', {
		templateUrl : 'js/module/payslip/payslips.html',
		controller : 'payslipsController'
	});
})

.controller('employeesController',function($resource, $timeout, $scope, employeeService, notificationService, ngTableParams, $filter, $uibModal, $log, $location, $routeParams) {
	$scope.pageClass = 'page-main';	
	
	$scope.employees;
	
	function getEmployees() {
		employeeService.getEmployees().then(function(data) {
			if (data != null) {
				$scope.employees = data;
				console.log(data);
			} else {
				$scope.employees = [];
				notificationService.showError('No data returned.');
			}
		}, function(error) {
			$scope.employees = [];
			notificationService.showError(error);
		});
	}
	
	getEmployees();
	
	/* Employees Table
	 ****************/
	$scope.tableParams = new ngTableParams({
		page: 1, 		
		count: 10, 			
		sorting: { id: "asc" }
	}, {
		total : 0, 
		counts: [],
		filterDelay: 500,
		getData : function($defer, params) {
			if($scope.employees != null && $scope.employees.length > 0) {
				var filteredData = params.filter() ? $filter('filter')($scope.employees, params.filter()) : $scope.employees;
				var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
				
				params.total(orderedData.length);
				
				$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
			}
		}
	});
	
	$scope.$watch("employees", function(newValue, oldValue) {		
		if($scope.employees) {		
			$scope.tableParams.reload();
		}
	});
	
	/* Employee Functions
	 *******************/
	
	$scope.refreshEmployees = function() {
		getEmployees();
	};
	
	$scope.newEmployee = function() {
		$location.path("/employee");
	};
	
	$scope.selectEmployee = function(employee) {
		$location.path("/employee/" + employee.id);
	};
	
	$scope.viewPayslips = function(employee) {
		$location.path("/employee/"+ employee.id + "/payslip");		
	}
	
	$scope.syncEmployees = function(employee) {
		
		var employeeIndex = -1;
    	angular.forEach($scope.employees, function(item, index) {
    		if(employee.id == item.id) {
    			employeeIndex = index;
    		}
    	});
		       			    	
    	if(employeeIndex !== -1) {
    		$scope.employees.splice(employeeIndex,1, employee);
    	} else {
    		$scope.employees.push(employee);
    	}
	    $scope.refreshEmployees(); 
	};
	
})

.controller('employeeController',function($resource, $timeout, $scope, employeeService, notificationService, ngTableParams, $filter, $routeParams, $location, $uibModal, $log) {
	$scope.pageClass = 'page-sub';	
	
	/* Initialise
	 ************/
	$scope.employee;
	
	if($routeParams.employeeId == null) {
		$scope.employee = {};	
	} else {
		getEmployee($routeParams.employeeId);		
	}
	
	function getEmployee(id) {
		employeeService.getEmployee(id).then(function(data) {
			if (data != null) {
				$scope.employee = data;
			} else {
				$scope.employee = null;
				notificationService.showError('Employee not found.');
			}
		}, function(error) {
			$scope.employee = null;
			notificationService.showError(error);
		});
	}
	
	$scope.closeEmployee = function() {
		$scope.employee = null;
		$location.path("/");
	};	
	
	$scope.saveEmployee = function() {
		console.log($scope.employee);
		employeeService.saveEmployee($scope.employee).then(function(data) {
			if (data != null) {
				$scope.employee = data;
				notificationService.showSuccess("Employee " + $scope.employee.firstName + " " + $scope.employee.lastName + " has been created.");
			} else {
				notificationService.showError('No data returned.');
			}
		}, function(error) {
			notificationService.showError(error);
		});
	};
	
});
