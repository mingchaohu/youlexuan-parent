/*
定义controller
 */
app.controller('brandController',function ($scope,$http,brandService,$controller) {

    //声明:brandController需要继承baseControler中的组件
    $controller('baseController',{$scope:$scope});

    //获取所有的品牌信息
    $scope.selectAll=function () {
        brandService.selectAll().success(
            function (response) {
                $scope.brandList=response;
            }
        )
    };

    //分页
    $scope.selectPage=function (page,rows) {
        brandService.selectPage(page,rows).success(
            function (response) {
                $scope.brandList=response.rows;
                $scope.paginationConf.totalItems=response.total;
            }
        )
    };


    //增加/更新品牌
    $scope.save=function () {
        if($scope.entity.name=='' || $scope.entity.name == null){
            alert("品牌名称不能为空");
            return;
        }
        if($scope.entity.firstChar=='' || $scope.entity.firstChar == null){
            alert("首字母不能为空");
            return;
        }
        if($scope.entity.id!=null){
            brandService.update($scope.entity).success(
                function (response) {
                    if(response.success){
                        $scope.entity={name:'',firstChar:''};
                        $scope.reloadList();
                    }else{
                        alert(response.message);
                    }
                }
            )
        }else{
            brandService.insert($scope.entity).success(
                function (response) {
                    if(response.success){
                        $scope.entity={name:'',firstChar:''};
                        $scope.reloadList();
                    }else{
                        alert(response.message);
                    }
                }
            )
        }
    };

    //查询单个
    $scope.selectOne=function (id) {
        brandService.selectOne(id).success(
        function (response) {
            $scope.entity=response;
        }
    )
};

    //删除品牌
    $scope.delete=function () {
        if($scope.selectIds==null||$scope.selectIds.length==0){
            alert("请选择后进行删除");
            return;
        }
        brandService.delete($scope.selectIds).success(
            function (response) {
                if(response.success){
                    $scope.reloadList();
                    $scope.selectIds=[];
                }else{
                    alert(response.message);
                }
            }
        )
    };

    //条件搜索
    $scope.searchEntity={};
    $scope.search=function(page,rows) {
        brandService.search(page,rows,$scope.searchEntity).success(
            function (response) {
                $scope.paginationConf.totalItems = response.total;
                $scope.brandList = response.rows;
            }
        );
    };

})