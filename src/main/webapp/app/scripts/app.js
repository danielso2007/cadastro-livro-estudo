'use strict';

/**
 * @ngdoc overview
 * @name app
 * @description
 * # app
 *
 * Main module of the application.
 */
var app = angular.module('app', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngProgress',
    'app.book',
    'app.main'
  ]);

app.config(appConfigFn);

appConfigFn.$inject = ['$routeProvider', '$ariaProvider', '$httpProvider'];
function appConfigFn ($routeProvider, $ariaProvider, $httpProvider) {
    $ariaProvider.config({tabindex: false});

    $httpProvider.interceptors.push(function($q, $rootScope) {
    		return {
    			'request': function(config) {
    				console.log('request');
    				return config || $q.when(config);
    			},
    			'requestError': function(rejection) {
    				console.log('requestError');
    				return $q.reject(rejection);
    			},
    			'response': function(response) {
    				console.log('response');
    				return response || $q.when(response);
    			},
    			'responseError': function(rejection) {
    				console.log('responseError');
    				return $q.reject(rejection);
    			},
    		};
    });

    // Evita o popup de login/senha do navegador
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.defaults.withCredentials = true;

    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/ebookRegister', {
        templateUrl: 'views/ebookregister.html',
        controller: 'EbookregisterCtrl',
        controllerAs: 'bookScope'
      })
      .when('/resultsearch', {
        templateUrl: 'views/resultsearch.html',
        controller: 'EbookregisterCtrl',
        controllerAs: 'bookScope'
      })
      .otherwise({
        redirectTo: '/'
      });
};


//app.run(appRunFn);
//
//appRunFn.$inject = ['$rootScope', '$state', '$stateParams'];
//function appRunFn($rootScope, $state, $stateParams) {
//	$rootScope.$state = $state;
//	$rootScope.$stateParams = $stateParams;
//
//	// Cleanup
//	$rootScope.$on('$destroy', function() {
//		stateChangeStartEvent();
//		stateChangeSuccessEvent();
//	})
//
//	$rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
//		$rootScope.$lastState = {state: fromState, params: fromParams};
//	});
//};


app.controller('AppController', appFn);

appFn.$inject = ['$rootScope', '$scope', '$location', 'ebookService', 'searchBookService', 'ngProgressFactory'];
function appFn($rootScope, $scope, $location, ebookService, searchBookService, ngProgressFactory) {
   $rootScope.progressbar = ngProgressFactory.createInstance();

   $scope.filtro;
   $rootScope.listBook;
   $scope.search = function(value) {
      if (value) {
          $scope.progressbar.start();
          searchBookService.searchBook({description:value},
              function(data){
                 $scope.progressbar.complete();
                 $rootScope.listBook = angular.copy(data);
                 $location.path("/resultsearch")
               });
      }
   };
};
