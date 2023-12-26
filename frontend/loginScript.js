function login() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    // Make an AJAX request to the Spring Boot backend for authentication
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/user/login", true);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Authentication successful, redirect to home page
            window.location.href = "./Home.html";
        } else if (xhr.readyState === 4 && xhr.status === 401) {
            // Authentication failed, display an error message
            alert("Invalid email or password");
        }
    };

    var data = {
        "email": email,
        "password": password
    };

    xhr.send(JSON.stringify(data));
}
