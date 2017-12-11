(function() {
    'use strict';

    angular
        .module('sindhu2App')
        .controller('TracDialogController', TracDialogController);

    TracDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Trac', 'User'];

    function TracDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Trac, User) {
        var vm = this;

        vm.trac = entity;
        vm.clear = clear;
        vm.save = save;
		vm.issues = [
            {issue : "Electrical"},
            {issue : "Carpenter"},
            {issue : "CommonArea"},
            {issue : "Plumbing"},
            {issue : "Security"},
            {issue : "HouseKeeping"},
            {issue : "Other"}
        ];		
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.trac.id !== null) {
                Trac.update(vm.trac, onSaveSuccess, onSaveError);
            } else {
                Trac.save(vm.trac, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sindhu2App:tracUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
