var casper = require('casper').create({
    viewportSize: {width: 1280, height: 800}
});

casper.start('https://www.facebook.com/', function() {
    this.echo(this.getTitle());
    console.log("Got the log in page");
    casper.capture('F:/Softwares/PriceTracker/PriceTracker/Backend/PriceTracker/src/main/resources/CasperJS/navigation.png');
    this.evaluate(fillUser);
    casper.capture('F:/Softwares/PriceTracker/PriceTracker/Backend/PriceTracker/src/main/resources/CasperJS/navigation2.png');

});

function fillUser(){
    var email = document.getElementById("email");
    var password = document.getElementById("pass");
    var submitButton = document.getElementById("u_0_5_uG");
    email.value = "nikhil";
    password.value = "nikhilTheGreat";
}

casper.run();