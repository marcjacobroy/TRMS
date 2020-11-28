package Tuition.main;

import io.javalin.Javalin;

import org.apache.log4j.Logger;


import Tuition.controller.EmployeeController;

// create a connection with javalin to process http requests on server with postman 
// specify paths 
public class ServerDriver {
	
	private static Logger log = Logger.getRootLogger();
	
	private static EmployeeController employeeController = new EmployeeController();
	
	private static final String EMPLOYEE_PATH = "/employee";
	
	
	
	public static void main(String[] args) {
		log.info("Program has started in Server Driver");
		Javalin app = Javalin.create().start(9096); //sets up and starts our server
		app.get("/hello", ctx -> ctx.html("Hello World"));
		
		app.get(EMPLOYEE_PATH, ctx -> employeeController.readEmployee(ctx));
		app.post(EMPLOYEE_PATH, ctx -> employeeController.createEmployee(ctx));
		app.patch(EMPLOYEE_PATH, ctx -> employeeController.updateEmployee(ctx));
		app.delete(EMPLOYEE_PATH, ctx -> employeeController.deleteEmployee(ctx));
		
	}
	
}
