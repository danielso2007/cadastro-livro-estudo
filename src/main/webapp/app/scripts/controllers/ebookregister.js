'use strict';

var app_book = angular.module('app.book', []);

app_book.controller('EbookregisterCtrl', function (ebookService, searchBookService, $rootScope, $location) {
    var bookScope = this;

    bookScope.book = new ebookService();
    bookScope.listBooks = ebookService.list();
    $rootScope.listBook;
    bookScope.searchBookService = new searchBookService();

    bookScope.save = function(){
      $rootScope.progressbar.start();
      bookScope.book.$save(function(){
          $rootScope.progressbar.complete();
          bookScope.book = new ebookService();
          bookScope.listBooks = ebookService.list();
      });
    }

    bookScope.remove = function(bookToRemove){
        bookToRemove.$remove({id:bookToRemove.id},
          function(res){
              bookScope.listBooks = ebookService.list();
          });
    };

    bookScope.edit = function(book){
          $rootScope.progressbar.start();
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
