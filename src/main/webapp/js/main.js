const alertContainer = $('#alertContainer');
const form = $('form');

function createAlert(type, message, tab, clean){
    if (clean) alertContainer.empty();
    if (tab) $('.nav-tabs a[href="#'+tab+'"]').tab('show');

    alertContainer.append('<div class="alert alert-'+ type +'">' + message + '</div>');
    $('.alert').delay(3000).fadeOut(400);
}