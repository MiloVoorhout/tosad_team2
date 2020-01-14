function test() {
    fetch('restservices/TestServlet')
        .then(reponse => reponse.json())
        .then(function (test) {
            console.log(test);
        })
}