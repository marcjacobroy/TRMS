window.onload = function() {
    let btn = document.getElementById("updateAwardButton");

    btn.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();
        updateAward();
    }, true);
}

function updateAward(){

    let value = document.getElementById("valueInput").value;
    let justification = document.getElementById("justificationInput").value;
    let awardId = document.getElementById("awardIdInput").value;
    let requestId = document.getElementById("requestIdInput").value;

    const rEndpoint = "/request/id";
    const rBody = "requestId=" + requestId;
    const rVerb = "POST";
    const rOutput = true;

    let request = ajaxRequest(rEndpoint, rVerb, rBody, rOutput);

    const eEndpoint = "/employee/id";
    const eBody = "employeeId=" + request.employeeId;
    const eVerb = "POST";
    const eOutput = true;

    let employee = ajaxRequest(eEndpoint, eVerb, eBody, eOutput);
    const limit = 1000;

    const aEndpopint = "/award/id";
    const aBody = "awardId=" + awardId;
    const aVerb = "POST";
    const aOutput = true;
    let award = ajaxRequest(aEndpopint, aVerb, aBody, aOutput);
    let adjust = award.value;

    let exceeding = (limit + adjust - employee.awardAmount - employee.pendingAmount - value < 0);
    
    const endpoint = "/award/update";
    const body = "value=" + value + "&justification=" + justification + "&awarded=" + false + "&exceeding=" + exceeding + "&requestId=" + requestId + "&awardId=" + awardId + "&accepted=" + false;
    const verb = "POST";
    const output = false;

    if (!award.accepted) {
        if (exceeding) {
            let message = prompt("This award will be marked as exceeding available funds for the requesting employee. Please enter justification for your direct supervisor to receive as a message", "Type here");
            const escalateEndpoint = "/employee/ds";
            const escalateVerb = "POST";
            const escalateBody = "employeeId=" + sessionStorage.getItem("id");
            const escalateOutput = true;
            let dsEmployee = ajaxRequest(escalateEndpoint, escalateVerb, escalateBody, escalateOutput);
            let ds = dsEmployee.employeeId;
            makeMessageToDS(message, ds);
        }
        ajaxRequest(endpoint, verb, body, output);
        window.alert("Success! Award has been made!");
        const updateEmployeeEndpoint = "/employee/update";
        const updateEmployeeVerb = "POST";
        const updateEmployeeBody = "employeeId=" + request.employeeId + "&email=" + employee.email + "&firstName=" + employee.firstName + "&lastName=" + employee.lastName + "&reportsTo=" + employee.reportsTo + "&benCo=" + employee.benCo + "&department=" + employee.department + "&type=" + employee.type + "&awardAmount=" + employee.awardAmount + "&pendingAmount=" + (parseInt(employee.pendingAmount) - parseInt(adjust) + parseInt(value));
        const updateEmployeeOutput = false;
        ajaxRequest(updateEmployeeEndpoint, updateEmployeeVerb, updateEmployeeBody, updateEmployeeOutput);
    } else {
        window.alert("This award has already been accepted by the requesting employee and can no longer be modified by you.");
    }
    
}

function makeMessageToDS(message, ds){

    const empEndpoint = "/employee/id";
    const empVerb = "POST";
    const empBody = "employeeId=" + ds;
    const empOutput = true;

    let recipient = ajaxRequest(empEndpoint, empVerb, empBody, empOutput);
    let recipientId = recipient.email;
    let contents = message;
    let senderId = sessionStorage.getItem("id");
    const currEmpEndpoint = "/employee/id";
    const currEmpVerb = "POST";
    const currEmpBody = "employeeId=" + senderId;
    const currEmpOutput = true;
    let currEmployee = ajaxRequest(currEmpEndpoint, currEmpVerb, currEmpBody, currEmpOutput);

    let sender = "Direct Supervisee with email " + currEmployee.email;
    let subject = "Escalation email pertaining your direct supervisee " + currEmployee.firstName + " " + currEmployee.lastName;

    const dsEndpoint = "/message";
    const dsVerb = "POST";
    const dsBody = "recipientId=" + recipientId + "&contents=" + contents + "&sender=" + sender + "&subject=" + subject;
    const dsOutput = false;
    ajaxRequest(dsEndpoint, dsVerb, dsBody, dsOutput);
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