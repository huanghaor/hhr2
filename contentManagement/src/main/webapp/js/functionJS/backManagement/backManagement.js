
var app = angular.module('BackManagement',['tm.pagination']);

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

app.controller('baseInfo', ['$scope','$http','$sce',function($scope,$http, $sce) {
    /**
     * 用户管理  begin
     */
    /**获取下拉列表的选择值*/
    $scope.getTheUserManage=function () {
        
    }
    $scope.getTheSelect=function () {
        $scope.m_typeids=$scope.m_typeid;
    }
    //根据用户管理条件查询值
    $scope.submitUserManagement = function () {
        $scope.userdata={};
        if($scope.m_typeid==1 && ($scope.searchText !=null && $scope.searchText !="")){
            $scope.userdata={u_email:$scope.searchText}
        }
        if($scope.m_typeid==2 && ($scope.searchText !=null && $scope.searchText !="")){
            $scope.userdata={u_name:$scope.searchText}
        }
        if($scope.m_typeid==3 && ($scope.searchText !=null && $scope.searchText !="")){
            $scope.userdata={u_phone:$scope.searchText}
        }
        $http({
            method:'post',
            url:'/user/getAllUsers',
            data:$.param($scope.userdata),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                //响应成功的处理方法体
                console.log(resq.data);
                if(resq.data.success){
                    //数据源
                    $scope.data = resq.data.data;
                    type="user";
                    $scope.tableFunction($scope.data,type);
                }

            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            }
        );

    }
    //初始用户管理化值
    $scope.getContentBody=function () {
         $http.post('/user/getAllUsers').then(function (response) {
             //数据源
             if(response.data.success){
                 $scope.data = response.data.data;
                 type="user";
                 $scope.tableFunction($scope.data,type);
             }else{
                alert(response.data.msg);
             }

         });
    }
    //删除账号
    $scope.deleteUser=function (u_id) {
        url='/user/deleteUserinfoByManagemer';
        condition={u_id:u_id};
        $scope.updateCommon(condition,url);
    }
    //启用账号
    $scope.Enable =function (u_id) {
        url='/user/updateUserinfoByManagemer';
        condition={u_id:u_id,u_status:1};
        $scope.updateCommon(condition,url);
    }
    //禁账号
    $scope.Prohibit =function (u_id) {
        url='/user/updateUserinfoByManagemer';
        condition={u_id:u_id,u_status:2};
        $scope.updateCommon(condition,url);
    }
    $scope.setJuris=function(u_id,type) {
        url = '/user/updateUserinfoByManagemer';
        if (type==1) {
            condition = {u_id: u_id, u_jurisdiction: 'admin'};
            $scope.updateCommon(condition, url);
        }
        if (type==2) {
            condition = {u_id: u_id, u_jurisdiction: 'customer'};
            $scope.updateCommon(condition, url);
        }
    }

    /**====end===*/


    /** 游记数据 begin*/
    //初始化游记数据
    $scope.getTheDonkeyTrip=function () {
        $http.post('/donkeytrip/queryDonkeytripDataByManager').then(function (response) {
            //数据源
            if(response.data.success) {
                type = "donkeytrip";
                $scope.data = response.data.data;
                $scope.tableFunction($scope.data, type);
            }else{
                alert(response.data.msg);
            }
        });
    }
    /**获取游记下拉列表的选择值*/
    $scope.getTheDonkeyTripSelect=function () {
        $scope.t_typeid=$scope.t_typeid;
    }
    //根据游记管理条件查询值
    $scope.submitDonkeyTripManagement = function () {
        $scope.DonkeyTrip={};
        if($scope.t_typeid==1 && ($scope.searchDonkeyText !=null && $scope.searchDonkeyText !="")){
            $scope.DonkeyTrip={t_title:$scope.searchDonkeyText}
        }
        if($scope.t_typeid==2 && ($scope.searchDonkeyText !=null && $scope.searchDonkeyText !="")){
            $scope.DonkeyTrip={t_place:$scope.searchDonkeyText}
        }
        if($scope.t_typeid==3){
            $scope.DonkeyTrip={t_fine_paste:'1'}
        }
        $http({
            method:'post',
            url:'/donkeytrip/queryDonkeytripDataByManager',
            data:$.param($scope.DonkeyTrip),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                //响应成功的处理方法体
                if(resq.data.success){
                    //数据源
                    $scope.data = resq.data.data;
                    type="donkeytrip";
                    $scope.tableFunction($scope.data,type);
                }else{
                    alert(resq.data.msg);
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            }
        );
    }
    //删除游记
    $scope.deleteDonkeyTrip=function (t_id) {
        url='/donkeytrip/deleteDonkeyTripById';
        condition={t_id:t_id};
        $scope.updateCommon(condition,url);
    }
    //设置为精帖
    $scope.SettingpUpAccurate=function (t_id) {
        url='/donkeytrip/updateDonkeyTripById';
        condition={t_id:t_id,t_fine_paste:'1'};
        $scope.updateCommon(condition,url);
    }
    $scope.cancelUpAccurate=function (t_id) {
        url='/donkeytrip/updateDonkeyTripById';
        condition={t_id:t_id,t_fine_paste:'0'};
        $scope.updateCommon(condition,url);
    }
    /**====end=====*/

    /**随笔日记---begin*/
    //获取游记下拉列表的选择值
    $scope.isessaydiaryType=false;
    $scope.isessaydiarySearch=false;
    //初始化下拉列表值
    $http({
        method:'POST',
        url:'/essaydiary/getTheessaydiaryTypeList',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).then(
        function success(response){
            if(response.data.success){
                $scope.ListessaydiaryData = response.data.data;
            }else{
               alert(response.msg);
            }
        },function error(resq){
            //响应错误的处理方法体
            console.log(resq);
        });
    //获取列表值
    $scope.getTheessaydiarySelect=function () {
        $scope.e_typeid=$scope.e_typeid;
        if($scope.e_typeid==1){
            $scope.isessaydiarySearch=false;
            $scope.isessaydiaryType=true;
        }else if($scope.e_typeid==2){
            $scope.isessaydiarySearch=true;
            $scope.isessaydiaryType=false;
        }else{
            $scope.isessaydiarySearch=false;
            $scope.isessaydiaryType=false;
        }
    }
    //获取类型值
    $scope.getEssaydiaryChange=function () {
        $scope.et_id=$scope.et_id;
    }
    //根据技术文章条件查询值
    $scope.submitessaydiaryManagement = function () {
        $scope.essaydiaryCondition={};
        if($scope.e_typeid==1){
            $scope.essaydiaryCondition={e_type:$scope.et_id}
        }
        if($scope.e_typeid==2){
            $scope.essaydiaryCondition={e_title:$scope.searchessaydiaryText}
        }
        if($scope.e_typeid==3){
            $scope.essaydiaryCondition={e_resources_mode:'1'}
        }
        if($scope.e_typeid==4){
            $scope.essaydiaryCondition={e_resources_mode:'2'}
        }
        if($scope.e_typeid==5){
            $scope.essaydiaryCondition={e_fine_paste:'1'}
        }
        $http({
            method:'post',
            url:'/essaydiary/getTheEssayDiaryByManager',
            data:$.param($scope.essaydiaryCondition),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                //响应成功的处理方法体
                if(resq.data.success){
                    //数据源
                    $scope.data = resq.data.data;
                    type="essaydiary";
                    $scope.tableFunction($scope.data,type);
                }else{
                    alert(resq.data.msg);
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            }
        );
    }
    //初始化值
    $scope.getTheEssaydiary=function () {
        $http.post('/essaydiary/getTheEssayDiaryByManager').then(function (response) {
            //数据源
            if(response.data.success) {
                type = "essaydiary";
                $scope.data = response.data.data;
                $scope.tableFunction($scope.data, type);
            }else{
                alert(response.data.msg);
            }
        });
    }
    //设置为推荐
    $scope.SettingUpEssaydiaryRecommend=function (e_id) {
        url='/essaydiary/updateTheAEssayDiaryByManager';
        condition={e_id:e_id,e_fine_paste:1};
        $scope.updateCommon(condition,url);
    }
    //取消设置推荐
    $scope.cancelUpEssaydiaryRecommend=function (e_id) {
        url='/essaydiary/updateTheAEssayDiaryByManager';
        condition={e_id:e_id,e_fine_paste:0};
        $scope.updateCommon(condition,url);
    }
    //删除笔记
    $scope.deleteEssaydiary=function (e_id) {
        url='/essaydiary/deleteTheEssayDiaryByManager';
        condition={e_id:e_id};
        $scope.updateCommon(condition,url);
    }
    /**====end====*/

    /**行业资讯===begin*/
    $scope.isIndustrySearch=false;
    //获取列表值
    $scope.getTheIndustrySelect=function () {
        $scope.i_typeid=$scope.i_typeid;
        if($scope.i_typeid==1){
            $scope.isIndustrySearch=true;
        }else{
            $scope.isIndustrySearch=false;
        }
    }

    //根据行业资讯条件查询值
    $scope.submitIndustryManagement = function () {
        var url = '/industryInfomation/selectIndustryInfoByPageList';
        var reSearch = function(fytpe,types) {
            if($scope.i_typeid==1){
                $scope.postData={
                    pageNumber: parseInt($scope.paginationConf.currentPage),
                    pageSize:  parseInt($scope.paginationConf.itemsPerPage),
                    i_title:$scope.searchIndustryText
                }
            }
            if($scope.i_typeid==2){
                $scope.postData={
                    pageNumber: parseInt($scope.paginationConf.currentPage),
                    pageSize:  parseInt($scope.paginationConf.itemsPerPage),
                    i_resources_mode:'1'}
            }
            if($scope.i_typeid==3){
                $scope.postData={
                    pageNumber: parseInt($scope.paginationConf.currentPage),
                    pageSize:  parseInt($scope.paginationConf.itemsPerPage),
                    i_resources_mode:'2'}
            }
            if($scope.i_typeid==4){
                $scope.postData={
                    pageNumber: parseInt($scope.paginationConf.currentPage),
                    pageSize:  parseInt($scope.paginationConf.itemsPerPage),
                    i_fine_paste:'1'}
            }
            var postData = {
                //发送给后台的请求数据
                pageNumber: parseInt($scope.paginationConf.currentPage),
                pageSize:  parseInt($scope.paginationConf.itemsPerPage)
            };
            $http({
                method:'POST',
                url:url,
                data:$.param( $scope.postData),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(
                function success(response){
                    console.log(response);
                    if(response.data.success){
                        $scope.paginationConf.totalItems = response.data.data.total; //总条数
                        $scope.items_Industry = response.data.data.rows;
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
            itemsPerPage:10,// 每页展示的数据条数
            pagesLength:5,//分页条目的长度
            //isSelectPage : true,
            //perPageOptions: [5, 10, 20] //可选择显示条数的数组
        };
        //当页码和页面记录数发生变化时监控后台查询如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', reSearch);
    }
    //初始化值
    $scope.getTheIndustry=function () {
        var url = '/industryInfomation/selectIndustryInfoByPageList';
        var reSearch = function(fytpe,types) {
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
                    console.log(response);
                    if(response.data.success){
                        $scope.paginationConf.totalItems = response.data.data.total; //总条数
                        $scope.items_Industry = response.data.data.rows;
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
            itemsPerPage:10,// 每页展示的数据条数
            pagesLength:5,//分页条目的长度
            //isSelectPage : true,
            //perPageOptions: [5, 10, 20] //可选择显示条数的数组
        };
        //当页码和页面记录数发生变化时监控后台查询如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。
        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', reSearch);
    }
    //设置为推荐
    $scope.SettingUpIndustryRecommend=function (i_id) {
        url='/industryInfomation/updateIndustryInfoByManager';
        condition={i_id:i_id,i_fine_paste:1};
        $scope.updateCommon(condition,url);
    }
    //取消设置推荐
    $scope.cancelUpIndustryRecommend=function (i_id) {
        url='/industryInfomation/updateIndustryInfoByManager';
        condition={i_id:i_id,i_fine_paste:0};
        $scope.updateCommon(condition,url);
    }
    //删除笔记
    $scope.deleteIndustry=function (i_id) {
        url='/industryInfomation/deleteIndustryInfoByManager';
        condition={i_id:i_id};
        $scope.updateCommon(condition,url);
    }
    /**行业资讯===end*/

    /**技术文章begin*/
    //获取游记下拉列表的选择值
    $scope.isSearch=false;
    $scope.isChooseType=false;
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
                $scope.ListArticleData = response.data.data;
            }else{
                layer.alert(response.msg);
            }

            // window.location.reload();
        },function error(resq){
            //响应错误的处理方法体
            console.log(resq);
        });
    //获取列表值
    $scope.getTheArticleSelect=function () {
        $scope.a_typeid=$scope.a_typeid;
        if($scope.a_typeid==1){
            $scope.isSearch=false;
            $scope.isChooseType=true;
        }else if($scope.a_typeid==2){
            $scope.isSearch=true;
            $scope.isChooseType=false;
        }else{
            $scope.isSearch=false;
            $scope.isChooseType=false;
        }
    }
    //获取类型值
    $scope.getChange=function () {
        $scope.m_typeid=$scope.m_typeid;
    }
    //根据技术文章条件查询值
    $scope.submitArticleManagement = function () {
        $scope.ArticleCondition={};
        if($scope.a_typeid==1){
            $scope.ArticleCondition={m_typeid:$scope.m_typeid}
        }
        if($scope.a_typeid==2){
            $scope.ArticleCondition={m_title:$scope.searchArticleText}
        }
        if($scope.a_typeid==3){
            $scope.ArticleCondition={m_resources_mode:'1'}
        }
        if($scope.a_typeid==4){
            $scope.ArticleCondition={m_resources_mode:'2'}
        }
        if($scope.a_typeid==5){
            $scope.ArticleCondition={m_fine_paste:'1'}
        }
        $http({
            method:'post',
            url:'/article/getTheArticleByManager',
            data:$.param($scope.ArticleCondition),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                //响应成功的处理方法体
                if(resq.data.success){
                    //数据源
                    $scope.data = resq.data.data;
                    type="article";
                    $scope.tableFunction($scope.data,type);
                }else{
                    alert(resq.data.msg);
                }
            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            }
        );
    }
    //初始化数据
    $scope.getTheArticle=function () {
        $http.post('/article/getTheArticleByManager').then(function (response) {
            //数据源
            if(response.data.success) {
                type = "article";
                $scope.data = response.data.data;
                $scope.tableFunction($scope.data, type);
            }else{
                alert(response.data.msg);
            }
        });
    }
    //设置为推荐
    $scope.SettingUpRecommend=function (m_id) {
        url='/article/updateTheArticleByManager';
        condition={m_id:m_id,m_fine_paste:'1'};
        $scope.updateCommon(condition,url);
    }
    //取消设置推荐
    $scope.cancelUpRecommend=function (m_id) {
        url='/article/updateTheArticleByManager';
        condition={m_id:m_id,m_fine_paste:'2'};
        $scope.updateCommon(condition,url);
    }
    //删除推荐
    $scope.deleteArticle=function (m_id) {
        url='/article/deleteTheArticleByManager';
        condition={m_id:m_id};
        $scope.updateCommon(condition,url);
    }
    /**end*/

    /**系统设置===begin*/
    //初始化数据
    $scope.getTheSystem=function () {
        $http({ url: '/index/navByManager', method: 'POST'}).then(function ( response ){
            //数据源
            if(response.data.success) {
                type = "system";
                $scope.data = response.data.data;
                $scope.tableFunction($scope.data, type);
            }else{
                alert(response.data.msg);
            }
        });
    }
    //启用模块
    $scope.SystemEnable =function (n_id) {
        url='/index/updateNavigationByManager';
        condition={n_id:n_id,n_status:1};
        $scope.updateCommon(condition,url);
    }
    //禁模块
    $scope.SystemProhibit =function (n_id) {
        url='/index/updateNavigationByManager';
        condition={n_id:n_id,n_status:0};
        $scope.updateCommon(condition,url);
    }
    /**end*/

    /**公共方法区begin*/
    //表格数据公共方法
    $scope.tableFunction=function(data,type){
        //分页总数
        $scope.data_table=data;
        $scope.pageSize = 20;
        $scope.pages = Math.ceil($scope.data_table.length / $scope.pageSize); //分页数
        $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
        $scope.pageList = [];
        $scope.selPage = 1;
        $scope.showRefresh=true;                 //是否显示刷新按钮
        //设置表格数据源(分页)
        if(type=="user") {
            $scope.setData = function () {
                $scope.items = $scope.data_table.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
            }
            $scope.items = $scope.data_table.slice(0, $scope.pageSize);
        }
        if(type=="donkeytrip") {
            $scope.setData = function () {
                $scope.items_Trip = $scope.data_table.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
            }
            $scope.items_Trip = $scope.data_table.slice(0, $scope.pageSize);
        }
        if(type=="article") {
            $scope.setData = function () {
                $scope.items_Article = $scope.data_table.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
            }
            $scope.items_Article = $scope.data_table.slice(0, $scope.pageSize);
        }
        if(type=="essaydiary"){
            $scope.setData = function () {
                $scope.items_essaydiary = $scope.data_table.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
            }
            $scope.items_essaydiary = $scope.data_table.slice(0, $scope.pageSize);
        }
        if(type=="system"){
            $scope.setData = function () {
                $scope.items_nav = $scope.data_table.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
            }
            $scope.items_nav = $scope.data_table.slice(0, $scope.pageSize);
        }
        //分页要repeat的数组
        for (var i = 0; i < $scope.newPages; i++) {
            $scope.pageList.push(i + 1);
        }
        //打印当前选中页索引
        $scope.selectPage = function (page) {
            //不能小于1大于最大
            if (page < 1 || page > $scope.pages) return;
            //最多显示分页数5
            if (page > 2) {
                //因为只显示5个页数，大于2页开始分页转换
                var newpageList = [];
                for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
                    newpageList.push(i + 1);
                }
                $scope.pageList = newpageList;
            }
            $scope.selPage = page;
            $scope.setData();
            $scope.isActivePage(page);
        };
        //设置当前选中页样式
        $scope.isActivePage = function (page) {
            return $scope.selPage == page;
        };
        //上一页
        $scope.Previous = function () {
            $scope.selectPage($scope.selPage - 1);
        }
        //下一页
        $scope.Next = function () {
            $scope.selectPage($scope.selPage + 1);
        };
        //首页
        $scope.PreviousFirst=function () {
            $scope.selectPage(1);
        }
        //最后一页
        $scope.PreviousLast=function () {
            $scope.selectPage($scope.pages);

        }
    }
    //修改公共方法
    $scope.updateCommon=function(condition,url){
        $http({
            method:'post',
            url:url,
            data:$.param(condition),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(
            function success(resq){
                //响应成功的处理方法体
                if(resq.data.success){
                    window.location.reload();
                }else{
                    alert(resq.data.msg);
                }

            },function error(resq){
                //响应错误的处理方法体
                console.log(resq);
            }
        );
    }
    /**end*/
}]);