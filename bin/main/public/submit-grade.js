window.onload = function() {
    let btn = document.getElementById("createGradeButton");

    btn.addEventListener("click", function(event){
        event.preventDefault();
        event.stopPropagation();
        makeGrade();
    }, true);
}


function makeGrade(){

    let employeeId = sessionStorage.getItem("id");

    let requestId = document.getElementById("requestIdInput").value;
    let grade = document.getElementById("gradeInput").value;

    const reqEndpoint = "/request/id";
    const reqVerb = "POST";
    const reqBody = "requestId=" + requestId;
    const reqOutput = true;

    let request = ajaxRequest(reqEndpoint, reqVerb, reqBody, reqOutput);
    
    const eventEndpoint = "/event/id";
    const eventVerb = "POST";
    const eventBody = "eventId=" + request.eventId;
    const eventOutput = true;

    let event = ajaxRequest(eventEndpoint, eventVerb, eventBody, eventOutput);

    let gradingFormat = event.gradingFormat;

    let currentDate = new Date();
    let eventDate = new Date(event.date);
    const oneDay = 24 * 60 * 60 * 1000;
    const courseFinished = eventDate < currentDate;

    console.log(gradingFormat);
    console.log(grade);
    if (request.employeeId != employeeId) {
        window.alert("This is not your request. Please check back to your list of requests.");
    // } else if (!courseFinished) {
    //     window.alert("This course has not finished yet. Please come back once you finish the course.");
    } else if (gradingFormat == 1 && (grade != "A" && grade != "B" && grade != "C")) {
        window.alert("Please enter a passing grade for the format A-F");
    } else if (gradingFormat == 2 && (grade != "1" && grade != "2" && grade != "3" && grade != "4")) {
        window.alert("Please enter a passing grade for the format 1-6");
    } else if (gradingFormat == 3 && grade != "Pass") {
        window.alert("Please enter a passing grade for the format Pass/Fail (presentation)");
    } else {
        const gradeEndpoint = "/grade/create";
        const gradeVerb = "POST";
        const gradeBody = "requestId=" + requestId + "&grade=" + grade;
        const gradeOutput = false;
        ajaxRequest(gradeEndpoint, gradeVerb, gradeBody, gradeOutput);
        window.alert("Successfully submitted grade!");
    }
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
        console.log(xhr.responseText);
        return JSON.parse(xhr.responseText);
    }
}