var app = angular.module('industry',['tm.pagination']);
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
//主页初始化列表
app.controller('industryInformation', ['$scope','$http','$sce',function($scope,$http, $sce) {
    var url = '/industryInfomation/selectIndustryInfoByPageListBycustomer';
    var reSearch = function(fytpe,types) {
        $scope.w_status='1';
        var postData = {
            //发送给后台的请求数据
            pageNumber: parseInt($scope.paginationConf.currentPage),
            pageSize:  parseInt($scope.paginationConf.itemsPerPage)
        };
        $http({
            method:'POST',
            url:url,
            data:$.param(postData),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(response){
                console.log(response.data);
                if(response.data.success){
                    $scope.paginationConf.totalItems = response.data.data.total; //总条数
                    $scope.IndustryListData = response.data.data.rows;
                    $scope.newZX =response.data.data.rows1;
                    $scope.newTJ =response.data.data.rows2;
                }else{
                    alert(response.data.msg);
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    }

    $scope.reSearch = reSearch;

    //配置分页基本参数
    $scope.paginationConf = {
        currentPage: 1, //起始页
        //totalItems:300,//总共有多少条记录
        itemsPerPage:20,// 每页展示的数据条数
        //pagesLength:5,//分页条目的长度
        //isSelectPage : true,
        //perPageOptions: [5, 10, 20] //可选择显示条数的数组
    };
    //当页码和页面记录数发生变化时监控后台查询如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', reSearch);

    /**获取后台传出的*/
    function getQueryString(name) {
        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }
    /**初始化基本信息内容*/
    $scope.getIndustryDetail=function(){
        $http({
            method:'post',
            url:'/industryInfomation/selectIndustryInfoById',
            data:$.param({
                i_id:getQueryString("i_id"),
                }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                console.log(resq.data);
                if(resq.data.success){
                    $scope.i_title = resq.data.data.i_title;
                    $scope.i_addtime =resq.data.data.i_addtime;
                    $scope.i_content =  resq.data.data.i_content;
                    $scope.i_type =  resq.data.data.i_type;
                    $scope.i_resources_mode_url = resq.data.data.i_resources_mode_url;
                    $scope.i_key_words = resq.data.data.i_key_words;

                }else{
                    alert("查询失败");
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    };
}]);