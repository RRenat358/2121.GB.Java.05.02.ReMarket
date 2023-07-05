angular.module('market-front').controller('ordersController', function ($scope, $http, $location) {
    // const contextPath = 'http://localhost:8189/app';
    // const contextPathApi = 'http://localhost:8189/app/api/v1';
    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.loadOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.goToPay = function (orderId) {
        $location.path('/order_pay/' + orderId);
    }

    $scope.loadOrders();

});