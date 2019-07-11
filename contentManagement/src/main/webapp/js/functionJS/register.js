
//注册
var appRes = angular.module('Register', []);
appRes.controller('register', function($scope,$http,$timeout, $interval) {
    $scope.userdata={};
    //提交注册
    $scope.submitForm = function () {
        if ($scope.signUpForm.$invalid){
            toastr.warning("请检查您的信息！");
        }else{
            $http({
                method:'post',
                url:'/user/register',
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
            },function error(resq){
               //响应错误的处理方法体
               console.log(resq);
            });
        }
    }
    //发送邮箱
    $scope.timer = false;
    $scope.timeout = 600;
    $scope.timerCount = $scope.timeout / 1000;
    $scope.text = "获取验证码";
    $scope.sendEmail=function(){
        if($scope.userdata.u_email==null){
            alert("请填写邮箱");
        }else{
            $scope.showTimer = true;
            $scope.timer = true;
            $scope.text = "秒后重新获取";
            var counter = $interval(function(){
                $scope.timerCount = $scope.timerCount - 1;
            }, 1000);
            $timeout(function(){
                $scope.text = "获取验证码";
                $scope.timer = false;
                $interval.cancel(counter);
                $scope.showTimer = false;
                $scope.timerCount = $scope.timeout / 1000;
            }, $scope.timeout);
            $.ajax({
                method:'post',
                url:'/user/sendemail',
                data:{'u_email':$scope.userdata.u_email},
                dataType: "json",
                success: function(resq){
                    alert(resq.msg);

                },
                error:function(resq){
                    //响应错误的处理方法体
                    console.log(resq);
                }
            })
        }


    };
})
.directive('compare', function () {
    var o = {};
    o.strict='AE';
    o.scope={
        orgText:'=compare'
    };
    o.require='ngModel';
    o.link=function(sco,ele,att,con){
        con.$validators.compare=function(v){
            return v==sco.orgText;
        };
        sco.$watch('orgText',function(){
            con.$validate();
        })
    };
    return o;
});