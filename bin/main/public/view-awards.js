window.onload = function () {

    let id = sessionStorage.getItem('id');
    const endpoint = "/award/employeeId";
    const verb = "POST";
    const body = "employeeId=" + id;
    const output = true;
    let awardsList = ajaxRequest(endpoint, verb, body, output);
    awardsList.forEach(award => {
        if (award.awarded) {
            addRow(award);
        }
    });

    const endpointEmp = "/employee/id";
    const verbEmp = "POST";
    const bodyEmp = "employeeId=" +id;
    const outputEmp = true;
    let employee = ajaxRequest(endpointEmp, verbEmp, bodyEmp, outputEmp);
    let total = document.getElementById("total");
    let remaining = document.getElementById("remaining");
    total.innerText = "You have been awarded a total of: $" + (parseInt(employee.awardAmount));
    const limit = 1000;
    remaining.innerText = "You have a remaining potential award amount of: $" + (limit - parseInt(employee.awardAmount));
}

let addRow = function (award) {
    let table = document.getElementById("award-table");
    let tableRow = document.createElement("tr");

    let awardIdCol = document.createElement("td");
    let eventCol = document.createElement("td");
    let amountAwardedCol = document.createElement("td");

    tableRow.appendChild(awardIdCol);
    tableRow.appendChild(eventCol);
    tableRow.appendChild(amountAwardedCol);

    let event = getEventOfAward(award);
    awardIdCol.innerText = award.awardId;
    eventCol.innerText = event.description;
    amountAwardedCol.innerText = award.value;

    table.appendChild(tableRow);

    awardIdCol.className = "table-style";
    eventCol.className = "table-style";
    amountAwardedCol.className = "table-style";

    
}

let getEventOfAward = function (award) {
    const endpoint = "/event/requestId";
    const verb = "POST";
    const body = "requestId=" + award.requestId;
    const output = true;
    let event = ajaxRequest(endpoint, verb, body, output);
    return event;
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