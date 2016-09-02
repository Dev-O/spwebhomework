var textAppp = angular.module('textAppp', ['ngRoute','ngMessages']);
textAppp.config(function($routeProvider) {
  $routeProvider
  .when('/', {
    templateUrl : 'index.html',
    controller  : 'formController'
  })

  .when('/userform', {
    templateUrl : 'pages/userform.html',
    controller  : 'formController'
  })
  .otherwise({redirectTo: '/'});
});