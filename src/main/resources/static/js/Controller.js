var app = angular.module('myApp',[]);

app.controller('indexController',function ($scope, $http){

    $scope.open = function(){
        window.location.href = "/feed.html";
    }

    $scope.login = function(){
        window.location.href = "/login.html";
    }

    $scope.register = function(){
        window.location.href = "/register.html";
    }

})

app.controller('LoginController',function($scope,$http){
/*
    $scope.connexion = function(){
        var username = $scope.username;
        var password = $scope.password;
        $scope.warning ="";

        if(typeof username === "undefined"){
            document.getElementById("username").style.borderColor = "#FF0000";
        }
        else if(typeof password === "undefined"){
            document.getElementById("password").style.borderColor = "#FF0000";
        }
        else {
            var req = {
                username : username,
                password : password
            };
            document.getElementById("username").style.borderColor = null;
            document.getElementById("password").style.borderColor = null;
            console.log(req);
            $http.post('/connexion',req)
                .then(function(resp){
                    if(resp.data == false){
                        $scope.username = "";
                        $scope.password = "";
                        $scope.warning = "Identifiants incorrects";
                    } else {
                        alert("Connexion réussie");
                        window.location.href = "/feed.html";
                    }
            });
        }
    };*/
})

app.controller('InscriptionController',function ($scope, $http){

    $scope.createUser = function(){

        var username = $scope.username;
        var password = $scope.password;
        var password2 = $scope.password2;

        if(password2 != password) {
            $scope.warning = "Les 2 mots de passe ne sont pas identiques";
            return;
        }

        if(typeof username === "undefined"){
            document.getElementById("username").style.borderColor = "#FF0000";
        }
        else if(typeof password === "undefined"){
            document.getElementById("password").style.borderColor = "#FF0000";
        }
        else if(typeof password2 === "undefined"){
            document.getElementById("password2").style.borderColor = "#FF0000";
        }
        else {
            var req = {
                username : username,
                password : password
            };

            document.getElementById("username").style.borderColor = null;
            document.getElementById("password").style.borderColor = null;
            document.getElementById("password2").style.borderColor = null;
            $http.post("/inscription", req)
                .then(function (resp) {
                    console.log(resp);
                    location.href = "/feed.html";
                }).catch(function(e) {
                    if(e.status == 500){
                        $scope.warning = "Identifiant déjà utilisé! Veuillez en choisir un autre";
                    }
                }).then(function(e) {
                    $scope.username = "";
                });

        }
    };

})

app.controller('MainController',function ($scope, $http) {

        /*$http.get('/feed').then(function (resp) {
        $scope.tasks = resp.data;
    });*/

    $scope.refresh = function(){
        $http.get('/feed').then(function (resp) {
            $scope.tasks = resp.data;
        });
    };

    $scope.refresh();

    $scope.logout = function(){
        window.location.href = "/logout";
    }

    $scope.createTask = function () {
        $http.post('/task', $scope.content).then(function (resp) {
            $scope.content = "";
            $scope.refresh();
        });
    };

    $scope.openEdit = function (id) {
        var text = document.getElementById("text" + id);
        var btnValid = document.getElementById("btn" + id);
        var btnEdit = document.getElementById("modif" + id);
        btnEdit.style.display = "none";
        text.value = "";
        text.style.display = "inline";
        btnValid.style.display = "inline";
    };

    $scope.update = function (id) {
        var text = document.getElementById("text" + id);
        var btnValid = document.getElementById("btn" + id);
        var btnEdit = document.getElementById("modif" + id);
        btnEdit.style.display = "inline";
        text.style.display = "none";
        btnValid.style.display = "none";
        if (text.value == "") {

        } else {
            $http.put('/updateTask', {id: id, content: text.value}).then(function (resp) {
                $scope.modif = "";
                $scope.refresh();
            });
        }
    }

    $scope.switch = function (id, done) {
        $http.put('/updateCB', {id: id, done: done}).then(function (resp) {
            $scope.refresh();
        });
    }

    $scope.deleteTask = function(id){
        $http.delete("/deleteTask/"+id).then(function(resp){
            $scope.refresh();
        })
    }

})