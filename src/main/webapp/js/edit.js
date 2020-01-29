let searchParams = new URLSearchParams(window.location.search);
let id = searchParams.get('id');

const rule_name = $("#rule_name");
const operatorSelectContainer = $("#operator_select_container");
const newListItemValueContainer = $("#newListItemValueContainer");
const listValuesContainer = $("#listValuesContainer");
const range = $("#range");
const literalValue = $("#literalValue");
const operatorSelectRange = $('#operator_select');
const operatorSelectCompare = $("#operator_select_compare");
const operatorSelectList = $("#operator_select_list");
const newListItemValue = $("#newListItemValue");
const listValues = $("#listValues");
const listActions = $("#listActions");
const minimum_value = $("#minimum_value");
const maximum_value = $("#maximum_value");
const literalValueTextarea = $("#literalValueTextarea");
const attributeEntitySelect = $("#attributeEntitySelect");
const interEntity = $("#interEntity");
const interEntityTableSelect = $("#interEntityTableSelect");
const interEntityAttributeSelect = $("#interEntityAttributeSelect");
const entityAttribute = $("#entityAttribute");
const otherValueContainer = $("#otherValueContainer");
const otherValue = $("#otherValue");
const attribute_select = $("#attribute_select");

var arrayObjects = [
    operatorSelectRange,
    operatorSelectCompare,
    operatorSelectList,
    operatorSelectContainer,
    newListItemValueContainer,
    listValuesContainer,
    listActions,
    range,
    literalValue,
    entityAttribute,
    interEntity,
    otherValueContainer
];

var arrayInputs = [
    operatorSelectRange,
    operatorSelectCompare,
    operatorSelectList,
    newListItemValue,
    listValues,
    minimum_value,
    maximum_value,
    literalValueTextarea,
    attributeEntitySelect,
    interEntityTableSelect,
    interEntityAttributeSelect,
    otherValue
];

arrayObjects.forEach(function(item) { item.hide(); });
arrayInputs.forEach(function(item) { item.prop('required', false); });


$.get("generate/getBusinessRules/getContent?id="+id, function (array) {
    $.each(array, function (i, val) {

        alert(val['businessRuleType']);

        if (val['businessRuleType'] === 'Attribute Range Rule') {
            alert('TEST');

            operatorSelectContainer.show();
            operatorSelectRange.show();
            operatorSelectRange.val(val['operatorID']);
            range.show();
            minimum_value.show();
            minimum_value.prop('required',true);
            maximum_value.show();
            maximum_value.prop('required',true);

            $.get("generate/getBusinessRules/getValues?id="+id, function (array) {
                $.each(array, function (i, val) {
                    if (val['type'] === '1') minimum_value.val(val['value']);
                    else if (val['type'] === '2') maximum_value.val(val['value']);
                });
            });

            $.get("define/GetTableInfo/GetAttributes?table="+val['attributeTable'], function(array){
                $.each( array, function( i, val ) {
                    attribute_select.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>');
                });
            });

            attribute_select.val(val['attributeID']);

            rule_name.val(val['name']);

        } else if (val['businessRuleType'] === 'Attribute Compare Rule') {

        } else if (val['businessRuleType'] === 'Attribute List Rule') {

        } else if (val['businessRuleType'] === 'Inter-Entity Compare Rule') {

        } else if (val['businessRuleType'] === 'Tuple Compare Rule') {

        } else if (val['businessRuleType'] === 'Entity Other Rule') {

        } else {
            alert("Bitch get the fuck outta here");
        }

        // ruleType.html(val['businessRuleType']);
        // ruleTypeName.html(val['name']);
        // ruleTypeAttr.html(val['attributeName']);
        // ruleTypeTable.html(val['attributeTable']);
        // ruleTypeOperator.html(val['operator']);
        // ruleTypeFailureType.html(val['failureType']);
        // ruleTypeFailureMessage.html(val['failureMessage']);

    });
});
