//定义controller
app.controller('userController', function ($scope, $http, userService, $controller) {

    //声明:userController需要继承baseControler中的组件
    $controller('baseController', {$scope: $scope});

    //获取所有的品牌信息
    $scope.selectAll = function () {
        userService.selectAll().success(
            function (response) {
                $scope.userList = response;
            }
        )
    };

    //分页
    $scope.selectPage = function (page, rows) {
        userService.selectPage(page, rows).success(
            function (response) {
                $scope.userList = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    //增加/更新品牌
    $scope.save = function () {
        if ($scope.entity.username == '' || $scope.entity.username == null) {
            alert("用户名不能为空");
            return;
        }
        if ($scope.entity.password == '' || $scope.entity.password == null) {
            alert("用户密码不能为空");
            return;
        }
        if ($scope.entity.phone == '' || $scope.entity.phone == null) {
            alert("电话号码不能为空");
            return;
        }
        if ($scope.entity.id != null) {
            userService.update($scope.entity).success(
                function (response) {
                    if (response.success) {
                        $scope.entity = {username: '', password: '',phone:''};
                        $scope.reloadList();
                    } else {
                        alert(response.message);
                    }
                }
            )
        } else {
            userService.insert($scope.entity).success(
                function (response) {
                    if (response.success) {
                        $scope.entity = {usename: '', password: '',phone:''};
                        $scope.reloadList();
                    } else {
                        alert(response.message);
                    }
                }
            )
        }
    };

    //查询单个
    $scope.selectOne = function (id) {
        userService.selectOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        )
    };

    //删除品牌
    $scope.delete = function () {
        if ($scope.selectIds == null || $scope.selectIds.length == 0) {
            alert("请选择后进行删除");
            return;
        }
        userService.delete($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();
                    $scope.selectIds = [];
                } else {
                    alert(response.message);
                }
            }
        )
    };

    //条件搜索
    $scope.searchEntity = {};
    $scope.search = function (page, rows) {
        userService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.paginationConf.totalItems = response.total;
                $scope.userList = response.rows;
            }
        );
    };

})