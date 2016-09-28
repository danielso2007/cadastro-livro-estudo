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
    'ui.router',
    'ngSanitize',
    'ngTouch',
    'ngProgress',
    'app.book',
    'app.main'
  ]);

function appConfigFn ($stateProvider, $urlRouterProvider, $ariaProvider, $httpProvider) {
    $ariaProvider.config({tabindex: false});

    $httpProvider.interceptors.push(function($q, $rootScope) {
    		return {
    			'request': function(config) {
    				$rootScope.progressbar.start();
    				return config || $q.when(config);
    			},
    			'requestError': function(rejection) {
    				$rootScope.progressbar.complete();
    				return $q.reject(rejection);
    			},
    			'response': function(response) {
    				$rootScope.progressbar.complete();
    				return response || $q.when(response);
    			},
    			'responseError': function(rejection) {
    				$rootScope.progressbar.complete();
    				return $q.reject(rejection);
    			},
    		};
    });

    // Evita o popup de login/senha do navegador
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.defaults.withCredentials = true;


  $urlRouterProvider.otherwise("/404");

  $stateProvider
      .state('home', {
        url: "/",
        templateUrl: "views/main.html",
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .state('ebookRegister', {
        url: "/ebookRegister",
        templateUrl: "views/ebookregister.html",
        controller: 'EbookregisterCtrl',
        controllerAs: 'bookScope'
      })
      .state('resultsearch', {
        url: "/resultsearch",
        templateUrl: "views/resultsearch.html",
        controller: 'EbookregisterCtrl',
        controllerAs: 'bookScope'
      });
};
app.config(appConfigFn);
appConfigFn.$inject = ['$stateProvider', '$urlRouterProvider', '$ariaProvider', '$httpProvider'];


function appRunFn($rootScope, $state, $stateParams) {
	$rootScope.$state = $state;
	$rootScope.$stateParams = $stateParams;

	// Cleanup
	$rootScope.$on('$destroy', function() {
	console.log('das');
		stateChangeStartEvent();
		stateChangeSuccessEvent();
	})

	$rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
		$rootScope.$lastState = {state: fromState, params: fromParams};
	});
};
app.run(appRunFn);
appRunFn.$inject = ['$rootScope', '$state', '$stateParams'];


function appFn($rootScope, $scope, $location, ebookService, searchBookService, ngProgressFactory, $state) {
   $rootScope.progressbar = ngProgressFactory.createInstance();

   $scope.filtro;
   $rootScope.listBook;
   $scope.search = function(value) {
      if (value) {
          $rootScope.progressbar.start();
          searchBookService.searchBook({description:value},
              function(data){
                 $rootScope.progressbar.complete();
                 $rootScope.listBook = angular.copy(data);
                 $state.go("resultsearch");
               });
      }
   };
};
app.controller('AppController', appFn);
appFn.$inject = ['$rootScope', '$scope', '$location', 'ebookService', 'searchBookService', 'ngProgressFactory', '$state'];
