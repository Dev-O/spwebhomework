var textAppp = angular.module('textAppp', ['ngRoute','ngMessages','angularUtils.directives.dirPagination']);
textAppp.config(function($routeProvider) {
  $routeProvider
  .when('/userform', {
    templateUrl : 'pages/userform.html',
    controller  : 'formController'
  })
  .otherwise({redirectTo: '/'});
});