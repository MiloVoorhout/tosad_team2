const rule_type_select = $('#rule_type_select');
const compareWith = $('#compare_with');
const operator = $('#operator_select');
const operatorCompare = $("#operator_select_compare");
const compare_with_select = $("#compare_with_select");
const operator_select_list = $("#operator_select_list");
const range = $("#range");
const literalValue = $("#literalValue");
const entityAttribute = $("#entityAttribute");
const properties = $("#properties");
const list = $("#list");
const listValue = $('#newListItemValue');
const listItems = $('#listValues');;
const tableSelect = $('#table_select');
const attributeSelect = $('#attribute_select');
const selectEntityAttribute = $('#select_entity_attribute');
const clearAllListBtn = $('#clearAllList');

properties.hide();

var arrayObj = [compareWith, operator, operatorCompare, compare_with_select, operator_select_list, range,
    literalValue, entityAttribute, list, listValue, listItems];

function hideAllObj(){
    arrayObj.forEach(function(item) { item.hide(); });
    properties.show();
}

rule_type_select.on('change', function (e) {
    setAttributes();
    if (this.value === '1') {
        hideAllObj();
        operator.show();
        range.show();
    }
    else if (this.value === '2') {
        hideAllObj();
        compareWith.show();
        compare_with_select.show();
        operatorCompare.show();
        literalValue.show();
    }
    else if (this.value === '3') {
        hideAllObj();
        compare_with_select.show();
        list.show();
        listItems.show();
        listValue.show();
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

listValue.keypress(function(event){
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
    $.get("rest/getAllTables", function (array) {
        $.each(array, function (i, val) {
            tableSelect.append('<option value="' + val['name'] + '">' + val['name'] + '</option>')
        });
    });
}

function setAttributes() {
    $.get("rest/getAttributes?table="+tableSelect.val(), function(array){
        attributeSelect.empty();
        selectEntityAttribute.empty();
        $.each( array, function( i, val ) {
            attributeSelect.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>')
            selectEntityAttribute.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>')
        });
    });
}

tableSelect.on('change', function(e) {
    setAttributes();
});

getTables();

$.post("rest/createNewBusinessRule", function (data) {
    
});