const ruleName = $('#rule_name');
const ruleType = $('#rule_type_select');
const properties = $("#properties");
const tableSelect = $('#table_select');
const attributeSelect = $('#attribute_select');
const compareWith = $('#compare_with');
const operatorSelectRange = $('#operator_select');
const operatorSelectCompare = $("#operator_select_compare");
const operatorSelectList = $("#operator_select_list");
const compareWithSelect = $("#compare_with_select");
const range = $("#range");
const literalValue = $("#literalValue");
const entityAttribute = $("#entityAttribute");
const list = $("#list");
const listInputValue = $('#newListItemValue');
const listItems = $('#listValues');;
const clearAllListBtn = $('#clearAllList');
const minimumValue = $('#minimum_value');
const maximumValue = $('#maximum_value');
const literalValueTextarea = $('#literalValueTextarea');
const attributeEntitySelect = $('#attributeEntitySelect');
const validationFailureSeverity = $('#validation_failure_severity');
const failureMessage = $('#failure_message');
const alertContainer = $('#alertContainer');

// hide all options
properties.hide();

// array of all components
var arrayObj = [compareWith, operatorSelectRange, operatorSelectCompare, compareWithSelect, operatorSelectList, range, minimumValue, maximumValue,
    literalValue, entityAttribute, list, listInputValue, listItems];

// array of all inputs
var arrayInputs = [tableSelect, attributeSelect, minimumValue, maximumValue, compareWithSelect,
    literalValueTextarea, attributeEntitySelect, listInputValue, listItems, operatorSelectRange, operatorSelectCompare, operatorSelectList];

function getRuleTypes() {
    $.get("rest/GetRuleTypes", function (data) {
        $.each(data, function (i, val) {
            ruleType.append('<option value="' + val['id'] + '">' + val['type'] + '</option>')
        });
    });
}

getRuleTypes();

function hideAllObj(){
    arrayObj.forEach(function(item) {
        item.hide();
    });
    arrayInputs.forEach(function(item) {
        item.prop('required', false);
    });
}

ruleType.on('change', function () {
    setAttributes();
    properties.show();
    if (this.value === '1') {
        hideAllObj();
        operatorSelectRange.show();
        range.show();
        minimumValue.prop('required',true);
        minimumValue.show();
        maximumValue.prop('required',true);
        maximumValue.show();
    }
    if (this.value === '2') {
        hideAllObj();
        compareWith.show();
        compareWithSelect.show();
        operatorSelectCompare.show();
        literalValue.show();
        literalValueTextarea.prop('required',true);
    }
    if (this.value === '3') {
        hideAllObj();
        compareWithSelect.show();
        list.show();
        listItems.show();
        listInputValue.show();
        operatorSelectList.show();
        listItems.prop('required', true);
    }
});



compareWithSelect.on('change', function (e) {
    if (this.value === '1') {
        literalValue.show();
        literalValue.prop('required', true);
        entityAttribute.hide();
        entityAttribute.prop('required', false);

    } else {
        entityAttribute.show();
        entityAttribute.prop('required', true);
        literalValue.hide();
        literalValue.prop('required', false);
    }
});

listInputValue.keypress(function(event){
    if (event.keyCode == 10 || event.keyCode == 13)
    {
        event.preventDefault();
        $(this).trigger("enterKey");
        if ($(this).val() !== '') {
            let newValue = $(this).val().replace(/ /g,'');
            listItems.append("<option value='"+ newValue +"'>" + newValue + "</option>");
            $(this).val("");
        }
    }
});


clearAllListBtn.on('click', function (e) {
    listItems.empty();
});

function getTables() {
    $.get("rest/GetTableInfo/getAllTables", function (array) {
        $.each(array, function (i, val) {
            tableSelect.append('<option value="' + val['name'] + '">' + val['name'] + '</option>')
        });
    });
}

function setAttributes() {
    $.get("rest/GetTableInfo/GetAttributes?table="+tableSelect.val(), function(array){
        attributeSelect.empty();
        attributeEntitySelect.empty();
        $.each( array, function( i, val ) {
            attributeSelect.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>')
            attributeEntitySelect.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>')
        });
    });
}

tableSelect.on('change', function(e) {
    setAttributes();
});

getTables();

$("form").submit(function (e) {
    e.preventDefault();

    if(!failureMessage.val()) {
        alertContainer.empty();
        alertContainer.append('<div class="alert alert-danger"><strong>Whoops</strong> Please enter a Failure message </div>');
        $('.alert').delay(3000).fadeOut(400);

    } else {
        if(ruleType.val() === "1") {
            $.get("rest/newBusinessRule/create" +
                "?rule_type_select=" + ruleType.val() +
                "&rule_name=" + ruleName.val() +
                "&tableSelect=" + tableSelect.val() +
                "&attributeSelect=" + attributeSelect.val() +
                "&operator=" + operatorSelectRange.val() +
                "&minimumValue=" + minimumValue.val() +
                "&maximumValue=" + maximumValue.val() +
                "&validationFailureSeverity=" + validationFailureSeverity.val() +
                "&failureMessage=" + failureMessage.val(), function () {
                if (e) createBusinessRuleAlert();
            });
        }

        if(ruleType.val() === '2') {

            let getValueType;

            if(compareWithSelect.val() === '1') {
                getValueType = literalValueTextarea.val();
            } else {
                getValueType = attributeEntitySelect.val();
            }

            $.get("rest/newBusinessRule/create" +
                "?rule_type_select=" + ruleType.val() +
                "&rule_name=" + ruleName.val() +
                "&tableSelect=" + tableSelect.val() +
                "&attributeSelect=" + attributeSelect.val() +
                "&operator=" + operatorSelectRange.val() +
                "&compareWith=" + compareWithSelect.val() +
                "&value=" + getValueType +
                "&validationFailureSeverity=" + validationFailureSeverity.val() +
                "&failureMessage=" + failureMessage.val(), function () {
                if (e) createBusinessRuleAlert();
            });
        }

        if(ruleType.val() === "3") {
            $.get("rest/newBusinessRule/create" +
                "?rule_type_select=" + ruleType.val() +
                "&rule_name=" + ruleName.val() +
                "&tableSelect=" + tableSelect.val() +
                "&attributeSelect=" + attributeSelect.val() +
                "&operator=" + operatorSelectRange.val() +
                "&listValues=" + listItems.val() +
                "&validationFailureSeverity=" + validationFailureSeverity.val() +
                "&failureMessage=" + failureMessage.val(), function () {
                if (e) createBusinessRuleAlert();
            });
        }

    }


});

function createBusinessRuleAlert(){
    alertContainer.append('<div class="alert alert-success"><strong>Success!</strong> Business Rule successfully created</div>');
    $('.alert').delay(3000).fadeOut(400);
    properties.hide();
    $('form').trigger("reset");
    $('.nav-tabs a[href="#rule_definition"]').tab('show')


}