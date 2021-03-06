'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'ngAnimate',
    'angular-jwt',
    'ui.bootstrap',
    //'myApp.index',
    //'myApp.result',
    'myApp.security',
    'myApp.controllers',
    'myApp.filters',
    'myApp.directives',
    'myApp.factories'
            //'myApp.services'
])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/index'});
            }])
        .config(function ($httpProvider) {
            $httpProvider.interceptors.push('AuthInterceptor');
        })
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/index', {
                    templateUrl: 'app/index/index.html',
                    controller: 'IndexCtrl'
                });
            }])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/result', {
                    templateUrl: 'app/result/result.html',
                    controller: 'ResultCtrl'
                });
            }]);

