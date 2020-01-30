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

function getAttr(value, name) {
    $.get("define/GetTableInfo/GetAttributes?table="+value, function(array){
        $.each( array, function( i, val ) {
            attribute_select.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>');
        });
        attribute_select.val(name);
    });
}


$.get("generate/getBusinessRules/getContent?id="+id, function (array) {
    $.each(array, function (i, val) {

        rule_name.val(val['name']);

        if (val['businessRuleType'] === 'Attribute Range Rule') {

            $.get("generate/getBusinessRules/getValues?id="+id, function (array) {
                $.each(array, function (i, val) {
                    if (val['type'] === 'Minimum value') minimum_value.val(val['value']);
                    else if (val['type'] === 'Maximum value') maximum_value.val(val['value']);
                });
            });

            getAttr(val['attributeTable'], val['attributeName']);
            operatorSelectRange.val(val['operatorID']);
            operatorSelectContainer.show();
            operatorSelectRange.show();
            range.show();
            minimum_value.show();
            minimum_value.prop('required', true);
            maximum_value.show();
            maximum_value.prop('required', true);

        } else if (val['businessRuleType'] === 'Attribute Compare Rule') {
            operatorSelectCompare.show();

            $.get("generate/getBusinessRules/getValues?id="+id, function (array) {
                $.each(array, function (i, val2) {
                    alert(val['compareStatus']);
                    if (val['compareStatus'] === 2) {
                        literalValue.show();
                        literalValueTextarea.val(val2['value']);
                    } else if (val['compareStatus'] === '4') {
                        attributeEntitySelect.show();
                    } else {
                        interEntity.show();
                    }
                });
            });

        } else if (val['businessRuleType'] === 'Attribute List Rule') {

        } else if (val['businessRuleType'] === 'Inter-Entity Compare Rule') {

        } else if (val['businessRuleType'] === 'Tuple Compare Rule') {

        } else if (val['businessRuleType'] === 'Entity Other Rule') {

        } else {
            alert("Error");
        }
    });
});

form.submit(function (e) {
    e.preventDefault();

    $.get("define/BusinessRule/modify" +
        "?ruleId=" + id +
        "&rule_name=" + rule_name.val() +
        "&attributeSelect=" + attribute_select.val() +
        "&operator=" + $("#operator_select").val() +
        "&minimumValue=" + minimum_value.val() +
        "&maximumValue=" + maximum_value.val()
        , function () {
            createAlert('success', 'Current business rule has been updated', false, true);
        });
});