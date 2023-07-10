angular.module('market-front').controller('ordersStatsController', function ($scope, $http, $location) {
    const ordersStatsPath = 'http://localhost:5555/stats/api/v1';

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


    $scope.loadAllOrders = function () {
        $http.get(ordersStatsPath + '/orders-stats/all-orders')
            .then(function (response) {
                $scope.allOrders = response.data;
            });
    }

    //============================================================
    $scope.topLimitCarts=3;
    $scope.loadTopProductsByAllCarts = function () {
        $http.get(ordersStatsPath + '/orders-stats/top-products-in-all-carts/' + $scope.topLimitCarts)
            .then(function (response) {
                $scope.topProductsByAllCarts = response.data;
            });
    }

    //============================================================
    $scope.topLimitOrders=3;
    $scope.loadTopProductsByAllOrders = function () {
        $http.get(ordersStatsPath + '/orders-stats/top-products-in-all-orders/' + $scope.topLimitOrders)
            .then(function (response) {
                $scope.topProductsByAllOrders = response.data;
            });
    }





    $scope.loadOrdersStats();
    $scope.loadAllOrders();
    $scope.loadTopProductsByAllCarts();
    $scope.loadTopProductsByAllOrders();

});