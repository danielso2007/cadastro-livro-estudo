'use strict';

var app_book = angular.module('app.book', []);

app_book.controller('EbookregisterCtrl', function (ebookService, searchBookService, $rootScope, $location) {
    var bookScope = this;

    bookScope.book = new ebookService();
    bookScope.listBooks = ebookService.list();
    $rootScope.listBook;
    bookScope.searchBookService = new searchBookService();

    bookScope.save = function(){
    console.log(bookScope.book);
        bookScope.book.$save(function(){
          bookScope.book = new ebookService();
          bookScope.listBooks = ebookService.list();
      });
    }

    bookScope.remove = function(bookToRemove){
            bookScope.bookRemove = bookToRemove;
            bookScope.bookRemove.$remove({id:bookScope.bookRemove.id},function(res){
            bookScope.listBooks = ebookService.list();
        })
    }

    bookScope.edit = function(book){
          bookScope.book = book;
          bookScope.book.$update(function(){
          bookScope.listBooks = ebookService.list();
        })
    }
    bookScope.search = function(value){
        searchBookService.searchBook({description:value},
       function(data){
          $rootScope.listBook = angular.copy(data);
          $location.path("/resultsearch")
        });
   }
});

app_book.factory('ebookService', function($resource) {
      return $resource('../rest/book', {}, {
          list: {
            method:'GET',
            isArray:true
          },
          save:{
            method:'POST'
          },
          update:{
              method:'PUT'
          },
          remove:{
            method:'DELETE'
          }
      });
});

app_book.factory('searchBookService', function ($resource) {
        return $resource('../rest/book/search/:description',{ description:'@description'},{
            searchBook:{
              method:'GET',
              isArray:true
            }
        });
      });
