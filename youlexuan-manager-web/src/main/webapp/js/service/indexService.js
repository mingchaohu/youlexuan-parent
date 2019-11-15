//服务层
app.service('indexService', function ($http) {
    //读取登录人名称
    this.getUsername = function () {
        return $http.get('../index/getUsername.do');
    };

});
