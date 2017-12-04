(function() {
    'use strict';

    angular
        .module('sindhu2App')
        .controller('OwnerdetailsController', OwnerdetailsController);

    OwnerdetailsController.$inject = ['Ownerdetails'];

    function OwnerdetailsController(Ownerdetails) {

        var vm = this;

        vm.ownerdetails = [];

        loadAll();

        function loadAll() {
            Ownerdetails.query(function(result) {
                vm.ownerdetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
