var app = angular.module('myApp',[]);

app.controller('indexController',function ($scope, $http){

    $scope.open = function(){
        window.location.href = "/feed.html";
    }

    $scope.login = function(){
        window.location.href = "/login";
    }

    $scope.register = function(){
        window.location.href = "/register.html";
    }

})

app.controller('LoginController',function($scope,$http){
    $scope.formLogin = {};

    $scope.connexion = function(){
        var login = document.getElementById("login");
        var pwd = document.getElementById("password");
        if(login.value == ""){
            login.style.borderColor = "#FF0000";
        }
        if(pwd.value == ""){
            pwd.style.borderColor = "#FF0000";
        }
        if(login.value != "" && pwd.value != "" ){
            login.style.borderColor = null;
            pwd.style.borderColor = null;
            $http.post('/connexion',$scope.formLogin).then(function(resp){
                if(resp.data == false){
                    alert("Identifiants incorrects");
                    login.value = "";
                    pwd.value = "";
                } else {
                    $scope.formLogin = {};
                    window.location.href = "./feed.html";
                    alert("Connexion réussie");
                }
            });
        }
    };
})

app.controller('InscriptionController',function ($scope, $http){

    $scope.createUser = function(){
        var login = document.getElementById("login");
        var pwd = document.getElementById("password");
        if(login.value == ""){
            login.style.borderColor = "#FF0000";
        }
        if(pwd.value == ""){
            pwd.style.borderColor = "#FF0000";
        }
        if(login.value != "" && pwd.value != "" ){
            login.style.borderColor = null;
            pwd.style.borderColor = null;
            $http.get('/checkUsers/'+login.value).then(function(resp){
                if(resp.data != null) {
                    login.style.borderColor = "#FF0000";
                    alert("Identifiant déjà utilisé. Veuillez en choisir un autre");
                    login.value = "";
                    pwd.value = "";
                } else {
                    $http.post('/inscription', $scope.formLogin).then(function (resp) {
                        $scope.formLogin = {};
                        window.location.href = "/feed.html";
                        alert("Inscription réussie");
                    });
                }
            });
        }
    };

})

app.controller('MainController',function ($scope, $http){
    $http.get('/feed').then(function(resp){
        $scope.tasks = resp.data;
    });

    $scope.share = function(){
        $http.post('/task',$scope.task).then(function(resp){
            $scope.task= "";
            window.location.reload();
        });
    }

    $scope.comment = function(id){
        $http.post('/comment/'+id,document.getElementById("commentaireStory"+id).value).then(function(resp){
            $scope.commentaire= "";
            window.location.reload();
        });
    }

    $scope.deleteTask = function(id){
        $http.delete("/deleteTask/"+id).then(function(resp){
            window.location.reload();
        })
    }

})