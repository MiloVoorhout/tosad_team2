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
const listItems = $('#listValues');
const clearAllListBtn = $('#clearAllList');
const minimumValue = $('#minimum_value');
const maximumValue = $('#maximum_value');
const literalValueTextarea = $('#literalValueTextarea');
const attributeEntitySelect = $('#attributeEntitySelect');
const validationFailureSeverity = $('#validation_failure_severity');
const failureMessage = $('#failure_message');
const alertContainer = $('#alertContainer');
const interEntity = $('#interEntity');
const interEntityTableSelect = $('#interEntityTableSelect');
const interEntityAttributeSelect = $('#interEntityAttributeSelect');
const form = $('form');
let temporaryValue;
let getValueType;
var arrayObj = [
    compareWith,
    operatorSelectRange,
    operatorSelectCompare,
    compareWithSelect,
    operatorSelectList,
    range,
    minimumValue,
    maximumValue,
    literalValue,
    entityAttribute,
    list,
    listInputValue,
    listItems,
    interEntity
];

var arrayInputs = [
    tableSelect,
    attributeSelect,
    minimumValue,
    maximumValue,
    compareWithSelect,
    literalValueTextarea,
    attributeEntitySelect,
    listInputValue,
    listItems,
    operatorSelectRange,
    operatorSelectCompare,
    operatorSelectList,
    interEntityAttributeSelect
];

properties.hide();
getRuleTypes();
getTables();
clearAllListBtn.on('click', function () {listItems.empty();});
tableSelect.on('change', function() { setAttributes(false);});
interEntityTableSelect.on('change', function() { setAttributes(true);});

function getRuleTypes() {
    $.get("define/GetRuleTypes", function (data) {
        $.each(data, function (i, val) {
            ruleType.append('<option value="' + val['id'] + '">' + val['type'] + '</option>');
        });
    });
}

function hideAllObj(){
    arrayObj.forEach(function(item) {item.hide();});
    arrayInputs.forEach(function(item) {item.prop('required', false);});
}

function resetForm(){
    properties.hide();
    form.trigger("reset");
    $('.nav-tabs a[href="#rule_definition"]').tab('show');
}

function createAlert(type, message, tab, clean){
    if (clean) alertContainer.empty();
    if (tab) $('.nav-tabs a[href="#'+tab+'"]').tab('show');

    alertContainer.append('<div class="alert alert-'+ type +'">' + message + '</div>');
    $('.alert').delay(3000).fadeOut(400);
}

function getTables() {
    $.get("define/GetTableInfo/getAllTables", function (array) {
        $.each(array, function (i, val) {
            tableSelect.append('<option value="' + val['name'] + '">' + val['name'] + '</option>');
            interEntityTableSelect.append('<option value="' + val['name'] + '">' + val['name'] + '</option>');
        });
    });
}

function setAttributes(interEntity) {

    if (interEntity) {
        temporaryValue = interEntityTableSelect.val();
        interEntityAttributeSelect.empty();
    } else {
        temporaryValue = tableSelect.val();
        attributeSelect.empty();
        attributeEntitySelect.empty();
    }

    $.get("define/GetTableInfo/GetAttributes?table="+temporaryValue, function(array){
        $.each( array, function( i, val ) {
            if (interEntity) {
                interEntityAttributeSelect.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>');
            } else {
                attributeSelect.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>');
                attributeEntitySelect.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>');
            }
        });
    });
}

ruleType.on('change', function () {
    setAttributes(false);
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
        setAttributes(true);
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

compareWithSelect.on('change', function () {
    if (this.value === '1') {
        literalValue.show();
        literalValueTextarea.prop('required', true);
        entityAttribute.hide();
        attributeEntitySelect.prop('required', false);
        interEntity.hide();
        interEntityAttributeSelect.prop('required', false);
        interEntityTableSelect.prop('required', false);

    } else if (this.value === '2') {
        entityAttribute.show();
        attributeEntitySelect.prop('required', true);
        literalValue.hide();
        literalValueTextarea.prop('required', false);
        interEntity.hide();
        interEntityAttributeSelect.prop('required', false);
        interEntityTableSelect.prop('required', false);

    } else {
        interEntity.show();
        interEntityAttributeSelect.prop('required', true);
        interEntityTableSelect.prop('required', true);
        literalValue.hide();
        literalValueTextarea.prop('required', false);
        entityAttribute.hide();
        attributeEntitySelect.prop('required', false);
    }
});

listInputValue.keypress(function(event){
    if (event.keyCode === 10 || event.keyCode === 13)
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

form.submit(function (e) {
    e.preventDefault();

    if(!failureMessage.val()) {
        createAlert('danger', 'Please enter a failure message', 'failure_handling', true);
    } else {
        if(ruleType.val() === "1") {
            $.get("define/newBusinessRule/create" +
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

        if(ruleType.val() === "2") {

            if (compareWithSelect.val() === "4" && tableSelect.val() === interEntityTableSelect.val()) {
                createAlert('danger', 'Please choose a different table to compare with',
                    'rule_definition', true);
            } else {
                if(compareWithSelect.val() === "1") {
                    getValueType = "&value="+literalValueTextarea.val();
                } else if (compareWithSelect.val() === "2"){
                    getValueType = "&value="+attributeEntitySelect.val();
                } else {
                    getValueType = "&interEntityTable="+interEntityTableSelect.val()+
                        "&value="+interEntityAttributeSelect.val();
                }

                $.get("define/newBusinessRule/create" +
                    "?rule_type_select=" + ruleType.val() +
                    "&rule_name=" + ruleName.val() +
                    "&tableSelect=" + tableSelect.val() +
                    "&attributeSelect=" + attributeSelect.val() +
                    "&operator=" + operatorSelectCompare.val() +
                    "&compareWith=" + compareWithSelect.val() +
                    getValueType +
                    "&validationFailureSeverity=" + validationFailureSeverity.val() +
                    "&failureMessage=" + failureMessage.val(), function () {
                    if (e) {
                        createAlert('success', 'Business Rule successfully created',
                            false, true);
                        resetForm();
                    }
                });
            }
        }

        if(ruleType.val() === "3") {
            $.get("define/newBusinessRule/create" +
                "?rule_type_select=" + ruleType.val() +
                "&rule_name=" + ruleName.val() +
                "&tableSelect=" + tableSelect.val() +
                "&attributeSelect=" + attributeSelect.val() +
                "&operator=" + operatorSelectList.val() +
                "&listValues=" + listItems.val() +
                "&validationFailureSeverity=" + validationFailureSeverity.val() +
                "&failureMessage=" + failureMessage.val(), function () {
                if (e) {
                    createAlert('success', 'Business Rule successfully created', false, true);
                    resetForm();
                }
            });
        }
    }
});