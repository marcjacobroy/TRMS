window.onload = function() {

    let btnDs = document.getElementById("ds");
    btnDs.style.display = "none";
    let btnDh = document.getElementById("dh");
    btnDh.style.display = "none";
    let btnBc = document.getElementById("bc");
    btnBc.style.display = "none";
    let id = sessionStorage.getItem("id");

    if (isDs(id)) {
        btnDs.style.display = "inline";
    }
    if (isDh(id)) {
        btnDh.style.display = "inline";
    }
    if (isBenco(id)) {
        btnBc.style.display = "inline";
    }
}

function isBenco(id) {
    endpoint = "/employees";
    verb = "GET";
    body = "";
    output = true;

    ret = false;
    let employeeList = ajaxRequest(endpoint, verb, body, output);
    employeeList.forEach(employee => {
        if (parseInt(employee.benCo, 10) == id){
            ret = true;
        }
    });
    return ret;
}

function isDs(id) {
    endpoint = "/employees";
    verb = "GET";
    body = "";
    output = true;

    ret = false;
    let employeeList = ajaxRequest(endpoint, verb, body, output);
    employeeList.forEach(employee => {
        if (parseInt(employee.reportsTo, 10) == id){
            ret = true;
        }
    });
    return ret;
}

function isDh(id) {
    endpoint = "/employee/id";
    verb = "POST";
    body = "employeeId=" + id;
    output = true;
    let employee = ajaxRequest(endpoint, verb, body, output);
    return employee.type == 2;
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