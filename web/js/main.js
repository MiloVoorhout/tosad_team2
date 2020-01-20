let rule_type_select = $('#rule_type_select');
let compareWith = $('#compare_with');
let operator = $('#operator_select');
let operatorCompare = $("#operator_select_compare");
let compare_with_select = $("#compare_with_select");
let operator_select_list = $("#operator_select_list");
let range = $("#range");
let literalValue = $("#literalValue");
let entityAttribute = $("#entityAttribute");
let properties = $("#properties");
let list = $("#list");
let listValue = $('#newListItemValue');
let listItems = $('#listValues');

rule_type_select.val(0);
properties.hide();

rule_type_select.on('change', function (e) {
    if (this.value === '1') {
        compareWith.hide();
        operator.show();
        operatorCompare.hide();
        range.show();
        literalValue.hide();
        entityAttribute.hide();
        properties.show();
        list.hide();
        operator_select_list.hide();
    }
    else if (this.value === '2') {
        compareWith.show();
        operator.hide();
        operatorCompare.show();
        range.hide();
        literalValue.show();
        entityAttribute.hide();
        properties.show();
        list.hide();
        operator_select_list.hide();
    }
    else if (this.value === '3') {
        compareWith.hide();
        operator.hide();
        operatorCompare.hide();
        range.hide();
        literalValue.hide();
        entityAttribute.hide();
        properties.show();
        list.show();
        operator_select_list.show();

    }
});

compare_with_select.on('change', function (e) {
    if (this.value === '1') {
        literalValue.show();
        entityAttribute.hide();
    } else {
        literalValue.hide();
        entityAttribute.show();
    }
});

listValue.bind("enterKey",function(e){
    if ($(this).val() !== '') {
        let newValue = $(this).val().replace(/ /g,'');
        listItems.append("<option value='"+ newValue +"'>" + newValue + "</option>");
        $(this).val("");
    }
});
listValue.keyup(function(e){
    if(e.keyCode == 13)
    {
        $(this).trigger("enterKey");
    }
});