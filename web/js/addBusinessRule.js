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
    }

    if(rule_type_select == 2) {
        let compare_with = $('#compare_with_select').val();
        let compare_text = $('#compare_with_select option:selected').html();
        let compare_val = "";

        if(compare_with == 1) {
            compare_val = $('#enter_literal_value_textbox').val();
        } else if (compare_with == 2) {
            compare_val = $('#select_entity_attribute option:selected').html();
        }

        const rangeArray = {'name': rule_name,
            'type': rule_type_select,
            'attribute': rule_attribute,
            'operator': rule_operator,
            'compareID':compare_with,
            'compareText':compare_text,
            'compareVal': compare_val,
            'failureVal': failure_validation,
            'failureMes': failure_message};
        window.sessionStorage.setItem(rule_name,JSON.stringify(rangeArray));
    }
}
