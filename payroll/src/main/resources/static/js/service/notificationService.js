servicesModule.factory("notificationService", function($rootScope, toaster) {

	return {
		showError : function(message) {
			var duration = 600000;
			
			$rootScope.error = message;
			toaster.pop('error', "Error", message, duration, 'trustedHtml');
		},
		showInfo : function(message, duration) {
			if(duration == null) {
				duration = 5000;
			}
			
			$rootScope.info = message;
			toaster.pop('note', "Information", message, duration, 'trustedHtml');
		},
		showSuccess : function(message, duration) {
			if(duration == null) {
				duration = 2000;
			}
			
			$rootScope.success = message;
			toaster.pop('success', "Success", message, duration, 'trustedHtml');
		},
		showWarning : function(message, duration) {
			if(duration == null) {
				duration = 5000;
			}
			
			$rootScope.warning = message;
			toaster.pop('warning', "Warning", message, duration, 'trustedHtml');
		},
		showProgress : function(message) {
			var duration = 1200000;
			
			$rootScope.progress = message;
			toaster.pop('note', "Information", "<i class='fa fa-spinner fa-fw fa-spin' style='color:white;'></i>  " + message, duration, 'trustedHtml');
		},
		clearError : function() {

			$rootScope.error = null;
			toaster.clear();
		},
		clearInfo : function() {

			$rootScope.info = null;
			toaster.clear();
		},
		clearSuccess : function() {

			$rootScope.success = null;
			toaster.clear();
		},
		clearWarning : function() {

			$rootScope.warning = null;
			toaster.clear();
		},
		clearProgress : function() {

			$rootScope.progress = null;
			toaster.clear();
		},
		clearAll : function() {

			$rootScope.error = null;
			$rootScope.info = null;
			$rootScope.success = null;
			$rootScope.warning = null;
			$rootScope.progress = null;
			toaster.clear();
		}
	};
});