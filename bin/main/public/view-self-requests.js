window.onload = function () {

    let id = sessionStorage.getItem('id');
    const endpoint = "/request/employeeId";
    const verb = "POST";
    const body = "employeeId=" + id;
    const output = true;

    let requestList = ajaxRequest(endpoint, verb, body, output);
    requestList.forEach(element => {
        addRow(element);
    });
}

let addRow = function (request) {
    let table = document.getElementById("request-table");
    let tableRow = document.createElement("tr");
    let requestIdCol = document.createElement("td");
    let eventIdCol = document.createElement("td");
    let eventDesCol = document.createElement("td");
    let justificationCol = document.createElement("td");
    let dateCol = document.createElement("td");
    let dsStatusCol = document.createElement("td");
    let dhStatusCol = document.createElement("td");
    let bcStatusCol = document.createElement("td");
    let statusCol = document.createElement("td");
    let urgencyCol = document.createElement("td");
    let attachmentCol = document.createElement("td");
    let hoursCol = document.createElement("td");

    tableRow.appendChild(requestIdCol);
    tableRow.appendChild(eventIdCol);
    tableRow.appendChild(eventDesCol);
    tableRow.appendChild(justificationCol);
    tableRow.appendChild(dateCol);
    tableRow.appendChild(dsStatusCol);
    tableRow.appendChild(dhStatusCol);
    tableRow.appendChild(bcStatusCol);
    tableRow.appendChild(statusCol);
    tableRow.appendChild(urgencyCol);
    tableRow.appendChild(attachmentCol);
    tableRow.appendChild(hoursCol);

    table.append(tableRow);

    eventEndpoint = "/event/requestId";
    eventVerb = "POST";
    eventBody = "requestId=" + request.requestId;
    eventOutput = true;

    console.log("requestId is " + request.requestId);
    let event = ajaxRequest(eventEndpoint, eventVerb, eventBody, eventOutput);

    requestIdCol.innerHTML = request.requestId;
    eventIdCol.innerHTML = request.eventId;
    eventDesCol.innerHTML = event.description;
    justificationCol.innerHTML = request.justification;
    dateCol.innerHTML = request.date;
    dsStatusCol.innerHTML = request.dsApproved ? "complete" : "incomplete";
    dhStatusCol.innerHTML = request.dhApproved ? "complete" : "incomplete";
    bcStatusCol.innerHTML = request.benCoApproved ? "complete" : "incomplete";
    statusCol.innerHTML = request.complete ? "complete" : "incomplete";
    urgencyCol.innerHTML = request.urgent ? "urgent" : "non urgent";
    attachmentCol.innerHTML = request.attachment;
    hoursCol.innerHTML = request.hoursMissed;


    requestIdCol.className = "table-style";
    eventIdCol.className = "table-style";
    eventDesCol.className = "table-style";
    justificationCol.className = "table-style";
    dateCol.className = "table-style";
    dsStatusCol.className = "table-style";
    dhStatusCol.className = "table-style";
    bcStatusCol.className = "table-style";
    statusCol.className = "table-style";
    urgencyCol.className = "table-style";
    attachmentCol.className = "table-style";
    hoursCol.className = "table-style";
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