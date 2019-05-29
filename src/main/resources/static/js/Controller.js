var app = angular.module('myApp',[]);

app.controller('indexController',function ($scope, $http){

    $scope.open = function(){
        window.location.href = "/feed.html";
    }

})

app.controller('LoginController',function($scope,$http){

    var url = window.location.href.split("/");
    if(url[3] == "login.html?error"){
        $scope.warning = "Identifiants incorrects";
    }
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
            var req = {
                username : username,
                password : password
            };

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

        $http.get('/user').then(function (resp) {
            $scope.user = resp.data;
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
        var content = document.getElementById("nomTache" + id);
        content.style.display = "none";
        btnEdit.style.display = "none";
        text.value = content.innerText.trim();
        text.style.display = "inline";
        btnValid.style.display = "inline";
    };

    $scope.update = function (id) {
        var text = document.getElementById("text" + id);
        var btnValid = document.getElementById("btn" + id);
        var btnEdit = document.getElementById("modif" + id);
        var content = document.getElementById("nomTache" + id);
        content.style.display = "inline";
        btnEdit.style.display = "inline";
        text.style.display = "none";
        btnValid.style.display = "none";
            $http.put('/updateTask', {id: id, content: text.value}).then(function (resp) {
                $scope.modif = "";
                $scope.refresh();
            });
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