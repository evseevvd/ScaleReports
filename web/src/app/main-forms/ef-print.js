/**
 * Created by Владимир on 02.10.2016.
 * Контроллер для просмотра на печать
 */

(function() {
    'use strict';
    angular.module('cof.EfPrint', [
            'cof.helper'
        ])
        .controller('EfPrint', [
            '$q',
            '$scope',
            '$state',
            '$resWeight',
            '$dateHelper',
            function ($q, $scope, $state, $resWeight, $dateHelper) {


                function _init() {
                    var defer = $q.defer();
                    if (!$state.params.criteria) {
                        $state.go("main");
                        return;
                    }
                    var criteria = angular.copy($state.params.criteria);
                    criteria.startDate = criteria.startDate ? $dateHelper.getTime(criteria.startDate.getTime()):'';
                    criteria.endDate = criteria.endDate ? $dateHelper.getTime(criteria.endDate.getTime()):'';

                    $resWeight.searchAll(criteria).then(function (response){
                        defer.resolve(response)
                    }, function(error) {
                        defer.reject(error);
                    });

                    return defer.promise;
                }

                _init().then(function(_data){
                    $scope.weights = !$scope.weights ? _data.weightAutos : $scope.weights.concat(_data.weightAutos);
                })

            }])
})();
