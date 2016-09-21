'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:EbookregisterCtrl
 * @description
 * # EbookregisterCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('EbookregisterCtrl', function () {
    var ebookRegister = this;

    ebookRegister.save = function() {
        console.log('save');
    };
  })
  .factory('ebookService', function() {
      var meaningOfLife = 42;

      return {
          someMethod: function() {
              return meaningOfLife;
          }
      };
  })
;
