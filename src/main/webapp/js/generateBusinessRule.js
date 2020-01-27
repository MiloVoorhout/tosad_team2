function generateRule() {
    let selectedKey = $('.list-group-item.list-group-item-action.active').attr("data-id");
    let jsonArray = JSON.parse(sessionStorage.getItem(selectedKey));

    $.get("generate/generate?id="+jsonArray["id"], function(array){
        let code = array[0]["code"];
        $('#modal_code').html(code);
        $('#generate_modal').modal('toggle');
    });

    $.get("generate/newBusinessRule/create" +
        "?rule_type_select=" + ruleType.val() +
        "&rule_name=" + ruleName.val() +
        "&tableSelect=" + tableSelect.val() +
        "&attributeSelect=" + attributeSelect.val() +
        "&operator=" + operatorSelectRange.val() +
        "&minimumValue=" + minimumValue.val() +
        "&maximumValue=" + maximumValue.val() +
        "&validationFailureSeverity=" + validationFailureSeverity.val() +
        "&failureMessage=" + failureMessage.val(), function () {
        if (e) {
            createAlert('success', 'Business Rule successfully created', false, true);
            resetForm();
        }
    });
}