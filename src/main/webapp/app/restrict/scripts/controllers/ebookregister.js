'use strict';

var app_book = angular.module('app.book', []);

app_book.controller('EbookregisterCtrl', function (ebookService, searchBookService, $rootScope, $location) {
    var bookScope = this;

    bookScope.book = new ebookService();
    bookScope.listBooks = ebookService.list();
    $rootScope.listBook;
    bookScope.searchBookService = new searchBookService();

    bookScope.save = function(){
      bookScope.book.$save(function(){
          bookScope.book = new ebookService();
          bookScope.listBooks = ebookService.list();
      });
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
          bookScope.book = book;
          bookScope.book.$update(function(){
            $rootScope.progressbar.complete();
            bookScope.listBooks = ebookService.list();
          })
    };

    bookScope.search = function(value){
       searchBookService.searchBook({description:value},
         function(data){
            $rootScope.listBook = angular.copy(data);
            $location.path("/resultsearch")
          });
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
