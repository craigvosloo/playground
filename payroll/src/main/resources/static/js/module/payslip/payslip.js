'use strict';

angular.module('payslipModule', ['ngRoute'])

.config(function($routeProvider) {
	
	$routeProvider.when('/employee/:employeeId?/payslip', {
		templateUrl : 'js/module/payslip/payslips.html',
		controller : 'payslipsController'
	})
	.when('/employee/:employeeId?/payslip/new', {
		templateUrl : 'js/module/payslip/payslip.html',
		controller : 'payslipController'
	})
	.when('/', {
		templateUrl : 'js/module/employee/employees.html',
		controller : 'employeesController'
	});
})

.controller('payslipsController',function($resource, $timeout, $scope, employeeService, payslipService, notificationService, ngTableParams, $filter, $uibModal, $log, $location, $routeParams) {
	$scope.pageClass = 'page-main';	
	
	$scope.payslips;
	$scope.employeeId = $routeParams.employeeId;
	$scope.employee;
	
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
	
	function getPayslips(employeeId) {
		payslipService.getPayslipsByEmployee(employeeId).then(function(data) {
			if (data != null) {
				$scope.payslips = data;
				console.log(data);
			} else {
				$scope.payslips = [];
				notificationService.showError('No data returned.');
			}
		}, function(error) {
			$scope.payslips = [];
			notificationService.showError(error);
		});
	}
	
    getPayslips($scope.employeeId);
    getEmployee($scope.employeeId);
	
	/* Payslips Table
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
			if($scope.payslips != null && $scope.payslips.length > 0) {
				var filteredData = params.filter() ? $filter('filter')($scope.payslips, params.filter()) : $scope.payslips;
				var orderedData = params.sorting() ? $filter('orderBy')(filteredData, params.orderBy()) : filteredData;
				
				params.total(orderedData.length);
				
				$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
			}
		}
	});
	
	$scope.$watch("payslips", function(newValue, oldValue) {		
		if($scope.payslips) {		
			$scope.tableParams.reload();
		}
	});
	
	/* Payslip Functions
	 *******************/
	
	$scope.refreshPayslips = function() {
		getPayslips($scope.employeeId);
	};
	
	$scope.closePayslips = function() {
		$location.path("/");
	};
	
	$scope.generatePayslip = function() {
		$location.path("/employee/" + $scope.employeeId + "/payslip/new");
	};
	
	$scope.syncPayslips = function(payslip) {
		
		var payslipIndex = -1;
    	angular.forEach($scope.payslips, function(item, index) {
    		if(payslip.id == item.id) {
    			payslipIndex = index;
    		}
    	});
		       			    	
    	if(payslipIndex !== -1) {
    		$scope.payslips.splice(payslipIndex,1, payslip);
    	} else {
    		$scope.payslips.push(payslip);
    	}
	    $scope.refreshPayslips(); 
	};
		
})

.controller('payslipController',function($resource, $timeout, $scope, employeeService, payslipService, notificationService, ngTableParams, $filter, $routeParams, $location, $uibModal, $log) {
	$scope.pageClass = 'page-sub';	
	
	/* Initialise
	 ************/

	$scope.payslipPeriods;
	$scope.employeeId = $routeParams.employeeId;
	$scope.payslip;
	$scope.payslipPeriod;
	$scope.employee;

	function getPayslipPeriods(employeeId) {
		payslipService.getPayslipPeriodsForEmployee(employeeId).then(function(data) {
			if (data != null) {
				$scope.payslipPeriods = data;
			} else {
				$scope.payslipPeriods = [];
				notificationService.showError('No data returned.');
			}
		}, function(error) {
			$scope.payslipPeriods = [];
			notificationService.showError(error);
		});
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

    getPayslipPeriods($scope.employeeId);
    getEmployee($scope.employeeId);
    
	function generatePayslip(payslipPeriod) {
		
		var payslipRequest = {}
		payslipRequest.employeeId = $scope.employeeId;
		payslipRequest.payslipDate = payslipPeriod.payslipPeriodDate;
		
		console.log(payslipRequest);
		
		payslipService.generatePayslip(payslipRequest).then(function(data) {
			if (data != null) {
				$scope.payslip = data;
				console.log($scope.payslip);
			} else {
				$scope.payslip = {};
				notificationService.showError('No data returned.');
			}
		}, function(error) {
			$scope.payslipPeriods = {};
			notificationService.showError(error);
		});	
	};
	
	$scope.$watch("payslipPeriod", function(newValue, oldValue) {
		if($scope.payslipPeriod) {
		   generatePayslip($scope.payslipPeriod);
		}
	});
	
	$scope.savePayslip = function() {		
		payslipService.savePayslip($scope.employeeId, $scope.payslip).then(function(data) {
			if (data != null) {
				$scope.payslip = data;
				notificationService.showSuccess("Payslip successfully saved");
			} else {
				notificationService.showError('No data returned.');
			}
		}, function(error) {
			notificationService.showError(error);
		});
	};
		
});
