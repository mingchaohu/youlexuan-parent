/*
定义服务
*/
app.service('specificationService', function ($http) {

    //获取所有的规格
    this.selectAll = function () {
        return $http.get('../specification/selectAll.do');
    };

    //分页
    this.selectPage = function (page, rows) {
        return $http.get('../specification/selectPage.do?page=' + page + '&rows=' + rows);
    };

    //增加规格
    this.insert = function (entity) {
        return $http.post('../specification/insert.do', entity);
    };

    //更新规格
    this.update = function (entity) {
        return $http.post('../specification/update.do', entity);
    };

    //查询单个
    this.selectOne = function (id) {
        return $http.get('../specification/selectOne.do?id=' + id);
    };

    //删除规格
    this.delete = function (ids) {
        return $http.get('../specification/delete.do?ids=' + ids);
    };

    //条件查询
    this.search = function (page, rows, searchEntity) {
        return $http.post('../specification/search.do?page=' + page + "&rows=" + rows, searchEntity);
    };

})