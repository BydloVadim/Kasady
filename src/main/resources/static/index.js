angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/spring/api/v1';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.generatePagesIndexes = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/cart',
            method: 'GET'
        }).then(function (response) {

            $scope.cart = response.data;
        });
    };

    $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/cart/add/' + productId,
            method: 'POST'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.deleteFromCart = function (productId) {
        $http({
            url: contextPath + '/cart/delete/' + productId,
            method: 'DELETE'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/cart/clear',
            method: 'DELETE'
        }).then(function (response) {
            $scope.cart = null;
        });
    };

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/orders',
            method: 'POST'
        }).then(function (response) {
            alert('заказ создан');
            $scope.loadCart();
            $scope.loadOrders();
        });
    };

    $scope.loadOrders = function () {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/orders',
            method: 'GET'
        }).then(function (response) {

            $scope.orders = response.data;
        });
    };

    $scope.clearUser = function() {
        delete $localStorage.summerUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.tryToAuth = function() {
        $http.post(contextPath + '/auth', $scope.user).then(function successCallback(response) {
            if (response.data.token) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.summerUser = {username: $scope.user.username, token: response.data.token};


                $scope.user.username = null;
                $scope.user.password = null;
                $scope.loadOrders();

            }

        }, function errorCallback(response) {
        });
    };

    $scope.tryToLogout = function() {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.isUserLoggedIn = function() {
        if ($localStorage.summerUser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.summerUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.summerUser.token;
        $scope.loadOrders();
    }

    $scope.fillTable();
    $scope.loadCart();
    $scope.loadOrders();

});