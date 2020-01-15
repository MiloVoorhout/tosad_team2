function add_button() {
    let rule_type_select = $('#rule_type_select').val();
    let rule_name = $('#rule_name').val();
    let rule_attribute = $('#attribute_select option:selected').html();
    let rule_operator = $('#operator_select option:selected').html();
    let failure_validation = $('#validation_failure_severity option:selected').html();
    let failure_message = $('#failure_message').val();

    if(rule_type_select == 1) {
        let range_min_val = $('#minimum_value').val();
        let range_max_val = $('#maximum_value').val();

        const rangeArray = {'name': rule_name,
                            'type': rule_type_select,
                            'attribute': rule_attribute,
                            'operator': rule_operator,
                            'minVal':range_min_val,
                            'maxVal': range_max_val,
                            'failureVal': failure_validation,
                            'failureMes': failure_message};
        window.sessionStorage.setItem(rule_name,JSON.stringify(rangeArray));

        // var storedArray = JSON.parse(sessionStorage.getItem(rule_name));
    }

    if(rule_type_select == 2) {
        let compare_with = $('#compare_with_select').val();

        if(compare_with == 1) {
            let compare_literal_val = $('#enter_literal_value_textbox').val();

            console.log(rule_type_select, rule_name,
                rule_attribute, rule_operator,
                compare_with, compare_literal_val,
                failure_validation, failure_message
            );
        } else if (compare_with == 2) {
            let compare_attribute = $('#select_entity_attribute option:selected').html();

            console.log(rule_type_select, rule_name,
                rule_attribute, rule_operator,
                compare_with, compare_attribute,
                failure_validation, failure_message
            );
        }
    }
}
