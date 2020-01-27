function initPage() {
    sessionStorage.clear();
    $.get("generate/getBusinessRules/information", function (array) {
        $.each(array, function (i, val) {
            sessionStorage.setItem(val["id"], JSON.stringify(array[i]));

            if(i == 0) {
                $('#list-tab').append('<a class="list-group-item list-group-item-action active" data-id="' + val["id"] + '" id="list-'+ val["id"] +'-list" data-toggle="list" href="#list-'+ val["id"] +'" role="tab" aria-controls="list-'+ val["id"] + '">' + val["name"] + '</a>');
                loadCompareRuleData(array[i], val["id"], val["businessRuleTypeID"]);
                $('#list-' + val["id"]).addClass('active');

            } else {
                $('#list-tab').append('<a class="list-group-item list-group-item-action" data-id="' + val["id"] + '" id="list-'+ val["id"] +'-list" data-toggle="list" href="#list-'+ val["id"] +'" role="tab" aria-controls="list-'+ val["id"] +'">' + val["name"] + '</a>');
                loadCompareRuleData(array[i], val["id"], val["businessRuleTypeID"]);
                $('#list-'+ val["id"] +'').on('click', function (e) {
                    e.preventDefault()
                    $(this).tab('show')
                })
            }
        });
    });
}

function loadCompareRuleData(array, id, information, valueText) {
    $('#nav-tabContent').append(
        '<div class="tab-pane fade show" id="list-'+ id +'" role="tabpanel" aria-labelledby="list-'+ id + '">\n' +
        '                                    <table class="table table-striped">\n' +
        '                                        <thead>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="col">Name</th>\n' +
        '                                            <th scope="col">Value</th>\n' +
        '                                        </tr>\n' +
        '                                        </thead>\n' +
        '                                        <tbody>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Rule Type</th>\n' +
        '                                            <td>' + information + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.attribute.Attribute</th>\n' +
        '                                            <td>' + information + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.attribute.Tabel</th>\n' +
        '                                            <td>' + information + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.productManagement.Operator</th>\n' +
        '                                            <td>' + information + '</td>\n' +
        '                                        </tr>\n' +
        '                                        ' + valueText + '\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Validation Failure Severity</th>\n' +
        '                                            <td>' + information + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Failure Message</th>\n' +
        '                                            <td>' + information + '</td>\n' +
        '                                        </tr>\n' +
        '                                        </tbody>\n' +
        '                                    </table>\n' +
        '                                </div>'
    )
}
