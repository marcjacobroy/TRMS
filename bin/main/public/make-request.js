window.onload = function() {
    let btn = document.getElementById("createRequestButton");

    btn.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();
        makeEvent();
    }, true);
}


function makeEvent(){

    let currentDate = new Date();
    let eventDate = new Date(document.getElementById("dateInput").value);
    let time = document.getElementById("timeInput").value;
    let location = document.getElementById("locationInput").value;
    let description = document.getElementById("descriptionInput").value;
    let cost = document.getElementById("costInput").value;
    let gradingFormat = document.getElementById("gradingFormatInput").value;
    let eventType = document.getElementById("typeInput").value;

    const oneDay = 24 * 60 * 60 * 1000;
    const diffDays = Math.round(Math.abs((currentDate - eventDate) / oneDay));
    
    const endpoint = "/event";
    const body = "date=" + eventDate + "&time=" + time + "&location=" + location + "&description=" + description + "&cost=" + cost + "&gradingFormat=" + gradingFormat + "&type=" + eventType;
    const verb = "POST";
    const output = true;

    if (diffDays < 7 || eventDate - currentDate < 0){
        window.alert("We are sorry, your request has been filed too late. Requests must be made with at least 1 week in advance.");
    } else {
        let event = ajaxRequest(endpoint, verb, body, output);
        makeRequest(event.eventId);
    }
}

function makeRequest(eventId){

    let employeeId = sessionStorage.getItem("id");
    let justification = document.getElementById("justificationInput").value;
    let currentDate = new Date();
    let eventDate = new Date(document.getElementById("dateInput").value);
    let hours = document.getElementById("hoursInput").value;
    let dhApproval = document.getElementById("dhApprovalInput");
    let dsApproval = document.getElementById("dsApprovalInput");
    let dhApprovalProof = dhApproval.checked;
    let dsApprovalProof = dsApproval.checked;
    let attachment = "none";
    let currentWorker = sessionStorage.getItem("id");

    const oneDay = 24 * 60 * 60 * 1000;
    const diffDays = Math.round(Math.abs((currentDate - eventDate) / oneDay));
    let urgent = (diffDays < 14);

    const endpoint = "/request";
    const body = "employeeId=" + employeeId + "&eventId=" + eventId + "&justification=" + justification + "&date=" + currentDate + "&dsApproved=" + dsApprovalProof + "&dhApproved=" + dhApprovalProof + "&benCoApproved=" + false + "&currentWorker=" + currentWorker + "&complete=" + false + "&urgent=" + urgent + "&attachment=" + attachment + "&hoursMissed=" + hours + "&dsApprovalProof=" + dsApprovalProof + "&dhApprovalProof=" + dhApprovalProof;
    const verb = "POST";
    const output = false;
    ajaxRequest(endpoint, verb, body, output);
    window.alert("Success! Request made!");
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