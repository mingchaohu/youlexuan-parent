app.controller('indexController', function ($scope, indexService) {
    //读取当前登录人
    $scope.getUsername = function () {
        indexService.getUsername().success(
            function (response) {
                $scope.username = response.username;
            }
        )
    };

});
