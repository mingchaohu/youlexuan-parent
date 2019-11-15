/*
*定义服务
*/
app.service('typeTemplateService', function ($http) {

    //查询所有
    this.selectAll = function () {
        return $http.get("../typeTemplate/selectAll.do");
    };

    //查询品牌和规格
    this.selectBrandAndSpecification = function () {
        return $http.get('../typeTemplate/selectBrandAndSpecification.do');
    };

    //分页列表
    this.selectPage = function (page, rows) {
        return $http.get('../typeTemplate/selectPage.do?page=' + page + '&rows=' + rows);
    };

    //增加模板
    this.insert = function (entity) {
        return $http.post('../typeTemplate/insert.do', entity);
    };

    //修改模板
    this.update = function (entity) {
        return $http.post('../typeTemplate/update.do', entity);
    };

    //根据ID获取实体
    this.selectOne = function (id) {
        return $http.get('../typeTemplate/selectOne.do?id=' + id);
    };

    //删除模板
    this.delete = function (ids) {
        return $http.get('../typeTemplate/delete.do?ids=' + ids);
    };

    //条件查询
    this.search = function (pageNum, pageSize, searchEntity) {
        return $http.post('../typeTemplate/search.do?pageNum=' + pageNum + '&pageSize=' + pageSize, searchEntity);
    }

})