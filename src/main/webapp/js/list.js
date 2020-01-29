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
const packageMethodSelect = $('#packageMethodSelect');
const packageName = $('#packageName');
const modalCode = $('#modal_code');
const generateModal = $('#generate_modal');
const packageModal = $('#packageModal');
let forEachRowStatus = 0;

subattrTr.hide();
subattrTableTr.hide();

function generateRule() {
    forEachRowStatus = 0;
    const ruleStatement = $('#statementMethodSelect option:selected').html().toUpperCase();
    const dataBaseType = $('#databaseMethodeSelect option:selected').val();
    if (ruleStatement.slice(ruleStatement.length - 12) == "FOR EACH ROW") forEachRowStatus = 1;
    console.log(forEachRowStatus);
    console.log(ruleStatement.slice(ruleStatement.length - 12) == "FOR EACH ROW");

    $.get("generate/generate" +
        "?id=" + $('.listItem.active').attr("data-id") +
        "&statement=" + ruleStatement +
        "&ferStatus=" + forEachRowStatus +
        "&dataBaseType=" + dataBaseType, function (array) {
        const code = array[0]["code"].replace(/(?:\r\n|\r|\n)/g, '<br>');
        modalCode.html(code);
        generateModal.modal('toggle');
    });
}

function generatePackage() {
    console.log(packageValues.val());
    if (packageName.val()) {
        if ($("#packageValues option:selected").length) {
            $.get("generate/generatePackage" +
                "?name=" + packageName.val() +
                "&packageValues=" + packageValues.val() +
                "&packageMethodSelect=" + packageMethodSelect.val(), function (array) {
                console.log(array);
                // createAlert('success', 'Package "'+ packageName.val() +'" succesfully created', false, true);
                // form.trigger("reset");
                // packageModal.modal('toggle');
            });
        } else createAlert('danger', 'Please choose one or more rules for this package', false, true);
    } else createAlert('danger', 'Please enter a package name', false, true);
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