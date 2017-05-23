angular.module('aluno-server').factory('AlunoResource', function($resource){
    var resource = $resource('rest/alunos/:AlunoId',{AlunoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});