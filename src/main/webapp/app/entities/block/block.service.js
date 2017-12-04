(function() {
    'use strict';
    angular
        .module('sindhu2App')
        .factory('Block', Block);

    Block.$inject = ['$resource'];

    function Block ($resource) {
        var resourceUrl =  'api/blocks/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
