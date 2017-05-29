/**
 * Created by Владимир on 06.07.2016.
 * Главная форма отчета
 */
(function() {
    'use strict';
    angular.module('cof.EfOtch', [
            'angular.filter',
            'bootstrapLightbox',
            'cof.helper'
        ])
        .filter('startFrom', function(){
            return function(input, start){
                start = +start;
                return input.slice(start);
            }
        })
        .constant('SEARCH_CRITERIA',
            {
                startDate: null,
                endDate: null,
                smena: '',
                carNom: [],
                cargo: [],
                loading: '',
                unloading: '',
                cargoCarrier: '',
                addressee: '',
                sender: '',
                route: '',
                itemPerPage:120,
                position:0
            }
        )
        .controller('EfOtch', [
            '$q',
            '$scope',
            '$state',
            '$element',
            '$resWeight',
            '$mdDialog',
            '$mdMedia',
            '$mdSidenav',
            '$dateHelper',
            'Lightbox',
            'SEARCH_CRITERIA',
            //
            function ($q, $scope, $state, $element, $resWeight, $mdDialog, $mdMedia, $mdSidenav, $dateHelper, Lightbox, SEARCH_CRITERIA) {

                $scope.criteria = angular.copy(SEARCH_CRITERIA);
                $scope.loadings = [];
                $scope.carNum = null;
                $scope.total= {
                    gross: 0,
                    netto: 0,
                    tare:0
                };

                $scope.toggleLeft = buildToggler('left');

                $scope.toggleRight = buildToggler('right');

                function buildToggler(componentId) {
                    return function() {
                        $mdSidenav(componentId).toggle();
                    }
                }

                function _successCB(response) {
                    return response;
                }

                function _setFirstTimeFrame(criteria) {

                    var endDate = criteria.endDate ? criteria.endDate:angular.copy(criteria.startDate);

                    criteria.startDate.setHours(8);
                    criteria.startDate.setMinutes(0);
                    criteria.startDate.setSeconds(0);

                    endDate.setHours(19);
                    endDate.setMinutes(59);
                    endDate.setSeconds(59);

                    criteria.endDate = endDate;

                    return criteria;
                }

                function _setSecondTimeFrame(criteria) {
                    criteria.endDate = criteria.endDate ? criteria.endDate:angular.copy(criteria.startDate);

                    criteria.startDate.setHours(20);
                    criteria.startDate.setMinutes(0);
                    criteria.startDate.setSeconds(0);

                    criteria.endDate.setDate(criteria.endDate.getDate()+1);
                    criteria.endDate.setHours(7);
                    criteria.endDate.setMinutes(59);
                    criteria.endDate.setSeconds(59);

                    return criteria;
                }

                function _init(){
                    $scope.criteria.startDate = $dateHelper.todayFromMonth();
                    $scope.criteria.endDate = new Date();
                    $scope.weights = [];
                    var defer = $q.defer();
                    $q.all(
                        [
                            $resWeight.getLoading().then(_successCB),
                            $resWeight.getUnloadings().then(_successCB),
                            $resWeight.getCargos().then(_successCB),
                            $resWeight.getCargoCarriers().then(_successCB),
                            $resWeight.getAddressees().then(_successCB),
                            $resWeight.getSenders().then(_successCB)
                        ]
                    )
                        .then(function(responses){
                            $scope.loadings = responses[0];
                            $scope.unloadings = responses[1];
                            $scope.cargos = responses[2];
                            $scope.cargoCarriers = responses[3];
                            $scope.addressees = responses[4];
                            $scope.senders = responses[5];
                            defer.resolve(responses);
                            console.log("Справочники загружены")
                        }, function (err, status) {
                            if (err.status === 500) {
                                alert("Внутренная ошибка сервера!!")
                            }
                        });
                    return defer.promise;
                }


                _init().then(function(){
                    _search($scope.criteria);
                });

                function _search(criteria) {
                    var criteria = angular.copy(criteria);

                    criteria.startDate = criteria.startDate ? $dateHelper.getTime(criteria.startDate.getTime()):'';
                    criteria.endDate = criteria.endDate ? $dateHelper.getTime(criteria.endDate.getTime()):'';
                    $resWeight.search(criteria).then(function(_data){

                        $scope.weights = !$scope.weights ? _data.weightAutos : $scope.weights.concat(_data.weightAutos);
                        $scope.itemsPerPage = _data.itemPerPage;
                        $scope.totalItems = _data.totalResult;
                        $scope.total.gross = _data.totalGross;
                        $scope.total.netto = _data.totalNetto;
                        $scope.total.tare = _data.totalTare;
                    });
                }



                $scope.search = function() {
                    console.log();
                    $scope.currentPage = 0;
                    $scope.criteria.position = 0;
                    $scope.criteria.itemPerPage = 100;
                    $scope.weights = [];

                    switch ($scope.criteria.smena) {
                        case 'Смена 1': $scope.criteria = _setFirstTimeFrame($scope.criteria); break;
                        case 'Смена 2': $scope.criteria = _setSecondTimeFrame($scope.criteria); break;
                    }
                    _search($scope.criteria);

                };

                function DialogController($scope, $mdDialog, item, Lightbox) {
                    $scope.item = item;

                    $scope.images = [];

                    $scope.images.push({url: _fixUrl(item.grossJpg1)});
                    $scope.images.push({url: _fixUrl(item.grossJpg2)});
                    $scope.images.push({url: _fixUrl(item.grossJpg3)});
                    $scope.images.push({url: _fixUrl(item.grossJpg4)});

                    $scope.images.push({url: _fixUrl(item.tareJpg1)});
                    $scope.images.push({url: _fixUrl(item.tareJpg2)});
                    $scope.images.push({url: _fixUrl(item.tareJpg3)});
                    $scope.images.push({url: _fixUrl(item.tareJpg4)});

                    function _fixUrl(url) {
                        return url.toString().substring(1);
                    }

                    $scope.openShowImgBox = function (index) {
                        Lightbox.openModal($scope.images, index);
                    };

                    $scope.hide = function() {
                        $mdDialog.hide();
                    };
                    $scope.cancel = function() {
                        $mdDialog.cancel();
                    };


                }

                $scope.showAdvanced = function(ev, item) {
                    var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
                    $mdDialog.show({
                            controller: DialogController,
                            templateUrl: 'main-forms/car.dlg.tpl.html',
                            parent: angular.element(document.body),
                            targetEvent: ev,
                            clickOutsideToClose: true,
                            fullscreen: useFullScreen,
                            locals: {
                                item: item
                            }
                        })
                        .then(function(answer) {
                            $scope.status = 'You said the information was "' + answer + '".';
                        }, function() {
                            $scope.status = 'You cancelled the dialog.';
                        });
                    $scope.$watch(function() {
                        return $mdMedia('xs') || $mdMedia('sm');
                    }, function(wantsFullScreen) {
                        $scope.customFullscreen = (wantsFullScreen === true);
                    });
                };

                $scope.changeInput = function () {
                    if ($scope.carNum.length < 3) {
                        return;
                    }
                    $resWeight.findCars($scope.carNum).then(function (response) {
                       $scope.cars = response;
                    });
                    console.log($scope.carNum);
                };

                $scope.clearSearch = function () {
                    $scope.criteria = angular.copy(SEARCH_CRITERIA);
                    $scope.criteria.startDate = null;
                    $scope.criteria.endDate = null;
                };

                $scope.loadMore = function () {
                  console.log("load more ....");
                    if (($scope.totalItems <= $scope.itemsPerPage) ||
                        ($scope.criteria.position === Math.floor($scope.totalItems/$scope.itemsPerPage)*$scope.itemsPerPage)) {
                        return;
                    }
                    $scope.criteria.position += $scope.itemsPerPage;
                    _search($scope.criteria);
                };

                $scope.preViewPrint = function () {
                    $state.go('print', {criteria: $scope.criteria});
                };

                $element.find('input').on('keydown', function(ev) {
                    ev.stopPropagation();
                });



            }])
        .config([
            //'$mdDateLocale',
            '$mdDateLocaleProvider',
            function($mdDateLocaleProvider){
                $mdDateLocaleProvider.months = ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'];
                $mdDateLocaleProvider.shortMonths = ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'];
            }
        ])
})();
