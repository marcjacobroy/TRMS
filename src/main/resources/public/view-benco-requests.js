window.onload = function () {

    let id = sessionStorage.getItem('id');
    const endpoint = "/request/bc";
    const verb = "POST";
    const body = "employeeId=" +id;
    const output = true;
    let requestList = ajaxRequest(endpoint, verb, body, output);
    requestList.forEach(element => {
        if (!element.bcApproved && element.dsApproved && element.dhApproved){
            addRow(element);
        }
    });
}

let addRow = function (request) {

    let table = document.getElementById("request-table");
    let tableRow = document.createElement("tr");
    let eventIdCol = document.createElement("td");
    let justificationCol = document.createElement("td");
    let dateCol = document.createElement("td");
    let dsStatusCol = document.createElement("td");
    let dhStatusCol = document.createElement("td");
    let bcStatusCol = document.createElement("td");
    let statusCol = document.createElement("td");
    let urgencyCol = document.createElement("td");
    let attachmentCol = document.createElement("td");
    let hoursCol = document.createElement("td");
    let btnApprove = document.createElement("button");
    btnApprove.innerHTML = "Approve";
    let btnReject = document.createElement("button");
    btnReject.innerHTML = "Reject";
    let btnMore = document.createElement("button");
    btnMore.innerHTML = "Request more info from requester";
    let btnMoreDs = document.createElement("button");
    btnMoreDs.innerHTML = "Request more info from requester's DS";
    let btnMoreDh = document.createElement("button");
    btnMoreDh.innerHTML = "Request more info from requester's DH";


    let employeeFirstNameCol = document.createElement("td");
    let employeeLastNameCol = document.createElement("td");
    let employeeEmailCol = document.createElement("td");

    let eventDateCol = document.createElement("td");
    let eventTimeCol = document.createElement("td");
    let eventLocationCol = document.createElement("td");
    let eventDescriptionCol = document.createElement("td");
    let eventCostCol = document.createElement("td");
    let eventGradingFormatCol = document.createElement("td");
    let eventTypeCol = document.createElement("td");

    const empEndpoint = "/employee/id";
    const empVerb = "POST";
    const empBody = "employeeId=" + request.employeeId;
    const empOutput = true;
    let employee = ajaxRequest(empEndpoint, empVerb, empBody, empOutput);
    employeeFirstNameCol.innerHTML = employee.firstName;
    employeeLastNameCol.innerHTML = employee.lastName;
    employeeEmailCol.innerHTML = employee.email;

    const eventEndpoint = "/event/id";
    const eventVerb = "POST";
    const eventBody = "eventId=" + request.eventId;
    const eventOutput = true;

    let event = ajaxRequest(eventEndpoint, eventVerb, eventBody, eventOutput);
    eventDateCol.innerHTML = event.date;
    eventTimeCol.innerHTML = event.time;
    eventLocationCol.innerHTML = event.location;
    eventDescriptionCol.innerHTML = event.description;
    eventCostCol.innerHTML = event.cost;
    eventGradingFormatCol.innerHTML = event.gradingFormat;

    tableRow.appendChild(employeeFirstNameCol);
    tableRow.appendChild(employeeLastNameCol);
    tableRow.appendChild(employeeEmailCol);
    tableRow.appendChild(eventDateCol);
    tableRow.appendChild(eventTimeCol);
    tableRow.appendChild(eventLocationCol);
    tableRow.appendChild(eventDescriptionCol);
    tableRow.appendChild(eventCostCol);
    tableRow.appendChild(eventGradingFormatCol);
    tableRow.appendChild(justificationCol);
    tableRow.appendChild(dateCol);
    tableRow.appendChild(dsStatusCol);
    tableRow.appendChild(dhStatusCol);
    tableRow.appendChild(bcStatusCol);
    tableRow.appendChild(statusCol);
    tableRow.appendChild(urgencyCol);
    tableRow.appendChild(attachmentCol);
    tableRow.appendChild(hoursCol);
    if (!request.complete){
        tableRow.appendChild(btnApprove);
        tableRow.appendChild(btnReject);
        tableRow.appendChild(btnMore);
        tableRow.appendChild(btnMoreDs);
        tableRow.appendChild(btnMoreDh);
    };
    table.append(tableRow);


    let currentDate = new Date();
    let eventDate = new Date(event.date);
    const oneDay = 24 * 60 * 60 * 1000;
    const diffDays = Math.round(Math.abs((currentDate - eventDate) / oneDay));
    let urgent = diffDays < 14;

    eventIdCol.innerHTML = request.eventId;
    justificationCol.innerHTML = request.justification;
    dateCol.innerHTML = request.date;
    dsStatusCol.innerHTML = request.dsApproved ? "complete" : "incomplete";
    dhStatusCol.innerHTML = request.dhApproved ? "complete" : "incomplete";
    bcStatusCol.innerHTML = request.benCoApproved ? "complete" : "incomplete";
    statusCol.innerHTML = request.complete ? "complete" : "incomplete";
    urgencyCol.innerHTML = urgent ? "urgent" : "non urgent";
    attachmentCol.innerHTML = request.attachment;
    hoursCol.innerHTML = request.hoursMissed;

    const deadlineDays = 1;
    if (diffDays < deadlineDays){
        const escalateEndpoint = "/employee/id";
        const escalateVerb = "POST";
        const escalateBody = "employeeId=" + sessionStorage.getItem("id");
        const escalateOutput = true;
        let currEmployee = ajaxRequest(escalateEndpoint, escalateVerb, escalateBody, escalateOutput);
        let ds = currEmployee.reportsTo;
        makeMessageToDS("I am incompetent and am neglecting my work", ds);
    }

    employeeFirstNameCol.className = "table-style";
    employeeLastNameCol.className = "table-style";
    employeeEmailCol.className = "table-style";
    eventDateCol.className = "table-style";
    eventTimeCol.className = "table-style";
    eventLocationCol.className = "table-style";
    eventDescriptionCol.className = "table-style";
    eventCostCol.className = "table-style";
    eventGradingFormatCol.className = "table-style";
    justificationCol.className = "table-style";
    dateCol.className = "table-style";
    dsStatusCol.className = "table-style";
    dhStatusCol.className = "table-style";
    bcStatusCol.className = "table-style";
    statusCol.className = "table-style";
    urgencyCol.className = "table-style";
    attachmentCol.className = "table-style";
    hoursCol.className = "table-style";

    btnApprove.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        message = prompt("Please inform the employee of their award amount in this message", "Enter message here");
        makeMessage(message);
        const approveEndpoint = "/request/update";
        const approveVerb = "POST";
        const approveBody = "employeeId=" + request.employeeId + "&eventId=" + request.eventId + "&justification=" + request.justification + "&date=" + request.date + "&dsApproved=" + request.dsApproved + "&dhApproved=" + request.dhApproved + "&benCoApproved=" + true + "&currentWorker=" + request.currentWorker + "&complete=" + true + "&urgent=" + request.urgent + "&attachment=" + request.attachment + "&hoursMissed=" + request.hoursMissed + "&dsApprovalProof=" + request.dsApprovalProof + "&dhApprovalProof=" + request.dhApprovalProof + "&requestId=" + request.requestId; 
        const approveOutput = false;
        ajaxRequest(approveEndpoint, approveVerb, approveBody, approveOutput);
        sessionStorage.setItem("requestId", request.requestId);
        window.location = "make-award.html";
    }, true);

    btnReject.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        let message = prompt("Please provide a reason for rejecting this request: ", "I regret to inform you that your request was not up to our standards.");

        makeMessage(message);

        const rejectEndpoint = "/request/delete";
        const rejectVerb = "POST";
        const rejectBody = "requestId=" + request.requestId;
        const rejectOutput = false;
        ajaxRequest(rejectEndpoint, rejectVerb, rejectBody, rejectOutput);
    }, true);

    btnMore.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        let message = prompt("Please input more details on what info you would like to see: ", "Type here");

        makeMessage(message);
    }, true);

    btnMoreDs.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        let message = prompt("Please input more details on what info you would like to see: ", "Type here");

        let rds = employee.reportsTo;

        makeMessageToRDS(message, rds);
    }, true);

    btnMoreDh.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        let message = prompt("Please input more details on what info you would like to see: ", "Type here");

        const RDHEndpoint = "/employee/dh";
        const RDHVerb = "POST";
        const RDHBody = "employeeId=" + request.employeeId;
        const RDHOutput = true;
        let RDH = ajaxRequest(RDHEndpoint, RDHVerb, RDHBody, RDHOutput);

        makeMessageToRDH(message, RDH.employeeId);
    }, true);

    function makeMessage(message){
        const empEndpoint = "/employee/id";
        const empVerb = "POST";
        const empBody = "employeeId=" + request.employeeId;
        const empOutput = true;

        let recipient = ajaxRequest(empEndpoint, empVerb, empBody, empOutput);
        let recipientId = recipient.email;
        let contents = message;
        let senderId = sessionStorage.getItem("id");
        let sender = "Benefits Coordinator";
        let subject = "Pertaining your request with id " + request.requestId;

        const messageEndpoint = "/message";
        const messageVerb = "POST";
        const messageBody = "senderId=" + senderId + "&recipientId=" + recipientId + "&contents=" + contents + "&sender=" + sender + "&subject=" + subject;
        const messageOutput = false;
        ajaxRequest(messageEndpoint, messageVerb, messageBody, messageOutput);
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

    function makeMessageToRDS(message, rds){
        const empEndpoint = "/employee/id";
        const empVerb = "POST";
        const empBody = "employeeId=" + rds;
        const empOutput = true;

        let recipient = ajaxRequest(empEndpoint, empVerb, empBody, empOutput);
        let recipientId = recipient.email;
        let contents = message;
        let sender = "Benefits Coordinator of your Direct Supervisee";
        let subject = "Pertaining your direct supervisee " + employee.firstName + " " + employee.lastName + "'s request with id " + request.requestId; 

        const rdsEndpoint = "/message";
        const rdsVerb = "POST";
        const rdsBody = "recipientId=" + recipientId + "&contents=" + contents + "&sender=" + sender + "&subject=" + subject;
        const rdsOutput = false;
        ajaxRequest(rdsEndpoint, rdsVerb, rdsBody, rdsOutput);
    }

    function makeMessageToRDH(message, rdh){
        const empEndpoint = "/employee/id";
        const empVerb = "POST";
        const empBody = "employeeId=" + rdh;
        const empOutput = true;

        let recipient = ajaxRequest(empEndpoint, empVerb, empBody, empOutput);
        let recipientId = recipient.email;
        let contents = message;
        let sender = "Benefits Coordinator of your Department Member";
        let subject = "Pertaining your department member " + employee.firstName + " " + employee.lastName + "'s request with id " + request.requestId;

        const rdhEndpoint = "/message";
        const rdhVerb = "POST";
        const rdhBody = "recipientId=" + recipientId + "&contents=" + contents + "&sender=" + sender + "&subject=" + subject;
        const rdhOutput = false;
        ajaxRequest(rdhEndpoint, rdhVerb, rdhBody, rdhOutput);
    }
}

function ajaxRequest(endpoint, verb, body, output) {

    let xhr = new XMLHttpRequest();
    const url = "http://localhost:9098" + endpoint;
    
    xhr.onreadystatechange = function() {
        switch (xhr.readyState) {

            case 0:
                //console.log("nothing, initalized not sent");
                break;
            case 1:
                //console.log("connection established")
                break;
            case 2:
                //console.log("request sent");
                break;
            case 3:
                //console.log("waiting response");
                break;
            case 4:
                //console.log("FINISHED!!!!!!!!!!!");
                //logic to add guest to table
                if (xhr.status === 200) {
                    //console.log("200 status code");
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