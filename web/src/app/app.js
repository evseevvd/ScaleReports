(function(){
'use strict';
angular
    .module("cof", [
        'ui.router',
        'ui.bootstrap',
        'ngMaterial',
        'dataGrid',
        'pagination',
        'Preloader',
        'app.templates',
        'cof.EfOtch',
        'cof.EfPrint',
        'cof.Resources'
])
    .config([
        '$stateProvider',
        '$urlRouterProvider',
        '$httpProvider',
        function ($stateProvider, $urlRouterProvider, $httpProvider) {
            $urlRouterProvider.otherwise('/main');
            $stateProvider
                .state('main', {
                    url: '/main',
                    views: {
                        content: {
                            controller: 'EfOtch',
                            templateUrl: 'main-forms/ef-otch.tpl.html'
                        }
                    },
                    params: {
                        criteria: null
                    }
                })
                .state('print', {
                    url: '/print',
                    views: {
                        content: {
                            controller: 'EfPrint',
                            templateUrl: 'main-forms/ef-print.tpl.html'
                        }
                    },
                    params: {
                        criteria: null
                    }
                });
            $httpProvider.interceptors.push('LoadingInterceptor');
        }
    ])
    .controller('AppCtrl', [
        function($scope){

    }]);
})();
