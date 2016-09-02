'use strict';

textAppp.controller('formController', ['$scope','$http',function ($scope, $http) {
	$scope.formValues = {};
	
	$scope.dataChanged = [];
	$scope.sort = function(keyname){
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
    };
	                          	
	$scope.processForm = function(isValid) {
		$scope.submitted = true;
		if (!isValid) {
		      alert('correct Form errors');
	    }
		
	  $http({ 
	  method  : 'POST',
	  url     : '/texts',
	  data    : $.param($scope.formValues),  // pass in data as strings
	  headers : { 'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
	 })
	  .then(function(response) {
		  console.log(response);
		  console.log(response.data);
		// if successful, bind success message to message
	      $scope.responseData = response.data.text;
	    
	  },   function(error) {
	          //Second function handles error
	         $scope.contentent = "Something went wrong";
	         $scope.errorName = response.status + error;
	                  
	      });
	   
	      // make another http call for weather information and temperature
	  
	  
	};   
	  
	  $scope.processUpdate = function(isValid){
		 // 
			$scope.submitted = true;
			if (!isValid) {
			      alert('correct Form errors');
		    }
			
			console.log($scope.dupdata);
			$http({ 
				  method  : 'PATCH',
				  url     : '/texts',
				  data    : $.param($scope.dupdata),  // pass in data as strings
				  headers : { 'Content-Type': 'application/x-www-form-urlencoded'}  // set the headers so angular passing info as form data (not request payload)
				 }).then(function(response) {
						  console.log(response);
						  console.log(response.data.responseText);
						  $scope.user.reston = response.data.responseText;
						  
				  //$scope.user.userName =  response.data.responseText;
				  },   function(error) {
				          //Second function handles error
				         $scope.errorName = error;
				                  
				      });
				   
				      // if not successful, bind errors to error variables
			   
				  };
				  
				  
				  
							//  itono();
}]);
