// const rule_type_select = $('#rule_type_select');
//
// function getRuleTypes() {
//     $.get("rest/GetRuleTypes", function (array) {
//         $.each(array, function (i, val) {
//             rule_type_select.append('<option value="' + val['id'] + '">' + val['type'] + '</option>')
//         });
//     });
// }
//
// getRuleTypes();

// function setAttributes() {
//     $.get("rest/GetRuleTypes", function(array){
//         attributeSelect.empty();
//         selectEntityAttribute.empty();
//         $.each( array, function( i, val ) {
//             attributeSelect.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>')
//             selectEntityAttribute.append('<option value="'+ val['name'] +'">'+ val['name'] +'</option>')
//         });
//     });
// }

// rule_type_select.on('change', function(e) {
//     setAttributes();
// });

// getTables();