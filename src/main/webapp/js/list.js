const listTab = $('#list-tab');
const content = $('#nav-tabContent');

const ruleStatement = $('#statementMethodSelect option:selected').html().toUpperCase();
const dataBaseType = $('#databaseMethodeSelect option:selected').val();
let forEachRowStatus = 0;
let ruleID;

const packageMethodSelect = $('#packageMethodSelect').val();
const modalCode = $('#modal_code').val();
const generateModal = $('#generate_modal').val();

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

function generateRule() {
    ruleID = $('.listItem.active').attr("data-id");
    if (ruleStatement.slice(ruleStatement.length - 12) == "FOR EACH ROW") forEachRowStatus = 1;
    $.get("generate/generate" +
        "?id=" + ruleID +
        "&statement=" + ruleStatement +
        "&ferStatus=" + forEachRowStatus +
        "&dataBaseType=" + dataBaseType, function (array) {
        let code = array[0]["code"];
        code = code.replace(/(?:\r\n|\r|\n)/g, '<br>');
        $('#modal_code').html(code);
        $('#generate_modal').modal('toggle');
    });
}

function generatePackage() {
    ruleID = $('.listItem.active').attr("data-id");
    $.get("generate/generatePackage" +
        "?id=" + ruleID +
        "&packageValues=" + packageValues +
        "&packageMethodSelect=" + packageMethodSelect, function (array) {
        console.log(array);
    });
}

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