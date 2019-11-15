app.controller('shopIndexController', function ($scope, $controller, shopIndexService) {
    //读取当前登录人
    $scope.getUsernameAndLastLogintime = function () {
        shopIndexService.getUsernameAndLastLogintime().success(
            function (response) {
                $scope.sellerId = response.sellerId;
                $scope.lastLogintime = response.lastLogintime;
            }
        )
    };

});
