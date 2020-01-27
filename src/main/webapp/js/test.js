const listTab = $('#list-tab');
const content = $('#nav-tabContent');

const ruleType = $('#ruleType');
const ruleTypeName = $('#ruleTypeName');
const ruleTypeTable = $('#ruleTypeTable');
const ruleTypeAttr = $('#ruleTypeAttr');
const ruleTypeOperator = $('#ruleTypeOperator');
const ruleTypeFailureType = $('#ruleTypeFailureType');
const ruleTypeFailureMessage = $('#ruleTypeFailureMessage');
let tempValue;

function getContent(id){
    $.get("generate/getBusinessRules/getContent?id="+id, function (array) {
        $.each(array, function (i, val) {
            ruleType.html(val['businessRuleType']);
            ruleTypeName.html(val['name']);
            ruleTypeAttr.html(val['attributeName']);
            ruleTypeTable.html(val['attributeTable']);
            ruleTypeOperator.html(val['operator']);
            ruleTypeFailureType.html(val['failureType']);
            ruleTypeFailureMessage.html(val['failureMessage']);
        });
    });
}

$.get("generate/getBusinessRules/getMenuItems", function (array) {
    $.each(array, function (i, val) {
        if (i === 0) {
            listTab.append('<a class="list-group-item list-group-item-action listItem active" data-id="'+val['id']+'" id="list-'+val['id']+'-list" data-toggle="list" href="#list-'+val['id']+'" role="tab" aria-controls="list-'+val['id']+'" aria-selected="true">'+val['name']+'</a>');
            getContent(val['id']);
        }
        else listTab.append('<a class="list-group-item list-group-item-action listItem" data-id="'+val['id']+'" id="list-'+val['id']+'-list" data-toggle="list" href="#list-'+val['id']+'" role="tab" aria-controls="list-'+val['id']+'">'+val['name']+'</a>');
    });
});

$( "a" ).click(function() {
    console.log("HALLO");

    var id = $(this).attr("data-id");
    alert(id);
    getContent(id)
});

$( "a" ).on( "click", function() {
    alert( $( this ).text() );
});