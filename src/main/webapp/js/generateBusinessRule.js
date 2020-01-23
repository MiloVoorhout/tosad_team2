function generateRule() {
    let selectedKey = $('.list-group-item.list-group-item-action.active').attr("data-id");
    let jsonArray = JSON.parse(sessionStorage.getItem(selectedKey));

    $.get("rest/generate?array="+jsonArray["id"]+","+jsonArray["name"]+","+jsonArray["comparestatus"]+","+jsonArray["operatorID"]+","+jsonArray["attributeID"]+","+jsonArray["subAttributeID"]+","+jsonArray["businessRuleTypeID"], function(array){
        let code = array[0]["code"];
        $('#modal_code').html(code);
        $('#generate_modal').modal('toggle');
    });
}