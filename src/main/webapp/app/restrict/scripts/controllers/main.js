'use strict';

/**
 * @ngdoc function
 * @name app.main.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the app.main
 */
var app_main = angular.module('app.main', []);

function mainFn (ebookService, searchBookService, $rootScope) {
   var main = this;
   main.listBooks = ebookService.list();
};

mainFn.$inject = ['ebookService', 'searchBookService', '$rootScope'];
app_main.controller('MainCtrl', mainFn);
