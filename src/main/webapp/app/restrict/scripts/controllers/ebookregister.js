'use strict';

var app_book = angular.module('app.book', []);

app_book.controller('EbookregisterCtrl', function (ebookService, searchBookService, $rootScope, $location, $state) {
    var bookScope = this;

    bookScope.book = new ebookService();
    bookScope.listBooks = ebookService.list();
    $rootScope.listBook;
    bookScope.searchBookService = new searchBookService();

    bookScope.clean = function(form) {
        if (form) {
          form.$setPristine();
          form.$setUntouched();
        }
        bookScope.book = new ebookService();
    };

    bookScope.save = function(form){
      if (form) {
        form.$setPristine();
        form.$setUntouched();
      }
      if (bookScope.book.id) {
        bookScope.book.$update(function(){
           bookScope.book = new ebookService();
           bookScope.listBooks = ebookService.list();
        });
      } else {
        bookScope.book.$save(function(){
            bookScope.book = new ebookService();
            bookScope.listBooks = ebookService.list();
        });
      }
    }

    bookScope.removeOpenDialog = function(bookToRemove, index){
        bookScope.objectRemove = bookToRemove;
        bookScope.indexRemove = index;
        $('#removeDialog').modal('show');
    };

    bookScope.remove = function(){
        var promise =  ebookService.remove({id: bookScope.objectRemove.id}).$promise;

        promise.then(function(data) {
            bookScope.listBooks.splice(bookScope.indexRemove, 1);
            delete bookScope.objectRemove;
            delete bookScope.indexRemove;
            $('#removeDialog').modal('hide');
        }).catch(function(response) {
            $('#removeDialog').modal('hide');
            $rootScope.progressbar.complete();
            console.error(response);
        });
    };

    bookScope.edit = function(book){
          bookScope.book = angular.copy(book);
    };
});

app_book.factory('ebookService', function($resource) {
      return $resource('../rest/book/:id', {}, {
          list: {
            method:'GET',
            isArray:true
          },
          save:{
            method:'POST'
          },
          update:{
              method:'PUT'
          }
      });
});

app_book.factory('searchBookService', function ($resource) {
    return $resource('../rest/book/search/:description',null,{
        searchBook:{
          method:'GET',
          isArray:true
        }
    });
});
