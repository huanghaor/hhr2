var app = angular.module('publishArticles',[]);
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
//编辑器
app.controller('publish', function($scope,$http) {
    //初始化下拉列表值
    $http({
        method:'POST',
        url:'/article/getTheArticleTypeList',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(
        function success(response){
            if(response.data.success){
                $scope.ListData = response.data.data;
            }else{
                layer.alert(response.msg);
            }

            // window.location.reload();
        },function error(resq){
            //响应错误的处理方法体
            console.log(resq);
        });
    //配置wangEditor
    var E, editor;
    E = window.wangEditor;
    editor = new E('#editorArticle'); //id一定要一致
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'emoticon',//表情
        'image',  // 插入图片
        'table',  // 表格
        'video',  // 插入视频
        'code',  // 插入代码
        'undo'  // 撤销
    ];
    //如果需要使用 base64 编码直接将图片插入到内容中，可参考一下示例配置
    editor.customConfig.uploadImgShowBase64 = true;
    // 将图片大小限制为 1M
    editor.customConfig.uploadImgMaxSize = 1* 1024 * 1024;
    // 限制一次最多上传 1 张图片
    editor.customConfig.uploadImgMaxLength = 1;
    editor.customConfig.uploadFileName = 'myFileName';

    editor.customConfig.debug=true;

    //上传图片
    editor.customConfig.uploadImgServer = '/message/uploadPicture';
    editor.customConfig.uploadImgHooks = {
        success: function (xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
            console.log(result);
        },
        fail: function (xhr, editor, result) {
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
            alert(result);
        }
    };

    editor.create();
    $scope.submitArticleFormData = function () {
        $scope.m_content=editor.txt.html();
        if ($scope.signUpForm.$invalid){
            toastr.warning("请检查您的信息！");
        }else{

            $http({
                method:'post',
                url:'/article/publishTheArticle',
                data:$.param({m_content:$scope.m_content,fileList: $scope.fileList,m_title:$scope.m_title,m_typeid:$scope.m_typeids}),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(
                function success(resq){
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
    }
    /**获取下拉列表的选择值*/
    $scope.getChange=function () {
        $scope.m_typeids=$scope.m_typeid;
    }
    /**上传文件*/
    $("#file-0a").fileinput({
        language: 'zh', //设置语言
        uploadUrl: '/article/saveTheFile', // you must set a valid URL here else you will get an error
        overwriteInitial: true,
        maxFileSize: 5000,
        maxFilesNum: 5,
        uploadAsync: false, //默认异步上传
        showPreview : false, //是否显示预览
        //allowedFileTypes: ['image', 'video', 'flash'],
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    }).on("filebatchuploadsuccess", function (event, data, previewId, index) {    //一个文件上传成功
        /**将返回的保存路径进行保存数据库*/
        $scope.fileList =data.response.msg;
        alert('附件文件上传成功！');

    }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
        console.log('文件上传失败！'+data);


    });
});