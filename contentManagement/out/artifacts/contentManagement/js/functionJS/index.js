
var app = angular.module('index',['ngRoute']);
app.filter('trusted', ['$sce', function ($sce) {
    return function (text) {
        return $sce.trustAsHtml(text);
    };
}]);
//获取导航栏列表
app.controller('NavCtrl', function($scope,$http,$location) {
    $http({ url: '/index/nav', method: 'get'}).then(function ( info ){
        $scope.navLists = info.data;
    });

    $scope.changeTheColor=function(i){
        $scope.fabricIsSelected = i;
    }
    //退出登陆
    $scope.Sign_out=function(){
        $http({
            method:'post',
            url:'user/logout',
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
    };
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
                    $scope.u_truename=resq.data.data.u_truename;
                }else{
                    $scope.u_userhead=resq.data.msg;
                    $scope.u_truename="XXX";
                }

            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    }
    //初始化加载页面数据
    $scope.initAllData=function () {
        $http({ url: '/index/initIndexAllData', method: 'get'}).then(function ( resq ){
            if(resq.data.success){
                console.log();
                $scope.essayDiariesList = resq.data.data.essayDiaries;
                $scope.technologyModulesList =resq.data.data.technologyModules;
                $scope.MessageWalList = resq.data.data.messageWals;
            }

        });
    }
});
//登陆
app.controller('login', function($scope,$http) {
    //更换验证码
    $scope.refreshCheckCode=function () {
        var append = '?clearCache=' + new Date().getTime() + 'a'
            + Math.random();
        $('#imageCode').attr('src', $('#imageCode').attr('src') + append);
    }
    $scope.userdata={};
    $scope.LoginsubmitForm = function () {
        console.log($scope.userdata)
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