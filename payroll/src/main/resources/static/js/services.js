var servicesModule = angular.module('servicesModule', []);

servicesModule.factory('globalErrorHandler', function($q, $rootScope, toaster, $location) {
	  return {
		    'response': function(response) {
		      return response || $q.when(response);
		    },

		   'responseError': function(rejection) {
			   console.log("Response Error: " + rejection.status);
			   console.log(rejection);	           
			   return $q.reject(rejection);
		    }
	  };
});