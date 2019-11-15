/*
*定义服务
*/
app.service('brandService',function ($http) {

    //获取所有的品牌信息
    this.selectAll=function () {
        return $http.get('../brand/selectAll.do');
    };

    //分页
    this.selectPage=function (page,rows) {
        return $http.get('../brand/selectPage.do?page='+page+'&rows='+rows);
    };

    //增加品牌
    this.insert=function (entity) {
        return $http.post("../brand/insert.do",entity);
    };

    //更新品牌
    this.update=function (entity) {
        return $http.post("../brand/update.do",entity);
    };

    //查询单个
    this.selectOne=function (id) {
        return $http.get("../brand/selectOne.do?id="+id);
    };

    //删除品牌
    this.delete=function (ids) {
        return $http.get("../brand/delete.do?ids="+ids);
    };

    //条件查询
    this.search=function (page,rows,searchEntity) {
        return $http.post('../brand/search.do?page=' + page + "&rows=" + rows, searchEntity);
    };

})