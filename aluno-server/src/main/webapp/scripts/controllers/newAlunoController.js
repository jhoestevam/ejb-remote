
angular.module('aluno-server').controller('NewAlunoController', function ($scope, $location, locationParser, flash, AlunoResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.aluno = $scope.aluno || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The aluno was created successfully.'});
            $location.path('/Alunos');
        };
        var errorCallback = function(response) {
            if(response && response.data) {
                flash.setMessage({'type': 'error', 'text': response.data.message || response.data}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        AlunoResource.save($scope.aluno, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Alunos");
    };
});