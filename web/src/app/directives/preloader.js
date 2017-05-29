(function () {
    'use strict';
    angular.module("Preloader", [])
        .directive("loading",function(){
         return {
             restrict: 'E',
             controller: function($rootScope, $scope, $mdDialog){

                 $rootScope.$watch('loading', function(oldVal, newVal) {
                     if (!$rootScope.loading) {
                         $mdDialog.hide();
                     } else {
                         $mdDialog.show({
                             clickOutsideToClose: false,
                             $rootScope: $rootScope,
                             preserveScope: true,
                             template: '<md-dialog>' +
                             '<md-dialog-content>' +
                             '<div class="col-sm-1 col-sm-offset-4"><md-progress-circular md-mode="indeterminate"></md-progress-circular>Поиск...</div>' +
                             '</md-dialog-content>' +
                             '</md-dialog>',
                             controller: function DialogController($rootScope, $mdDialog) {
                                 $rootScope.closeDialog = function () {
                                     $mdDialog.hide();
                                 }
                             }
                         });
                     }
                 });
             }
         }
        })
})();
