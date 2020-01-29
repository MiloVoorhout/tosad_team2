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
const packageName = $('#packageName');
const modalCode = $('#modal_code');
const modalTrigger = $('#packageGeneratorModal');
const modalTriggerCode = $('#triggerPackageGeneratorCode');
const generateModal = $('#generate_modal');
const packageModal = $('#packageModal');
const valueContainer = $('#valueContainer');
const editBtn = $('#editBtn');
let forEachRowStatus = 0;

const ruleTypeAttrContainer = $('#ruleTypeAttrContainer');
const ruleTypeOperatorContainer = $('#ruleTypeOperatorContainer');
const ruleTypeFailureTypeContainer = $('#ruleTypeFailureTypeContainer');
const ruleTypeFailureMessageContainer = $('#ruleTypeFailureMessageContainer');



subattrTr.hide();
subattrTableTr.hide();

function generateRule() {
    forEachRowStatus = 0;
    const ruleStatement = $('#statementMethodSelect option:selected').html().toUpperCase();
    const dataBaseType = $('#databaseMethodeSelect option:selected').val();
    if (ruleStatement.slice(ruleStatement.length - 12) == "FOR EACH ROW") forEachRowStatus = 1;

    $.get("generate/generate" +
        "?id=" + $('.listItem.active').attr("data-id") +
        "&statement=" + ruleStatement +
        "&ferStatus=" + forEachRowStatus +
        "&dataBaseType=" + dataBaseType, function (array) {
        sessionStorage.clear();
        sessionStorage.setItem("triggerCode", array[0]["code"]);
        const code = array[0]["code"].replace(/(?:\r\n|\r|\n)/g, '<br>');
        modalCode.html(code);
        generateModal.modal('toggle');
    });
}
function generatePackage() {
    forEachRowStatus = 0;
    const ruleStatement = $('#packageMethodSelect option:selected').html().toUpperCase();
    if (ruleStatement.slice(ruleStatement.length - 12) == "FOR EACH ROW") forEachRowStatus = 1;

    if (packageName.val()) {
        if ($("#packageValues option:selected").length) {
            if(/(^[a-z-_]+)$/.test(packageName.val())) {
                $.get("generate/generatePackage" +
                    "?name=" + packageName.val() +
                    "&tableName=" + packageValues.val() +
                    "&packageMethodSelect=" + ruleStatement +
                    "&ferStatus=" + forEachRowStatus, function (array) {
                    packageModal.modal('toggle');
                    modalTriggerCode.html(array[0]["code"]);
                    modalTrigger.modal('toggle');
                });
            } else createAlert('danger', 'Please make the name like name_test', false, true);
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

            if(val['businessRuleType'] === 'Entity Other Rule'){
                ruleTypeAttrContainer.hide();
                ruleTypeOperatorContainer.hide();
                subattrTr.hide();
                subattrTableTr.hide();
                ruleTypeFailureTypeContainer.hide();
                ruleTypeFailureMessageContainer.hide();
            } else {
                ruleTypeAttrContainer.show();
                ruleTypeOperatorContainer.show();
                ruleTypeFailureTypeContainer.show();
                ruleTypeFailureMessageContainer.show();
            }

            if (val['subAttribute']) {
                subattrTr.show();
                subattrTableTr.show();
                ruleTypeSubAttr.html(val['subAttribute']);
                ruleTypeSubAttrTable.html(val['subAttributeTable']);
            } else {
                subattrTr.hide();
                subattrTableTr.hide();
            }


            getValues(id);
        });
    });
}

function getValues(id) {
    $.get("generate/getBusinessRules/getValues?id="+id, function (array) {
        valueContainer.empty();
        valueContainer.append("<tr><th>Value</th><th>Type</th></tr>");
        $.each(array, function (i, val) {
            valueContainer.append("<tr><td>"+ val['value'] +"</td><td>"+ val['type'] +"</td></tr>")
        });
    });
}

function getAllRules(){
    packageValues.empty();
    $.get("define/GetTableInfo/getAllTables", function (array) {
        $.each(array, function (i, val) {
            packageValues.append("<option value='"+ val['name'] +"'>" + val['name'] + "</option>");
        });
    });
}

function executeTrigger() {
    const triggerCode = sessionStorage.getItem("triggerCode");
    triggerCode.replace(/(?:\r\n|\r|\n)/g, '');
    console.log(triggerCode);
    $.get("generate/executeTrigger?code=" + triggerCode, function (array) {
        $.each(array, function (i, val) {
            console.log(array);
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


editBtn.click(function(e){
   e.preventDefault();
    window.location.href = 'edit.html?id='+$('.listItem.active').attr("data-id");
});

packageBtn.click(function(e){
    e.preventDefault();
    getAllRules();
});
