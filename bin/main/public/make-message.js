
window.onload = function() {
    let btn = document.getElementById("createMessageButton");

    btn.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();
        makeMessage();
    }, true);
}


function makeMessage(){

    let senderId = sessionStorage.getItem("id");

    const empEndpoint = "/employee/id";
    const empVerb = "POST";
    const empBody = "employeeId=" + senderId;
    const empOutput = true;

    let employee = ajaxRequest(empEndpoint, empVerb, empBody, empOutput);
    console.log(employee.firstName);
    let sender = employee.firstName + " " + employee.lastName + " with email " + employee.email;

    let recipientId = document.getElementById("recipientInput").value;
    let contents = document.getElementById("messageInput").value;
    let subject = document.getElementById("subjectInput").value;

    const endpoint = "/message";
    const verb = "POST";
    const output = false;
    const body = "sender=" + sender + "&recipientId=" + recipientId + "&contents=" + contents + "&subject=" + subject;
    ajaxRequest(endpoint, verb, body, output);
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
                    console.log("200 status code");
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