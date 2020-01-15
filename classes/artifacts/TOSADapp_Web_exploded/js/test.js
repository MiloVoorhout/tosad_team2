function test() {
    fetch('restservices/testservlet')
        .then(reponse => reponse.json())
        .then(function (test) {
            console.log(test);
        })
}