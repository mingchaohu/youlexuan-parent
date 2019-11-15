/*
定义controller
 */
app.controller('specificationController', function ($scope, $http, specificationService,$controller) {

    //声明:specificationController需要继承baseControler中的组件
    $controller('baseController', {$scope: $scope});

    //获取所有的规格信息
    $scope.selectAll = function () {
        specificationService.selectAll().success(function (response) {
            $scope.specificationList = response;
        })
    };

    //分页
    $scope.selectPage = function (page, rows) {
        specificationService.selectPage(page, rows).success(function (response) {
            $scope.specificationList = response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    };

    //增加/更新规格
    $scope.save = function () {
        if ($scope.entity.specification.id != null) {
            specificationService.update($scope.entity).success(function (response) {
                if (response.success) {
                    $scope.reloadList();
                    $scope.entity = {specification: {}, specificationOption:[]};
                }
            })
        } else {
            specificationService.insert($scope.entity).success(function (response) {
                if (response.success) {
                    $scope.reloadList();
                    $scope.entity = {specification: {}, specificationOption: []};
                }
            })
        }
    };

    //查询单个
    $scope.selectOne = function (id) {
        specificationService.selectOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        )
    };

    //删除规格
    $scope.delete = function () {
        if ($scope.selectIds == null || $scope.selectIds.length == 0) {
            alert("请选择后进行删除");
            return;
        }
        specificationService.delete($scope.selectIds).success(
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
        specificationService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.paginationConf.totalItems = response.total;
                $scope.specificationList = response.rows;
            }
        )
    };

    $scope.entity = {specification: {}, specificationOption: []};
    //增加行
    $scope.addRow = function () {
        $scope.entity.specificationOption.push({});
    };

    //删除行
    $scope.deleteRow = function (index) {
        $scope.entity.specificationOption.splice(index, 1);
    };

})