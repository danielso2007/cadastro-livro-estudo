'use strict';

describe('Controller: EbookregisterCtrl', function () {

  // load the controller's module
  beforeEach(module('app.book'));

  var EbookregisterCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    EbookregisterCtrl = $controller('EbookregisterCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(EbookregisterCtrl.awesomeThings.length).toBe(3);
  });
});
