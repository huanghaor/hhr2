var app = angular.module('DonkeyTripDetail',[]);
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
app.controller('DonkeyTripDetailData', function($scope,$http) {
    /**初始化基本信息内容*/
    $scope.getcontent=function(){
        $http({
            method:'post',
            url:'/donkeytrip/queryDonkeytripData',
            data:$.param({t_id:getQueryString("t_id"),FYType:'detailById'}),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                if(resq.data.success){
                    $scope.t_title = resq.data.data.rows[0].t_title;
                    $scope.t_praisenumber = resq.data.data.rows[0].t_praisenumber;
                    $scope.t_create_time =resq.data.data.rows[0].t_create_time;
                    $scope.u_truename =resq.data.data.rows[0].userinfo.u_truename;
                    $scope.t_tourism_strategy =  resq.data.data.rows[0].t_tourism_strategy;
                    $scope.t_begin_time=resq.data.data.rows[0].t_begin_time;
                    $scope.t_end_time=resq.data.data.rows[0].t_end_time;
                    $scope.t_travelperson=resq.data.data.rows[0].t_travelperson;
                    $scope.t_per_capita_consumption=resq.data.data.rows[0].t_per_capita_consumption;
                    $scope.t_place = resq.data.data.rows[0].t_place;
                    $scope.travel_comment = resq.data.data.rows[0].travel_comment;
                    $scope.imgdata = resq.data.data.rows[0].travel_picture_url;
                    $scope.p_picture_url =  resq.data.data.rows[0].travel_picture_url[0].p_picture_url
                }else{
                    alert("查询失败");
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            });
    };
    /**初始化评论信息*/
    $scope.getDonkeyComment=function(){

        $http({
            method:'post',
            url:'/donkeytrip/getDonkeyComment',
            data:$.param({t_id:getQueryString("t_id")}),
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
    $scope.submitDonkeyCommentFormData = function () {
        if ($scope.signUpForm.$invalid){
            toastr.warning("请检查您的信息！");
        }else{

            $http({
                method:'post',
                url:'/donkeytrip/publicTravel_comment',
                data:$.param({c_comment_content:$scope.c_comment_content,tc_id:getQueryString("t_id")}),
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
    $scope.submitanswerUpFormInDonkey = function (replyc_id,remark_from_uid) {
        $scope.answerData={
            replyc_id:replyc_id,
            reply_to_uid:remark_from_uid,
            reply_content:$scope.reply_content_answer
        }
        console.log( $scope.answerData)
        $http({
            method:'post',
            url:'/donkeytrip/Travel_commentReply',
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
    $scope.submitdialogueUpFormInDonkey = function (replyc_id,reply_from_uid) {
        $scope.dialogue={
            reply_content:$scope.reply_content,
            replyc_id:replyc_id,
            reply_to_uid:reply_from_uid
        }
        $http({
            method:'post',
            url:'/donkeytrip/Travel_commentReply',
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
