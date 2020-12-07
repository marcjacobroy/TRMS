window.onload = function() {

    let btn = document.getElementById("viewSubmittedGradesButton");
    btn.style.display = "none";
    let form = document.getElementById("structure");
    let id = sessionStorage.getItem("id");

    if (isBenco(id) || isDS(id)) {
        btn.style.display = "inline";
        console.log("yes");
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
    console.log("returning " + ret);
    return ret;
}

function isDS(id) {
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
    console.log("returning " + ret);
    return ret;
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