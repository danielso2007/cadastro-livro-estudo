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
    'app.book',
    'app.main'
  ]);


app.config(function ($routeProvider) {
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
      .otherwise({
        redirectTo: '/'
      });
});
