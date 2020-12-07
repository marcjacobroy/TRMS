window.onload = function () {

    let id = sessionStorage.getItem('id');
    const endpoint = "/grade/reademp";
    const verb = "POST";
    const body = "employeeId=" + id;
    const output = true;

    let gradeList = ajaxRequest(endpoint, verb, body, output);
    gradeList.forEach(element => {
        addRow(element);
    });
}

let addRow = function (grade) {
    let table = document.getElementById("request-table");
    let tableRow = document.createElement("tr");
    let requestIdCol = document.createElement("td");
    let gradeCol = document.createElement("td");

    tableRow.appendChild(requestIdCol);
    tableRow.appendChild(gradeCol);
    table.append(tableRow);

    requestIdCol.innerHTML = grade.requestId;
    gradeCol.innerHTML = grade.grade;

    requestIdCol.className = "table-style";
    gradeCol.className = "table-style";
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