var app = angular.module('messageRemark',[]);
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
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
app.controller('contentReamrk', function($scope,$http) {
    /**获取当前留言墙内容*/
   $scope.getcontent=function(){
       $http({
           method:'post',
           url:'/message/selectMessageByid',
           data:$.param({w_id:getQueryString("w_id")}),
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded'
           }
       }).then(
           function success(resq){
               console.log(resq);
               if(resq.data.success){
                   $scope.messageDatas = resq.data.data;
                   $scope.w_addtime= resq.data.data.w_addtime;
                   $scope.count_remark= resq.data.data.count_remark;
                   $scope.w_praisenumber= resq.data.data.w_praisenumber;
                   $scope.w_content= resq.data.data.w_content;
               }else{
                   alert("查询失败");
               }
           },function error(resq){
               //响应错误的处理方法体
               console.log(resq);
           });
   };
    /**初始化评论信息*/
   $scope.getMessageRemark=function(){

       $http({
           method:'post',
           url:'/messageRemark/queryTheMessageRemark',
           data:$.param({w_id:getQueryString("w_id")}),
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded'
           }
       }).then(
           function success(resq){
               if(resq.data.success){
                   $scope.ReplyDatas = resq.data.data;
               }else{
                   alert(resq.data.msg);
               }
           },function error(resq){
               //响应错误的处理方法体
               console.log(resq);
           });
   };
   /**提交评论*/
    $scope.submitFormData = function () {
        if ($scope.signUpForm.$invalid){
            toastr.warning("请检查您的信息！");
        }else{

            $http({
                method:'post',
                url:'/messageRemark/publicMessageRemark',
                data:$.param({remark_content:$scope.remark_content,remark_message_id:getQueryString("w_id")}),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(
                function success(resq){
                    //响应成功的处理方法体
                    console.log(resq);
                    alert(resq.data.msg)
                    if(resq.data.success){
                        window.location.reload();
                    }
                },function error(resq){
                    //响应错误的处理方法体
                    console.log(resq);
                });
        }
    }
    /**
     * 回复评论按钮操作功能
     * @constructor
     */
    $scope.ReplyTheMessage=function(id){
        var obj = document.getElementsByClassName("id_"+id);
        if(obj[0].style.display=="none"){
            obj[0].style.display="";
        }else{
            obj[0].style.display="none";
        }

    }
    /**
     * 对话回复按钮操作功能
     */
    $scope.ReplyThedialogue = function(id){
        var obj = document.getElementsByClassName("id_dia"+id);
        if(obj[0].style.display=="none"){
            obj[0].style.display="";
        }else{
            obj[0].style.display="none";
        }
    }

    /**提交回复评论*/
    $scope.submitanswerUpForm = function (remark_id,remark_from_uid) {
        $scope.answerData={
            remark_id:remark_id,
            reply_to_uid:remark_from_uid,
            reply_content:$scope.reply_content_answer
        }
        console.log( $scope.answerData)
        $http({
            method:'post',
            url:'/messageRemark/submitanswerUpForm',
            data:$.param( $scope.answerData),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                console.log(resq);
                //响应成功的处理方法体
                alert(resq.data.msg)
                if(resq.data.success){
                    window.location.reload();
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    }

    /**提交对话回复*/
    $scope.submitdialogueUpForm = function (remark_id,reply_from_uid) {
        $scope.dialogue={
            reply_content:$scope.reply_content,
            remark_id:remark_id,
            reply_to_uid:reply_from_uid
        }
        $http({
            method:'post',
            url:'/messageRemark/submitanswerUpForm',
            data:$.param( $scope.dialogue),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                //响应成功的处理方法体
                console.log(resq);
                alert(resq.data.msg)
                if(resq.data.success){
                    window.location.reload();
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    }
});
