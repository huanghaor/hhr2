/**
 * 该js为所有页面中的公用js:即导航栏，登陆，获取头像，加载个人信息等所有页面都需要的信息
 */

/**主页app*/
var index = angular.module('index',['ngRoute','starter.services']);
index.controller('NavCtrl', function($scope,$http,$location,utilService) {
    utilService.getTheNavList($scope,$http);
});

/**公共函数*/
angular.module('starter.services', [])
    .factory('utilService', function() {
        return {
            /**获取导航栏*/
            getTheNavList:function($scope,$http){
                $http({ url: '/index/nav', method: 'get'}).then(function ( info ){
                    $scope.navLists = info.data;
                });
            }
        }
    })