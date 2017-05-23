'use strict';

angular.module('aluno-server',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Alunos',{templateUrl:'views/Aluno/search.html',controller:'SearchAlunoController'})
      .when('/Alunos/new',{templateUrl:'views/Aluno/detail.html',controller:'NewAlunoController'})
      .when('/Alunos/edit/:AlunoId',{templateUrl:'views/Aluno/detail.html',controller:'EditAlunoController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
