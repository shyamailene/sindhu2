(function() {
    'use strict';

    angular
        .module('sindhu2App')
        .controller('OwnerdetailsDetailController', OwnerdetailsDetailController);

    OwnerdetailsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Ownerdetails', 'Flat'];

    function OwnerdetailsDetailController($scope, $rootScope, $stateParams, previousState, entity, Ownerdetails, Flat) {
        var vm = this;

        vm.ownerdetails = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sindhu2App:ownerdetailsUpdate', function(event, result) {
            vm.ownerdetails = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
