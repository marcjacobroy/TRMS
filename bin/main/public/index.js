window.onload = function() {
    let forms = document.getElementsByTagName('form');
    forms[0].style.display = "block";
    forms[1].style.display = "none";
    grabEmployee();
    loginGuest();
}

as = document.getElementsByTagName('a');

for (i = 0; i < as.length; i++){
    as[i].onclick = function(){toggleForms()};
    console.log(i);
}

function toggleForms(){
    let forms = document.getElementsByTagName('form');
    for (i = 0; i < forms.length; i++){
        if (forms[i].style.display === "none") {
            forms[i].style.display = "block";
        } else {
            forms[i].style.display = "none";
        }
    }
}

let grabEmployee = function() {

    let btn = document.getElementById("createEmployeeButton");
    let registerForm = document.getElementsByClassName("register-form")[0];
    btn.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        let firstName = document.getElementById("firstNameInput").value;
        let lastName = document.getElementById("lastNameInput").value;
        let email = document.getElementById("emailInput").value;
        let department = document.getElementById("departmentInput").value;
        let reportsTo = document.getElementById("reportsToInput").value;
        let benCo = document.getElementById("benCoInput").value;
        let type = 1;

        const endpoint = "/employee";
        const verb = "POST";
        let body = "firstName=" + firstName + "&lastName=" + lastName + "&email=" + email + "&type=" + type + "&reportsTo=" + reportsTo + "&department=" + department + "&benCo=" + benCo;
        
        ajaxRequest(endpoint, verb, body, false);
        toggleForms();
        registerForm.reset();
    }, true);

}

function viewDashboardRedirect(){
    window.location = "portal.html";
}

let loginGuest = function() {
    let btn = document.getElementById("loginButton");
    let loginForm = document.getElementsByClassName("login-form")[0];
    btn.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        let email = document.getElementById("emailLogin").value;
        
        empEndpoint = "/employee/email";
        empVerb = "POST";
        empBody = "email=" + email;
        empOutput = true;
        let employee = ajaxRequest(empEndpoint, empVerb, empBody, empOutput);
        let id = employee.employeeId;
        sessionStorage.setItem('id', id);
        viewDashboardRedirect();
    }, true);
}

function ajaxRequest(endpoint, verb, body, output) {

    let xhr = new XMLHttpRequest();
    const url = "http://localhost:9098" + endpoint;
    
    xhr.onreadystatechange = function() {
        switch (xhr.readyState) {

            case 0:
                console.log("nothing, initalized not sent");
                break;
            case 1:
                console.log("connection established")
                break;
            case 2:
                console.log("request sent");
                break;
            case 3:
                console.log("waiting response");
                break;
            case 4:
                console.log("FINISHED!!!!!!!!!!!");
                //logic to add guest to table
                if (xhr.status === 200) {
                }
                break;
        }
    }

    //opens up the request
    xhr.open(verb, url, false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    //sends request
    xhr.send(body);

    if (output) {
        return JSON.parse(xhr.responseText);
    }
}