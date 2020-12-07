window.onload = function () {

    const endpoint = "/employees";
    const verb = "GET";
    const body = null;
    const output = true;

    let employeeList = ajaxRequest(endpoint, verb, body, output);
    employeeList.forEach(element => {
        addRow(element);
    });
}

let addRow = function (employee) {
    let table = document.getElementById("employee-table");
    let tableRow = document.createElement("tr");
    let firstNameCol = document.createElement("td");
    let lastNameCol = document.createElement("td");
    let emailCol = document.createElement("td");

    tableRow.appendChild(firstNameCol);
    tableRow.appendChild(lastNameCol);
    tableRow.appendChild(emailCol);
    table.appendChild(tableRow);

    firstNameCol.innerHTML = employee.firstName;
    lastNameCol.innerHTML = employee.lastName;
    emailCol.innerHTML = employee.email;

    firstNameCol.className = "table-style";
    lastNameCol.className = "table-style";
    emailCol.className = "table-style";
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