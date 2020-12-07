window.onload = function () {

    let id = sessionStorage.getItem('id');
    const endpoint = "/event/employeeId";
    const verb = "POST";
    const body = "employeeId=" +id;
    const output = true;

    let eventList = ajaxRequest(endpoint, verb, body, output);
    eventList.forEach(element => {
        addRow(element);
    });
}

let addRow = function (event) {
    let table = document.getElementById("request-table");
    let tableRow = document.createElement("tr");
    let dateCol = document.createElement("td");
    let timeCol = document.createElement("td");
    let descriptionCol = document.createElement("td");
    let costCol = document.createElement("td");
    let gradingFormatCol = document.createElement("td");
    let typeCol = document.createElement("td");

    tableRow.appendChild(dateCol);
    tableRow.appendChild(timeCol);
    tableRow.appendChild(descriptionCol);
    tableRow.appendChild(costCol);
    tableRow.appendChild(gradingFormatCol);
    tableRow.appendChild(typeCol);

    table.append(tableRow);

    dateCol.innerHTML = event.date;
    timeCol.innerHTML = event.time;
    descriptionCol.innerHTML = event.description;
    costCol.innerHTML = event.cost;
    gradingFormatCol.innerHTML = event.gradingFormat;
    typeCol.innerHTML = event.type 


    dateCol.className = "table-style";
    timeCol.className = "table-style";
    descriptionCol.className = "table-style";
    costCol.className = "table-style";
    gradingFormatCol.className = "table-style";
    typeCol.className = "table-style";
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