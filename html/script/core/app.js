function include(file, isModule) {
    var script = document.createElement('script');
    script.src = file;
    if (isModule) {
        script.type = 'module';
        script.defer = false;
    } else {
        script.defer = true;
    }
    script.type = 'text/javascript';

    document.getElementsByTagName('head').item(0).appendChild(script);
}

function module(file) {
    this.include(file, true);
}
function script(file) {
    this.include(file, false);
}

module('https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js');
module('services/core/ajax-core.js');
module('services/core/base-service.js');
module('script/index.js');

/**
 * Services
 */
module('services/movie-service.js');
/**
 * Components
 */
module('components/movie/movie.js');


function inject(component, destination) {
    $('#mainContainer').load('component/' + component, function () {
        destination.load();
    });
}