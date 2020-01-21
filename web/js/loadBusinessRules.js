function initPage() {
    for (var i = 0; i < sessionStorage.length; i++) {
        var storedKey = sessionStorage.key(i)
        var storedArray = JSON.parse(sessionStorage.getItem(storedKey));

        if(i == 0) {
            $('#list-tab').append('<a class="list-group-item list-group-item-action active" id="list-'+ i +'-list" data-toggle="list" href="#list-'+ i +'" role="tab" aria-controls="list-'+ i + '">' + storedKey + '</a>');
            if (storedArray['type'] == 1) {
                loadRangeRuleData(i, storedKey);
            }
            else if (storedArray['type'] == 2) {
                loadCompareRuleData(i, storedKey);
            } else {
                loadListRuleData(i, storedKey);
            }
            $('#list-' + i).addClass('active');
        } else {
            $('#list-tab').append('<a class="list-group-item list-group-item-action" id="list-'+ i +'-list" data-toggle="list" href="#list-'+ i +'" role="tab" aria-controls="list-'+ i +'">' + storedKey + '</a>');
            if (storedArray['type'] == 1) {
                loadRangeRuleData(i, storedKey);
            } else if (storedArray['type'] == 2) {
                loadCompareRuleData(i, storedKey);
            } else {
                loadListRuleData(i, storedKey);
            }

            $('#list-'+ i +'').on('click', function (e) {
                e.preventDefault()
                $(this).tab('show')
            })
        }
    }
}

function loadRangeRuleData(number, key) {
    var storedArray = JSON.parse(sessionStorage.getItem(key))

    $('#nav-tabContent').append(
        '<div class="tab-pane fade show" id="list-'+ number +'" role="tabpanel" aria-labelledby="list-'+ number + '">\n' +
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
        '                                            <td>' + storedArray['typeText'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.attribute.Attribute</th>\n' +
        '                                            <td>' + storedArray['attribute'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.attribute.Tabel</th>\n' +
        '                                            <td>' + storedArray['table'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.productManagement.Operator</th>\n' +
        '                                            <td>' + storedArray['operator'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <!-- In case of Between -->\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Min Value</th>\n' +
        '                                            <td>' + storedArray['minVal'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Max Value</th>\n' +
        '                                            <td>' + storedArray['maxVal'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <!-- end -->\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Validation Failure Severity</th>\n' +
        '                                            <td>' + storedArray['failureVal'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Failure Message</th>\n' +
        '                                            <td>' + storedArray['failureMes'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        </tbody>\n' +
        '                                    </table>\n' +
        '                                </div>'
    )
}

function loadCompareRuleData(number, key) {
    var storedArray = JSON.parse(sessionStorage.getItem(key))

    $('#nav-tabContent').append(
        '<div class="tab-pane fade show" id="list-'+ number +'" role="tabpanel" aria-labelledby="list-'+ number + '">\n' +
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
        '                                            <td>' + storedArray['typeText'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.attribute.Attribute</th>\n' +
        '                                            <td>' + storedArray['attribute'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.attribute.Tabel</th>\n' +
        '                                            <td>' + storedArray['table'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.productManagement.Operator</th>\n' +
        '                                            <td>' + storedArray['operator'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <!-- In case of Compare -->\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Compare with</th>\n' +
        '                                            <td>' + storedArray['compareText'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <!-- end -->\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Value</th>\n' +
        '                                            <td>' + storedArray['compareVal'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Validation Failure Severity</th>\n' +
        '                                            <td>' + storedArray['failureVal'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Failure Message</th>\n' +
        '                                            <td>' + storedArray['failureMes'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        </tbody>\n' +
        '                                    </table>\n' +
        '                                </div>'
    )
}

function loadListRuleData(number, key) {
    var storedArray = JSON.parse(sessionStorage.getItem(key))

    $('#nav-tabContent').append(
        '<div class="tab-pane fade show" id="list-'+ number +'" role="tabpanel" aria-labelledby="list-'+ number + '">\n' +
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
        '                                            <td>' + storedArray['typeText'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.attribute.Attribute</th>\n' +
        '                                            <td>' + storedArray['attribute'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.attribute.Tabel</th>\n' +
        '                                            <td>' + storedArray['table'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">tosad.productManagement.Operator</th>\n' +
        '                                            <td>' + storedArray['operator'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <!-- In case of Between -->\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Values</th>\n' +
        '                                            <td>' + storedArray['listValues'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <!-- end -->\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Validation Failure Severity</th>\n' +
        '                                            <td>' + storedArray['failureVal'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        <tr>\n' +
        '                                            <th scope="row">Failure Message</th>\n' +
        '                                            <td>' + storedArray['failureMes'] + '</td>\n' +
        '                                        </tr>\n' +
        '                                        </tbody>\n' +
        '                                    </table>\n' +
        '                                </div>'
    )
}