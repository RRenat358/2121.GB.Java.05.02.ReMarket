angular.module('market-front').controller('ordersStatsController', function ($scope, $http, $location) {
    const ordersStatsPath = 'http://localhost:5555/stats/api/v1';
    // const contextPath = 'http://localhost:5555/core/api/v1';


/*
    $scope.loadOrdersStats = function () {
        $http.get(contextPath + '/orders-stats')
            .then(function (response) {
                $scope.OrdersStats = response.data;
            });
    }
*/

    $scope.loadOrdersStats = function () {
        $http.get(ordersStatsPath + '/orders-stats/number-of-orders-by-user')
            .then(function (response) {
                $scope.OrdersStats = response.data;
            });
    }


    // $scope.loadOrders = function () {
    //     $http.get(contextPath + '/orders')
    //         .then(function (response) {
    //             $scope.MyOrders = response.data;
    //         });
    // }

    $scope.loadAllOrders = function () {
        $http.get(ordersStatsPath + '/orders-stats/all-orders')
            .then(function (response) {
                $scope.allOrders = response.data;
            });
    }


    $scope.topProductsByAllUsers = function () {
        $http.get(ordersStatsPath + '/orders-stats/top-products-by-all-users/3')
            .then(function (response) {
                $scope.topProductsByAllUsers = response.data;
            });
    }







    $scope.loadOrdersStats();
    $scope.loadAllOrders();

});