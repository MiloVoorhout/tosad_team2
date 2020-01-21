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
const listItems = $('#listValues');
const listActionsContainer = $('#listActions');

rule_type_select.val(0);
properties.hide();

var arrayObj = [compareWith, operator, operatorCompare, compare_with_select, operator_select_list, range, literalValue, entityAttribute, list, listValue, listItems];

function hideAllObj(){
    arrayObj.forEach(function(item) { item.hide(); });
    properties.show();
}

rule_type_select.on('change', function (e) {
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

listItems.on('change', function (e) {
    alert(listItems.val());

    if (listItems.val()) {
        listActionsContainer.empty();
        listActionsContainer.append('<button type="button" class="btn btn-danger btn-sm" id="clearAllList">Clear all</button>');
        listActionsContainer.append('<button type="button" class="btn btn-danger btn-sm" id="clearAllList">Delete selected</button>');
    } else {
        listActionsContainer.empty();
        listActionsContainer.append('<button type="button" class="btn btn-danger btn-sm" id="clearAllList">Clear all</button>');
    }
});