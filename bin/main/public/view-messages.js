window.onload = function () {
    
    const empEndpoint = "/employee/id";
    const empVerb = "POST";
    const empBody = "employeeId=" + sessionStorage.getItem("id");
    const empOutput = true;
    let employee = ajaxRequest(empEndpoint, empVerb, empBody, empOutput);
    let employeeId = employee.email;
    const endpoint = "/messages/id";
    const verb = "POST";
    const body = "employeeId=" + employeeId;
    const output = true;
    let messageList = ajaxRequest(endpoint, verb, body, output);
    messageList.forEach(element => {
        addRow(element);
    });
}

let addRow = function (message) {
    let table = document.getElementById("message-table");
    let tableRow = document.createElement("tr");
    let senderCol = document.createElement("td");
    let contentsCol = document.createElement("td");
    let subjectCol = document.createElement("td");

    tableRow.appendChild(senderCol);
    tableRow.appendChild(contentsCol);
    tableRow.appendChild(subjectCol);
    table.appendChild(tableRow);

    senderCol.innerHTML = message.sender;
    contentsCol.innerHTML = message.contents;
    subjectCol.innerHTML = message.subject;

    senderCol.className = "table-style";
    contentsCol.className = "table-style";
    tableRow.className = "table-style";
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