function generateRule() {
    let selectedKey = $('.list-group-item.list-group-item-action.active').attr("data-id");
    let jsonArray = JSON.parse(sessionStorage.getItem(selectedKey));
    let ruleSatement = $('#statementMethodSelect option:selected').html();


    $.get("generate/generate" +
        "?id="+jsonArray["id"] +
        "&statement=" + ruleSatement ,function(array){
        let code = array[0]["code"];
        $('#modal_code').html(code);
        $('#generate_modal').modal('toggle');
    });
}