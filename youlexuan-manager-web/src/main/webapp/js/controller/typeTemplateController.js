/*
定义controller
 */
app.controller('typeTemplateController', function ($scope, typeTemplateService, $controller) {

    //声明:brandController需要继承baseControler中的组件
    $controller('baseController', {$scope: $scope});

    //查询所有
    $scope.selectAll = function () {
        typeTemplateService.selectAll().success(function (response) {
            $scope.typeTemplateList = response;
        })
    };

    //分页列表
    $scope.selectPage = function (page, rows) {
        typeTemplateService.selectPage(page, rows).success(
            function (response) {
                $scope.typeTemplateList = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    //根据ID获取实体
    $scope.selectOne = function (id) {
        typeTemplateService.selectOne(id).success(function (response) {
            $scope.entity = response;
            $scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
            $scope.entity.specIds = JSON.parse($scope.entity.specIds);
            $scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
        })
    };

    //增加/修改模板
    $scope.save = function () {
        if ($scope.entity.id == null) {
            typeTemplateService.insert($scope.entity).success(function (response) {
                if (response.success) {
                    $scope.reloadList();
                    $scope.entity = {customAttributeItems: []}
                } else {
                    alert(response.message);
                }
            })
        } else {
            typeTemplateService.update($scope.entity).success(function (response) {
                if (response.success) {
                    $scope.reloadList();
                    $scope.entity = {customAttributeItems: []}
                } else {
                    alert(response.message);
                }
            })
        }
    };

    //删除模板
    $scope.delete = function () {
        typeTemplateService.delete($scope.selectIds).success(function (response) {
            if (response.success) {
                $scope.reloadList();
                $scope.selectIds = [];
            }
        })
    };

    //条件搜索
    $scope.searchEntity = {};
    $scope.search = function (pageNum, pageSize) {
        typeTemplateService.search(pageNum, pageSize, $scope.searchEntity).success(function (response) {
            $scope.typeTemplateList = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    };

    //customAttributeItems
    $scope.entity = {customAttributeItems: []};
    //增加行
    $scope.addRow = function () {
        $scope.entity.customAttributeItems.push({});
    };
    //删除行
    $scope.deleteRow = function (index) {
        $scope.entity.customAttributeItems.splice(index, 1);
    };

    //关联品牌 下拉框
    $scope.brandList = {data: []};
    //规格下拉框
    $scope.specificationList = {data: []};
    //查询品牌和规格
    $scope.selectBrandAndSpecification = function () {
        typeTemplateService.selectBrandAndSpecification().success(
            function (response) {
                $scope.brandList = {data: response.brandList};
                $scope.specificationList = {data: response.specificationList};
            }
        )
    };

    // 将 json字符串 某个属性的值取出来，形成一个普通字符串
    $scope.jsonToString = function (jsonStr, key) {
        var jsonArr = JSON.parse(jsonStr);
        var str = [];
        for (var i = 0; i < jsonArr.length; i++) {
            var jsonObj = jsonArr[i];
            str.push(jsonObj[key]);
        }
        return str.toString();
    };

})