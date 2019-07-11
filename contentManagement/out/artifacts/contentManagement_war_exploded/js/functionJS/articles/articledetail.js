var app = angular.module('articledetails',[]);
app.filter('trusted', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}]);


/**获取导航栏列表*/
app.controller('NavCtrl', function($scope,$http,$location) {
    $http({ url: '/index/nav', method: 'get'}).then(function ( info ){
        $scope.navLists = info.data;
    });
    //退出登陆
    $scope.Sign_out=function(){
        $http({
            method:'post',
            url:'/user/logout',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                //响应成功的处理方法体
                if(resq.data.success){
                    alert(resq.data.msg);
                    window.location.reload();
                }else{
                    alert("操作失败，请稍后再试！");
                }

            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    }
    //获取用户头像及用户名
    $scope.judgeIsLogin=function () {
        $http({
            method:'post',
            url:'/user/judgeIsLogin',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                //响应成功的处理方法体
                if(resq.data.success){
                    $scope.u_userhead=resq.data.data.u_userhead;
                    $scope.u_truename=resq.data.data.u_truename+"欢迎您！";
                }else{
                    $scope.u_userhead=resq.data.msg;
                    $scope.u_truename="";
                }

            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    }
});
/**登陆*/
app.controller('login', function($scope,$http) {
    //更换验证码
    $scope.refreshCheckCode=function () {
        var append = '?clearCache=' + new Date().getTime() + 'a'
            + Math.random();
        $('#imageCode').attr('src', $('#imageCode').attr('src') + append);
    }
    $scope.userdata={};
    $scope.LoginsubmitForm = function () {
        if ($scope.signUpForm.$invalid){
            alert("请检查您的信息！");
        }else{
            $http({
                method:'post',
                url:'/user/land',
                data:$.param($scope.userdata),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(
                function success(resq){
                    //响应成功的处理方法体
                    if(resq.data.success){
                        $('#myModal').modal('hide');
                        $('#modelForm');
                        window.parent.location.reload();
                    }else{
                        alert(resq.data.msg);
                    }
                },function error(resq){
                    //响应错误的处理方法体
                    console.log(resq);
                });
        }
    }
});
/**获取后台传出的*/
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
app.controller('articledatas', function($scope,$http) {
    /**初始化基本信息内容*/
    $scope.getcontentessay=function(){
        $http({
            method:'post',
            url:'/article/getTheTechnologyDataList',
            data:$.param({
                m_id:getQueryString("m_id"),
                pageNumber: 1,
                pageSize: 1,
                FYType:''}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                console.log(resq);
                if(resq.data.success){
                    $scope.m_title = resq.data.data.rows[0].m_title;
                    $scope.m_readnum = resq.data.data.rows[0].m_readnum;
                    $scope.m_datatime =resq.data.data.rows[0].m_datatime;
                    $scope.u_truename =resq.data.data.rows[0].userinfo.u_truename;
                    $scope.m_content =  resq.data.data.rows[0].m_content;
                    $scope.m_resources_mode_url = resq.data.data.rows[0].m_resources_mode_url;
                    $scope.fileListData =  resq.data.data.rows[0].technology_module_enclosure;

                }else{
                    alert("查询失败");
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    };
});
