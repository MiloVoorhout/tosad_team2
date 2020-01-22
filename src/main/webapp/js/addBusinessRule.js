function add_button() {
    const rule_type_select = $('#rule_type_select').val();
    const rule_type_text = $('#rule_type_select option:selected').html();
    const rule_name = $('#rule_name').val();
    const rule_attribute = $('#attribute_select option:selected').html();
    const rule_table = $('#table_select option:selected').html();
    const failure_validation = $('#validation_failure_severity option:selected').html();
    const failure_message = $('#failure_message').val();
    let rule_operator = "";

    if(rule_type_select == 1) {
        rule_operator = $('#operator_select option:selected').html();
        let range_min_val = $('#minimum_value').val();
        let range_max_val = $('#maximum_value').val();

        const rangeArray = {'name': rule_name,
                            'type': rule_type_select,
                            'typeText': rule_type_text,
                            'attribute': rule_attribute,
                            'table': rule_table,
                            'operator': rule_operator,
                            'minVal':range_min_val,
                            'maxVal': range_max_val,
                            'failureVal': failure_validation,
                            'failureMes': failure_message};
        console.log(rangeArray);
    }

    if(rule_type_select == 2) {
        rule_operator = $('#operator_select_compare option:selected').html();
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
            'typeText': rule_type_text,
            'attribute': rule_attribute,
            'table': rule_table,
            'operator': rule_operator,
            'compareID':compare_with,
            'compareText':compare_text,
            'compareVal': compare_val,
            'failureVal': failure_validation,
            'failureMes': failure_message};
        console.log(rangeArray);
    }

    if(rule_type_select == 3) {
        rule_operator = $('#operator_select_list option:selected').html();
        var optionValues = [];

        $('#listValues option').each(function () {
            optionValues.push($(this).val())
        })

        const rangeArray = {'name': rule_name,
            'type': rule_type_select,
            'typeText': rule_type_text,
            'attribute': rule_attribute,
            'table': rule_table,
            'operator': rule_operator,
            'listValues':optionValues,
            'failureVal': failure_validation,
            'failureMes': failure_message};
        window.sessionStorage.setItem(rule_name, JSON.stringify(rangeArray));
    }

    let properties = $("#properties");
    properties.hide();
    $('#rule_type_select').val(0);
}
