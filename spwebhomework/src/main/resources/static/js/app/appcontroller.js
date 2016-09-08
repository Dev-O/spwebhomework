'use strict';

textAppp.controller('formController', ['$scope','$http',function ($scope, $http) {
	$scope.formValues = {};
	$scope.arrayOfUsers = [{"id":1, "first_name":"Heather", "last_name":"Bell", "hobby":"Eating"},
	                       
		                      {"id":2,"first_name":"Andrea","last_name":"Dean","hobby":"Gaming"},
		                      
		                      {"id":3,"first_name":"Peter","last_name":"Barnes" ,"hobby":null},
		                      
		                      {"id":4,"first_name":"Harry", "last_name":"Bell" , "hobby":"Youtubing"},
		                      
		                      {"id":5,"first_name":"Deborah","last_name":"Burns","hobby":"Fishing"},
		                      
		                      { "id":6, "first_name":"Larry", "last_name":"Kim", "hobby":null},
	                          
		                      {"id":7, "first_name":"Jason","last_name":"Wallace","hobby":"Football"},
		                      
		                      {"id":8,"first_name":"xHeather","last_name":"ell","hobby":"sating"},
		                       
		                      {"id":9,"first_name":"omdr","last_name":"Sean","hobby":null},
		                      {"id":10,"first_name":"weter","last_name":"Barnes","hobby":"seading Books"},

		                      {"id":11,"first_name":"zdrea","last_name":"Dean","hobby":"caming"},
		                     
		                      {"id":12,"first_name":"ieter","last_name":"Barnes","hobby":null},
		                     	                      
		                      {"id":13,"first_name":"garry","last_name":"Tell","hobby":"tytubing"},
		                     	                      
		                      {"id":14,"first_name":"Deborah","last_name":"Burns","hobby":"lishing"},
		                     	                      
		                      {"id":15,"first_name":"Larry","last_name":"rim","hobby":"Skipping"}
		                      
		                     ];
	$scope.dataChanged = [];
	(function(){
		$scope.submitted = true;
		$http({ 
			  method  : 'GET',
			  url     : '/texts',
			 })
			  .then(function(response) {
				  console.log(response);
			      $scope.dataChanged19 = response.data;
			     
			     
			  },   function(error) {
			          //Second function handles error
			         $scope.errorName = error.status + error;
			                  
			      });
			   
			      // if not successful, bind errors to error variables
			     	   
			  })(); 
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
