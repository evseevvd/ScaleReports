/**
 * Фабрика для работы с рестом
 */
(function () {
    'use strict';
    angular.module("cof.Resources", [])
        .constant("BACKEND_CONFIG", {
            search: 'cof/api/search',
            searchAll: 'cof/api/search/all',
            loadings: 'cof/api/loading',
            unloadings: 'cof/api/unloading',
            cargos: 'cof/api/cargo',
            cargoCarriers: 'cof/api/cargocarrier',
            addressees: 'cof/api/addressee',
            senders: 'cof/api/sender',
            cars: 'cof/api/carnom',
            findCars: 'cof/api/find/car/',
            smena: 'cof/api/smena'
        })
        .factory("$resWeight", function ($q, $http, BACKEND_CONFIG) {


            var _search = function (criteria) {
                return $http.post(BACKEND_CONFIG.search, criteria, null).then(function (response) {
                    return response.data;
                }, function (response) {
                    return response.$error;
                });
            };

            var _searchAll = function (criteria) {
                return $http.post(BACKEND_CONFIG.searchAll, criteria, null).then(function (response) {
                    return response.data;
                }, function (response) {
                    return response.$error;
                });
            };

            var _getLoadings = function () {
                return $http.get(BACKEND_CONFIG.loadings).then(function (response) {
                    return response.data;
                })
            };

            var _getUnloadings = function () {
                return $http.get(BACKEND_CONFIG.unloadings).then(function (response) {
                    return response.data;
                });
            };

            var _getCargos = function () {
                return $http.get(BACKEND_CONFIG.cargos).then(function (response) {
                    return response.data;
                });
            };

            var _getCargoCarriers = function () {
                return $http.get(BACKEND_CONFIG.cargoCarriers).then(function (response) {
                    return response.data;
                });
            };

            var _getSenders = function () {
                return $http.get(BACKEND_CONFIG.senders).then(function (response) {
                    return response.data;
                });
            };

            var _getAddressees = function () {
                return $http.get(BACKEND_CONFIG.addressees).then(function (response) {
                    return response.data;
                });
            };

            var _getCars = function () {
                return $http.get(BACKEND_CONFIG.cars).then(function (response) {
                    return response.data;
                });
            };

            var _findCars = function (carNum) {
                return $http.get(BACKEND_CONFIG.findCars+carNum).then(function (response) {
                    return response.data;
                });
            };

            return {
                search: _search,
                searchAll: _searchAll,
                getLoading: _getLoadings,
                getUnloadings: _getUnloadings,
                getCargos: _getCargos,
                getCargoCarriers: _getCargoCarriers,
                getAddressees: _getAddressees,
                getSenders: _getSenders,
                getCars: _getCars,
                findCars: _findCars
            }
        })
        .service('LoadingInterceptor', [
            '$q',
            '$rootScope',
            function ($q, $rootScope) {
                var xhrCreations = 0;
                var xhrResolutions = 0;

                function isLoading() {
                    return xhrResolutions < xhrCreations;
                }

                function update(config) {
                    if (!config.headers && config.headers.Accept.indexOf("json") !== -1) {
                        $rootScope.loading = isLoading();
                    } else {
                        $rootScope.loading = false;
                    }

                }

                function updateStatus() {
                    $rootScope.loading = isLoading();
                }

                return {
                    request: function (config) {
                        if (config.headers.Accept.indexOf("json") !== -1) {
                            xhrCreations++;
                        }
                        updateStatus();
                        return config;
                    },
                    requestError: function (rejection) {
                        xhrResolutions++;
                        updateStatus();
                        return $q.reject(rejection);
                    },
                    response: function (response) {
                        if (response.config.headers.Accept.indexOf("json") !== -1) {
                            xhrResolutions++;
                        }
                        updateStatus();
                        return response;
                    },
                    responseError: function (rejection) {
                        xhrResolutions++;
                        updateStatus();
                        return $q.reject(rejection);
                    }
                }
            }
        ])
})();
