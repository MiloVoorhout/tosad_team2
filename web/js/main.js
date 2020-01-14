let rule_type_select = $('#rule_type_select');
let compareWith = $('#compare_with');
let operator = $('#operator_select');
let operatorCompare = $("#operator_select_compare");
let compare_with_select = $("#compare_with_select");
let range = $("#range");
let literalValue = $("#literalValue");
let entityAttribute = $("#entityAttribute");
let properties = $("#properties");

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
    }
    else {
        compareWith.show();
        operator.hide();
        operatorCompare.show();
        range.hide();
        literalValue.show();
        entityAttribute.hide();
        properties.show();
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