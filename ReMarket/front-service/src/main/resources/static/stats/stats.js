angular.module('market-front').controller('ordersStatsController', function ($scope, $http, $location) {
    const ordersStatsPath = 'http://localhost:5555/stats/api/v1';




/*
    $scope.loadOrdersStats = function () {
        $http.get(contextPath + '/orders-stats')
            .then(function (response) {
                console.log("===============OrdersStats===============");
                console.log($scope.OrdersStats);
                $scope.OrdersStats = response.data;
            });
    }
*/

    $scope.loadOrdersStats = function () {
        $http.get(ordersStatsPath + '/orders-stats/number-of-orders-by-user')
            .then(function (response) {
                console.log("===============OrdersStats===============");
                console.log($scope.OrdersStats);
                $scope.OrdersStats = response.data;
                console.log($scope.OrdersStats);
            });
    }




    $scope.loadOrdersStats();

});