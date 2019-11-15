/*
*定义服务
*/
app.service('userService', function ($http) {

    //获取所有的品牌信息
    this.selectAll = function () {
        return $http.get('../user/selectAll.do');
    };

    //分页
    this.selectPage = function (page, rows) {
        return $http.get('../user/selectPage.do?page=' + page + '&rows=' + rows);
    };

    //增加品牌
    this.insert = function (entity) {
        return $http.post("../user/insert.do", entity);
    };

    //更新品牌
    this.update = function (entity) {
        return $http.post("../user/update.do", entity);
    };

    //查询单个
    this.selectOne = function (id) {
        return $http.get("../user/selectOne.do?id=" + id);
    };

    //删除品牌
    this.delete = function (ids) {
        return $http.get("../user/delete.do?ids=" + ids);
    };

    //条件查询
    this.search = function (page, rows, searchEntity) {
        return $http.post('../user/search.do?page=' + page + "&rows=" + rows, searchEntity);
    };

})