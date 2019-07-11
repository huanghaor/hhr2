
var app = angular.module('personalCenter',['ngRoute']);

/**获取导航栏列表*/
app.controller('NavCtrl', function($scope,$http,$location) {
    $http({ url: '/index/nav', method: 'get'}).then(function ( info ){
        $scope.navLists = info.data;
    });
    //iframe页面跳转
    $scope.jumpToInframe=function (data) {
        $("#mainFrame").attr("src",data);
    }
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

/**保存个人中心信息*/
app.controller('baseInfo', function($scope,$http) {
    /**页面初始化值*/
    $scope.getContentBody=function () {
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
                    $scope.u_name=resq.data.data.u_name;
                    $scope.u_phone=resq.data.data.u_phone;
                    $scope.u_truename=resq.data.data.u_truename;
                    $scope.u_email=resq.data.data.u_email;
                    $scope.u_userhead=resq.data.data.u_userhead;
                    $scope.url_pic =resq.data.data.u_userhead;
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            }
        );
    }

    $scope.userdata={};
    /**修改个人信息*/
    $scope.submitBaseForm = function () {
        $scope.userdata={u_name:$scope.u_name, u_phone:$scope.u_phone,u_truename:$scope.u_truename,u_email:$scope.u_email,old_password:''};
        var regx=/^1[3|5|8|7|9][0-9]\d{8}$/;
        if(!regx.test($scope.u_phone)){
            $scope.error="手机号格式不正确，请重新输入";
        }else{
            $scope.error="";
            $http({
                method:'post',
                url:'/user/updateUserinfo',
                data:$.param($scope.userdata),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(
                function success(resq){
                    //响应成功的处理方法体
                    alert(resq.data.msg);
                    if(resq.data.success){
                        window.location.reload();
                    }
                    alert(resq.data.msg);
                    window.location.reload();
                },function error(resq){
                    //响应错误的处理方法体
                    console.log(resq);
                });
        }

    }

    /**修改密码*/
    $scope.submitupdataPasswordForm=function () {
        $scope.userdata={old_password:$scope.old_password,u_password:$scope.u_password};
        if($scope.password2 !=$scope.u_password){
            $scope.error_pass="请输入相同密码";
        }else{
            $scope.error_pass="";
            $http({
                method:'post',
                url:'/user/updateUserinfo',
                data:$.param($scope.userdata),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(
                function success(resq){
                    //响应成功的处理方法体
                    alert(resq.data.msg);
                    /**请求成功后及执行退出功能，并跳转到首页*/
                    if(resq.data.success){
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
                                    window.location.href="/indexContent.html";
                                }else{
                                    alert("操作失败，请稍后再试！");
                                }

                            },function error(resq){
                                //响应错误的处理方法体
                                console.log(resq);
                            });
                    }
                },function error(resq){
                    //响应错误的处理方法体
                    console.log(resq);
                });
        }
    }

    /**上传头像*/
    $scope.submitbasePictureForm = function () {
        /*var files = $("#fileToUpload").prop('files');//获取到文件列表
        console.log(files);
        if (files.length == 0) {
            alert('请选择文件');
            return;
        } else {
            var reader = new FileReader();//新建一个FileReader
            reader.readAsText(files[0], "UTF-8");//读取文件
            reader.onload = function (evt) { //读取完文件之后会回来这里
                var fileString = evt.target.result;
                //post方式上传图片到控制器
                $http({
                    method:'post',
                    url:'/user/updateTheUser_head',
                    data:$.param(fileString),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(
                    function success(resq){
                        //响应成功的处理方法体
                        console.log(date);
                    },function error(resq){
                        //响应错误的处理方法体
                        console.log(resq);
                    }
                );

            }
        }*/

    }
});