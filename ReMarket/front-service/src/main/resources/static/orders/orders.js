angular.module('market-front').controller('ordersController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5555/core/api/v1';
    const ordersStatsPath = 'http://localhost:5555/stats/api/v1';

    $scope.loadOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.goToPay = function (orderId) {
        $location.path('/order_pay/' + orderId);
    }

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



/*
    //подгрузка скрипта в хейдр, но сама функция loadOrdersStats() не вызывается
    $scope.loadStats = function () {
        console.log('preparing to load...')
        console.log("loadStats");
        let node = document.createElement('script');
        node.src = "stats/stats.js";
        node.type = 'text/javascript';
        node.async = true;
        node.charset = 'utf-8';
        document.getElementsByTagName('head')[0].appendChild(node);
        // node.loadOrdersStats();
        console.log(node);
    }
    $scope.loadStats();
*/




    $scope.loadOrders();
    $scope.loadOrdersStats();

});