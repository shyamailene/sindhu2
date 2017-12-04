(function() {
    'use strict';

    angular
        .module('sindhu2App')
        .controller('TracController', TracController);

    TracController.$inject = ['Trac'];

    function TracController(Trac) {

        var vm = this;

        vm.tracs = [];

        loadAll();

        function loadAll() {
            Trac.query(function(result) {
                vm.tracs = result;
                vm.searchQuery = null;
            });
        }
    }
})();
