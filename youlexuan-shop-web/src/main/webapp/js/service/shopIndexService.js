//服务层
app.service('shopIndexService', function ($http) {
    //读取登录人名称
    this.getUsernameAndLastLogintime = function () {
        return $http.get('../index/getUsernameAndLastLogintime.do');
    };

})
