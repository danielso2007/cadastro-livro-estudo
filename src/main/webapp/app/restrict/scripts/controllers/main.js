'use strict';

/**
 * @ngdoc function
 * @name app.main.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the app.main
 */
var app_main = angular.module('app.main', []);


app_main.controller('MainCtrl', mainFn);

mainFn.$inject = ['ebookService', 'searchBookService', '$rootScope'];
function mainFn (ebookService, searchBookService, $rootScope) {
   var main = this;
   main.listBooks = ebookService.list();
};
