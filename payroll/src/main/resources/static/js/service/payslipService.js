servicesModule.factory("payslipService", ['$http', '$q', '$timeout', function($http, $q, $timeout) {

			var urlBase = '/payslip';
			var payslipService = {};

			$http.defaults.headers.put["Content-Type"] = "application/json";
			
			payslipService.generatePayslip = function(payslipRequest) {

				return $http.post(urlBase + "/", payslipRequest).then(function(response) {
					if (typeof response.data === 'object') {
						return response.data;
					} else {
						// invalid response
						return $q.reject("Invalid response when trying to generate the payslip.");
					}
				}, function(response) {

					return $q.reject("Failed response when trying to generate the payslip.");
				});
			};	
			
			payslipService.savePayslip = function(employeeId, payslip) {

				return $http.post(urlBase + "/employee/" + employeeId, payslip).then(function(response) {
					if (typeof response.data === 'object') {
						return response.data;
					} else {
						// invalid response
						return $q.reject("Invalid response when trying to save the payslip.");
					}
				}, function(response) {

					return $q.reject("Failed response when trying to save the payslip.");
				});
			};	
			
			payslipService.getPayslipsByEmployee = function(employeeId) {

				return $http.get(urlBase + "/employee/" + employeeId).then(function(response) {
					if (typeof response.data === 'object') {
						
						return response.data;
					} else {
						// invalid response
						return $q.reject("Invalid response getting the payslips for employee: " + employeeId);
					}
				}, function(response) {

					return $q.reject("Failed response getting the payslips for employee: " + employeeId);
				});
			};
			
			payslipService.getPayslipPeriodsForEmployee = function(employeeId) {

				return $http.get(urlBase + "/period/employee/" + employeeId).then(function(response) {
					if (typeof response.data === 'object') {
						
						return response.data;
					} else {
						// invalid response
						return $q.reject("Invalid response getting the payslip periods for employee: " + employeeId);
					}
				}, function(response) {

					return $q.reject("Failed response getting the payslip periods for employee: " + employeeId);
				});
			};			

			return payslipService;
		} ]);