# PROJECT NAME

## Project Description

The purpose of TRMS is to provide a system that encourages quality knowledge growth relevant to an individual’s expertise. Currently, TRMS provides reimbursements for university courses, seminars, certification preparation classes, certifications, and technical training. The decision has been made to develop a BPMsolution for this system to improve the timeliness and accuracy of approvals. The current system relies solely on email communication, requiring manual lookups of available funds and is error-prone due to inbox clutter and incorrect routing of tasks. Furthermore, there is no way to record and report on reimbursements awarded, and so the company has no way to identify highly-invested courses that could be developed to be offered in-house.

## Technologies Used


 * Java built application
 * Utilizes JDBC to connect to a Postgres database.
 * Exposes HTTP endpoints through the use of Javalin.
 * Log4j for logging throughout the program.
 * Junit for unit testing
 * Mockito for dependency based testing to ensure proper unit tests.
 * Fully functional frontend
 * HTML for page structure
 * CSS for page styles
 * JavaScript for page functionality
 * Utilize AJAX for acquiring resources


## Features

* Log-in as an employee, supervisor, department head, or benefits coordiator
* Create a reimbursement request
* Approve reimbursement requests as an elevated user
* Request gradually moves through approval process until final benefits coordinator approval

To-do list:
* Integrate JWT in order to provide better protection for API endpoints
* Improve visual appeal and uesr-friendliness of front-end application 

## Getting Started
   
* Clone this repo: git clone https://github.com/marcjacobroy/TRMS
* Run the provided SQL script to construct the database in a PostgreSQL database
* Set environment variables to conncet to database: 
   * TRMS_URL : URL of the database
   * TRMS_USERNAME : Username for your local install of postgres
   * TRMS_PASSWORD : Password of your local install
