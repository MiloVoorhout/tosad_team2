const listTab = $('#list-tab');
const content = $('#nav-tabContent');

const ruleType = $('#ruleType');
const ruleTypeName = $('#ruleTypeName');
const ruleTypeTable = $('#ruleTypeTable');
const ruleTypeAttr = $('#ruleTypeAttr');
const ruleTypeOperator = $('#ruleTypeOperator');
const ruleTypeFailureType = $('#ruleTypeFailureType');
const ruleTypeFailureMessage = $('#ruleTypeFailureMessage');
const ruleTypeDB = $('#ruleTypeDB');
const ruleTypeSubAttrContainer = $('#ruleTypeSubAttrContainer');
const packageBtn = $('#packageBtn');
const packageValues = $('#packageValues');

const subattrTr = $('#subattrTr');
const ruleTypeSubAttr = $('#ruleTypeSubAttr');
const subattrTableTr = $('#subattrTableTr');
const ruleTypeSubAttrTable = $('#ruleTypeSubAttrTable');

subattrTr.hide();
subattrTableTr.hide();

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
            getValues(id);
        });
    });
}

function getValues(id) {
    $.get("generate/getBusinessRules/getValues?id="+id, function (array) {
        $.each(array, function (i, val) {

            if (i === 0) {
                if (val['subAttribute']) {
                    subattrTr.show();
                    subattrTableTr.show();
                    ruleTypeSubAttr.html = val['subAttribute'];
                    ruleTypeSubAttrTable.html = val['subAttributeTable'];
                } else {
                    subattrTr.hide();
                    subattrTableTr.hide();

                }
                ruleTypeDB.html = val['databaseName'];
            }

        });
    });
}

function getAllRules(){
    $.get("generate/getBusinessRules/getMenuItems", function (array) {
        $.each(array, function (i, val) {
            packageValues.append("<option value='"+ val['id'] +"'>" + val['name'] + "</option>");
        });
    });
}

$.get("generate/getBusinessRules/getMenuItems", function (array) {
    $.each(array, function (i, val) {
        if (i === 0) {
            listTab.append('<button onclick="getContent('+val["id"]+')" class="list-group-item list-group-item-action listItem active" data-id="'+val['id']+'" id="list-'+val['id']+'-list" data-toggle="list" href="#list-'+val['id']+'" role="tab" aria-controls="list-'+val['id']+'" aria-selected="true">'+val['name']+'</button>');
            getContent(val['id']);
        }
        else listTab.append('<button onclick="getContent('+ val["id"] +')" class="list-group-item list-group-item-action listItem" data-id="'+val['id']+'" id="list-'+val['id']+'-list" data-toggle="list" href="#list-'+val['id']+'" role="tab" aria-controls="list-'+val['id']+'">'+val['name']+'</button>');
    });
});


packageBtn.click(function(e){
    e.preventDefault();
    getAllRules();
});


