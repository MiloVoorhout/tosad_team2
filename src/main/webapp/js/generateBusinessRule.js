function generateRule() {
    let ruleID = $('.list-group-item.list-group-item-action.active').attr("data-id");
    let ruleStatement = $('#statementMethodSelect option:selected').html().toUpperCase();
    let forEachRowStatus = 0;
    let dataBaseType = $('#databaseMethodeSelect option:selected').val();

    if (ruleStatement.slice(ruleStatement.length - 12) == "FOR EACH ROW") forEachRowStatus = 1;

    $.get("generate/generate" +
        "?id=" + ruleID +
        "&statement=" + ruleStatement +
        "&ferStatus=" + forEachRowStatus +
        "&dataBaseType=" + dataBaseType, function (array) {
        let code = array[0]["code"];
        code = code.replace(/(?:\r\n|\r|\n)/g, '<br>');
        $('#modal_code').html(code);
        $('#generate_modal').modal('toggle');
    });
}