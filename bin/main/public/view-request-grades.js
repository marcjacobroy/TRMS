window.onload = function () {

    let id = sessionStorage.getItem('id');
    const endpoint = "/grade/readbc";
    const verb = "POST";
    const body = "employeeId=" + id;
    const output = true;

    let gradeList = ajaxRequest(endpoint, verb, body, output);
    gradeList.forEach(element => {
        addRow(element);
    });

    const endpointDs = "/grade/readds";
    let gradeListDs = ajaxRequest(endpointDs, verb, body, output);
    gradeListDs.forEach(element => {
        addRow(element);
    });
}

let addRow = function (grade) {
    let table = document.getElementById("request-table");
    let tableRow = document.createElement("tr");
    let nameCol = document.createElement("td");
    let requestIdCol = document.createElement("td");
    let gradeCol = document.createElement("td");
    let btnApprove = document.createElement("button");
    let btnReject = document.createElement("button");

    rEndpoint = "/request/id";
    rVerb = "POST";
    rBody = "requestId=" + grade.requestId;
    rOutput = true;
    let request = ajaxRequest(rEndpoint, rVerb, rBody, rOutput);

    eEndpoint = "/employee/id";
    eVerb = "POST";
    eBody = "employeeId=" + request.employeeId;
    eOutput = true;
    let employee = ajaxRequest(eEndpoint, eVerb, eBody, eOutput);

    aEndpoint = "/award/requestId";
    aVerb = "POST";
    aBody = "requestId=" + grade.requestId;
    aOutput = true;
    let award = ajaxRequest(aEndpoint, aVerb, aBody, aOutput);

    btnApprove.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        const approveEndpoint = "/award/update";
        const approveVerb = "POST";
        const approveBody = "awarded=" + true + "&value=" + award.value + "&justification=" + award.justification + "&exceeding=" + award.exceeding + "&requestId=" + award.requestId + "&awardId=" + award.awardId + "&accepted=" + award.accepted;
        const approveOutput = false;
        ajaxRequest(approveEndpoint, approveVerb, approveBody, approveOutput);

        const eEndpoint = "/employee/requestId";
        const eBody = "requestId=" + grade.requestId;
        const eVerb = "POST";
        const eOutput = true;
        let employee = ajaxRequest(eEndpoint, eVerb, eBody, eOutput);
        let employeeId = employee.employeeId;

        const updateEmployeeEndpoint = "/employee/update";
        const updateEmployeeVerb = "POST";
        const updateEmployeeBody = "employeeId=" + employeeId + "&email=" + employee.email + "&firstName=" + employee.firstName + "&lastName=" + employee.lastName + "&reportsTo=" + employee.reportsTo + "&benCo=" + employee.benCo + "&department=" + employee.department + "&type=" + employee.type + "&awardAmount=" + (parseInt(employee.awardAmount) + parseInt(award.value)) + "&pendingAmount=" + (parseInt(employee.pendingAmount) - parseInt(award.value));
        const updateEmployeeOutput = false;
        ajaxRequest(updateEmployeeEndpoint, updateEmployeeVerb, updateEmployeeBody, updateEmployeeOutput);

    }, true);

    btnReject.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();

        const eEndpoint = "/employee/requestId";
        const eBody = "requestId=" + grade.requestId;
        const eVerb = "POST";
        const eOutput = true;
        let employee = ajaxRequest(eEndpoint, eVerb, eBody, eOutput);
        let employeeId = employee.employeeId;

        const updateEmployeeEndpoint = "/employee/update";
        const updateEmployeeVerb = "POST";
        const updateEmployeeBody = "employeeId=" + employeeId + "&email=" + employee.email + "&firstName=" + employee.firstName + "&lastName=" + employee.lastName + "&reportsTo=" + employee.reportsTo + "&benCo=" + employee.benCo + "&department=" + employee.department + "&type=" + employee.type + "&awardAmount=" + employee.awardAmount + "&pendingAmount=" + (parseInt(employee.pendingAmount) - parseInt(award.value));
        const updateEmployeeOutput = false;
        ajaxRequest(updateEmployeeEndpoint, updateEmployeeVerb, updateEmployeeBody, updateEmployeeOutput);

        const dgEndpoint = "/grade/delete";
        const dgVerb = "POST";
        const dgBody = "gradeId=" + grade.gradeId;
        const dgOutput = false;
        ajaxRequest(dgEndpoint, dgVerb, dgBody, dgOutput);

        const rejectEndpoint = "/award/delete";
        const rejectVerb = "POST";
        const rejectBody = "awardId=" + award.awardId;
        const rejectOutput = false;
        ajaxRequest(rejectEndpoint, rejectVerb, rejectBody, rejectOutput);

        const drEndpoint = "/request/delete";
        const drVerb = "POST";
        const drBody = "requestId=" + grade.requestId;
        const drOutput = false;
        ajaxRequest(drEndpoint, drVerb, drBody, drOutput);

    }, true);


    tableRow.appendChild(nameCol);
    tableRow.appendChild(requestIdCol);
    tableRow.appendChild(gradeCol);
    if (!award.awarded) {
        btnApprove.innerText = "Approve";
        btnReject.innerText = "Reject";
        tableRow.appendChild(btnApprove);
        tableRow.appendChild(btnReject);
    }
    table.append(tableRow);

    nameCol.innerHTML = employee.firstName + " " + employee.lastName;
    requestIdCol.innerHTML = grade.requestId;
    gradeCol.innerHTML = grade.grade;

    nameCol.className = "table-style";
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