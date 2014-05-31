/**
 * Created by sa on 31-05-2014.
 */
angular.module("g1",[]).controller("Gadget1Controller",["$scope",function($scope){
    $scope.add = function() {
      console.log($scope.name);
    };
    $scope.salute = function() {
        console.log("Hello, "+$scope.name);
    }
}]);
