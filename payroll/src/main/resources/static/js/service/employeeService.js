servicesModule.factory("employeeService", ['$http', '$q', '$timeout', function($http, $q, $timeout) {

			var urlBase = '/employee';
			var employeeService = {};

			$http.defaults.headers.put["Content-Type"] = "application/json";

			employeeService.getEmployees = function() {

				return $http.get(urlBase + "/").then(function(response) {
					if (typeof response.data === 'object') {
						
						return response.data;
					} else {
						// invalid response
						return $q.reject("Invalid response getting the employees.");
					}
				}, function(response) {

					return $q.reject("Failed response getting the employees.");
				});
			};			

			employeeService.getEmployee = function(id) {

				return $http.get(urlBase + "/" + id).then(function(response) {
					if (typeof response.data === 'object') {
						
						return response.data;
					} else {
						// invalid response
						return $q.reject("Invalid response getting the employee: " + id);
					}
				}, function(response) {

					return $q.reject("Failed response getting the employee: " + id);
				});
			};			

			
			employeeService.saveEmployee = function(employee) {
				return $http.post(urlBase + "/", employee).then(function(response) {
					if (typeof response.data === 'object') {
						return response.data;
					} else {
						// invalid response
						return $q.reject("Invalid response when trying to save the employee: " + employee.firstName + " " + employee.lastName);
					}
				}, function(response) {

				});
			};
			
			return employeeService;
		} ]);