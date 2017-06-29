(function () {
    'use strict';

    angular
        .module('app.services')
        .factory('AuthService', ['$http', '$cookies', '$rootScope', '$state', 'UtilService', 'UserService', 'API_URL', AuthService])

        function AuthService($http, $cookies, $rootScope, $state, UtilService, UserService, API_URL) {

	    	var service = {};
	    	
	        service.Login = Login;
	        service.SetCredentials = SetCredentials;
	        service.ClearCredentials = ClearCredentials;
	        service.RefreshToken = RefreshToken;
	        service.GenerateAccessToken = GenerateAccessToken;
	        service.IsLoggedIn = IsLoggedIn;
	        service.UserHasPermission = UserHasPermission;

	        function Login(username, password) {
	        	 return $http.post(API_URL + '/api/auth/login', {'username' : username, 'password': password}).then(UtilService.handleSuccess, UtilService.handleError('Invalid credentials'));
	        }

	        function RefreshToken(token) {
		       	 return $http.post(API_URL + '/api/auth/refresh', {'refreshToken' :token}).then(UtilService.handleSuccess, UtilService.handleError('Invalid refresh token'));
		    }
	        
	        function GenerateAccessToken(token) {
		       	 return $http.get(API_URL + '/api/auth/access').then(UtilService.handleSuccess, UtilService.handleError('Unable to generate token'));
		    }

	        function SetCredentials(auth) {
	            $rootScope.globals = {"auth": auth};
	            $http.defaults.headers.common['Authorization'] = auth.type + " " + auth.accessToken;
	            $cookies.putObject('globals', $rootScope.globals);
	        }

	        function ClearCredentials() {
	            $rootScope.globals = {};
	            $cookies.remove('globals');
	            $cookies.remove('Access-Token');
	            $http.defaults.headers.common.Authorization = null;
	        }

	        function IsLoggedIn() {
	            return $rootScope.currentUser != null && $rootScope.globals.auth != null;
	        }

	        function UserHasPermission(permissions){
	            if(!IsLoggedIn()){
	                return false;
	            }

	            var found = false;
	            angular.forEach(permissions, function(permission, index)
	            {
	                if ($rootScope.currentUser.roles.indexOf(permission) >= 0)
	                {
	                    found = true;
	                    return;
	                }
	            });
	            return found;
	        }
	        
	        return service;
    }

    angular.module('app')
    	.directive('permission', ['AuthService', function(AuthService) {
	       return {
	           restrict: 'A',
	           scope: {
	              permission: '='
	           },
	           link: function (scope, elem, attrs) {
	                scope.$watch(AuthService.IsLoggedIn, function() {
	                    if (AuthService.UserHasPermission(scope.permission)) {
	                        elem.show();
	                    } else {
	                        elem.hide();
	                    }
	                });
	           }
	       }
    }]);
})();