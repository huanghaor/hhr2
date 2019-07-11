var app = angular.module('donkeytripIndex',['tm.pagination']);

//留言墙主页
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
app.controller('donkeytripData', ['$scope','$http','$sce',function($scope,$http, $sce) {
    var fytpe='';
    var types=3;
    /**点击我的游记查询*/
    $scope.MySelfDonkey = function (fytpe) {
        fytpe='owner';
        $scope.reSearch(fytpe);
    }
    $scope.allDonkey = function () {
        $scope.reSearch('');
    }
    //默认排序
    $scope.moren=function () {
        types=3;
        $scope.reSearch(fytpe,types);
    }
    //按更新时间
    $scope.updateTime=function () {
        types=2;
        $scope.reSearch(fytpe,types);
    }
    //按点赞数
    $scope.Traffic_volume=function () {
        types=1;
        $scope.reSearch(fytpe,types);
    }
    /**初始化页面信息：默认加载全部*/
    var url = '/donkeytrip/queryDonkeytripData';
    var reSearch = function(fytpe,types) {
        var postData = {
            //发送给后台的请求数据
            pageNumber: parseInt($scope.paginationConf.currentPage),
            pageSize:  parseInt($scope.paginationConf.itemsPerPage),
            type:types,//条件查询
            FYType:fytpe//区分我的留言墙和公共区域
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
                if(response.data.success){
                    $scope.paginationConf.totalItems = response.data.data.total; //总条数
                    $scope.donkeytripDataList = response.data.data.rows;
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
        itemsPerPage:8,// 每页展示的数据条数
        //pagesLength:5,//分页条目的长度
        //isSelectPage : true,
        //perPageOptions: [5, 10, 20] //可选择显示条数的数组
    };
    //当页码和页面记录数发生变化时监控后台查询如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', reSearch);
    /**点赞*/
    $scope.GiveThumbsUp =function (t_id) {
        $.ajax({
            method:'POST',
            url:'/donkeytrip/giveThumbsUpByDonkey',
            data:{'t_id':t_id},
            dataType: "json",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            success: function(resq){
                console.log(resq)
                if(resq.success){
                    window.location.reload();
                }else{
                    alert("系统出错！请稍后再试");
                }


            },
            error:function(resq){
                //响应错误的处理方法体
                console.log(resq);
            }
        })
    }

}]);
