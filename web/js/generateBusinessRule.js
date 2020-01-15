function generateRule() {
    let selectedKey = $('.list-group-item.list-group-item-action.active').html();
    let jsonArray = JSON.parse(sessionStorage.getItem(selectedKey));

    let code = '<a>CREATE OR REPLACE TRIGGER ' + jsonArray['name'] + '<br>' +
    'BEFORE INSERT ON ' + jsonArray['tabel'] + ' FOR EACH ROW<br>' +
    'BEGIN<br>' +
        '&emsp;:new.' + jsonArray['attribute'] + ' ' + jsonArray['operator'] + ' ' + jsonArray['minVal'] + ' AND ' + jsonArray['maxVal'] + '<br>' +
    'END;</a>';

    $('#modal_code').html(code);
    $('#generate_modal').modal('toggle');
}