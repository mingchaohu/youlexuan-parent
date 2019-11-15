//商品控制层
app.controller('goodsController', function ($scope, $controller, goodsService, itemCatService, shopTypeTemplateService, uploadService) {

    //继承baseController
    $controller('goodsEditController', {$scope: $scope});

    //变量初始化
    $scope.entity = {goods: {}, goodsDesc: {itemImages: [],specificationItems:[]}};
    $scope.itemCatList1 = [];
    $scope.itemCatList2 = [];
    $scope.itemCatList3 = [];

    //一级商品分类
    $scope.selectItemCatList1 = function () {
        itemCatService.selectTbItemCatByParentId("0").success(function (response) {
            $scope.itemCatList1 = response;
        })
    }

    //二级商品分类
    $scope.$watch('entity.goods.category1Id', function (newValue, oldValue) {
        if (newValue) {
            itemCatService.selectTbItemCatByParentId(newValue).success(function (response) {
                $scope.itemCatList2 = response;
            })
        }
    })

    //三级商品分类
    $scope.$watch('entity.goods.category2Id', function (newValue, oldValue) {
        if (newValue) {
            itemCatService.selectTbItemCatByParentId(newValue).success(function (response) {
                $scope.itemCatList3 = response;
            })
        }
    })

    //模板ID
    $scope.$watch('entity.goods.category3Id', function (newValue, oldValue) {
        if (newValue) {
            itemCatService.findOne(newValue).success(function (response) {
                $scope.entity.goods.typeTemplateId = response.typeId;
            })
        }
    })

    //品牌
    $scope.$watch('entity.goods.typeTemplateId', function (newValue, oldValue) {
        if (newValue) {
            shopTypeTemplateService.selectOne(newValue).success(function (response) {
                $scope.typeTemplate = response;
                $scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds);
                $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
            })
            shopTypeTemplateService.findSpecList(newValue).success(
                function (response) {
                    $scope.specList = response;
                }
            );
        }
    })

    //上传文件
    $scope.uploadFile = function () {
        uploadService.uploadFile().success(function (response) {
            if (response.success) {
                $scope.image_entity.url = response.message;
            } else {
                alert(response.message);
            }
        })
    }

    //增加图片到列表中
    $scope.add_image_entity = function () {
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    }

    //移除列表中的图片
    $scope.remove_image_entity = function (index) {
        $scope.entity.goodsDesc.itemImages.splice(index,1);
    }

    //保存
    $scope.add = function () {
        $scope.entity.goodsDesc.introduction = editor.html();
        goodsService.add($scope.entity).success(function (response) {
            if (response.success) {
                editor.html("");
                $scope.entity = {goods: {}, goodsDesc: {}};
            } else {
                alert(response.message);
            }
        })
    }

    //查询实体
    $scope.findOne = function (id) {
        goodsService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        goodsService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }

    $scope.searchEntity = {};
    $scope.status=['未审核','已审核','审核未通过','关闭'];
    //搜索
    $scope.search = function () {
        goodsService.search($scope.searchEntity).success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    $scope.itemCatList=[];
    $scope.findItemCatList=function () {
        itemCatService.findAll().success(function (response) {
            for(var i=0;i<response.length;i++){
                $scope.itemCatList[response[i].id]=response[i].name;
            }
        })
    }

    $scope.marketableStates = ['已下架','已上架'];
    $scope.updateMarketable = function(status){
        goodsService.updateMarketable($scope.selectIds,status).success(function (resp) {
            if(resp.success){
                $scope.reloadList();
                $scope.selectIds = [];
            }
        });
    }


});	