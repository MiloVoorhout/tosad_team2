function initPage() {
    for (var i = 0; i < sessionStorage.length; i++) {
        var storedKey = sessionStorage.key(i)
        var storedArray = JSON.parse(sessionStorage.getItem(storedKey));

        console.log(storedArray['name']);

        if(i == 0) {
            $('#list-tab').append('<a class="list-group-item list-group-item-action active" id="list-'+ i +'-list" data-toggle="list" href="#list-'+ i +'" role="tab" aria-controls="list-'+ i + '">' + storedKey + '</a>');
            $('#nav-tabContent').append(
                '<div class="tab-pane fade show active" id="list-'+ i +'" role="tabpanel" aria-labelledby="list-'+ i + '">\n' +
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
                '                                            <td>' + storedArray['type'] + '</td>\n' +
                '                                        </tr>\n' +
                '                                        <tr>\n' +
                '                                            <th scope="row">tosad.attribute.Attribute</th>\n' +
                '                                            <td>' + storedArray['attribute'] + '</td>\n' +
                '                                        </tr>\n' +
                '                                        <tr>\n' +
                '                                            <th scope="row">tosad.productManagement.Operator</th>\n' +
                '                                            <td>' + storedArray['operator'] + '</td>\n' +
                '                                        </tr>\n' +
                '                                        <!-- In case of Compare -->\n' +
                '                                        <tr>\n' +
                '                                            <th scope="row">Compare with</th>\n' +
                '                                            <td>tosad.attribute.Attribute</td>\n' +
                '                                        </tr>\n' +
                '                                        <!-- end -->\n' +
                '                                        <tr>\n' +
                '                                            <th scope="row">Value</th>\n' +
                '                                            <td>' + "Max value: " + storedArray['maxVal'] + "Min value: " + storedArray['minVal'] +'</td>\n' +
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
        } else {
            $('#list-tab').append('<a class="list-group-item list-group-item-action" id="list-'+ i +'-list" data-toggle="list" href="#list-'+ i +'" role="tab" aria-controls="list-'+ i +'">' + storedKey + '</a>');
            $('#nav-tabContent').append(
                '<div class="tab-pane fade show" id="list-'+ i +'" role="tabpanel" aria-labelledby="list-'+ i +'">\n' +
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
                '                                            <td>' + storedArray['type'] + '</td>\n' +
                '                                        </tr>\n' +
                '                                        <tr>\n' +
                '                                            <th scope="row">tosad.attribute.Attribute</th>\n' +
                '                                            <td>' + storedArray['attribute'] + '</td>\n' +
                '                                        </tr>\n' +
                '                                        <tr>\n' +
                '                                            <th scope="row">tosad.productManagement.Operator</th>\n' +
                '                                            <td>' + storedArray['operator'] + '</td>\n' +
                '                                        </tr>\n' +
                '                                        <!-- In case of Compare -->\n' +
                '                                        <tr>\n' +
                '                                            <th scope="row">Compare with</th>\n' +
                '                                            <td>tosad.attribute.Attribute</td>\n' +
                '                                        </tr>\n' +
                '                                        <!-- end -->\n' +
                '                                        <tr>\n' +
                '                                            <th scope="row">Value</th>\n' +
                '                                            <td>' + "Max value: " + storedArray['maxVal'] + "Min value: " + storedArray['minVal'] +'</td>\n' +
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
            );

            $('#list-'+ i +'').on('click', function (e) {
                e.preventDefault()
                $(this).tab('show')
            })
        }
    }


}