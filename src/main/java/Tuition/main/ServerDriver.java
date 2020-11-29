package Tuition.main;

import io.javalin.Javalin;

import org.apache.log4j.Logger;


import Tuition.controller.EmployeeController;
import Tuition.controller.RequestController;
import Tuition.controller.EventController;


// create a connection with javalin to process http requests on server with postman 
// specify paths 
public class ServerDriver {
	
	private static Logger log = Logger.getRootLogger();
	
	private static EmployeeController employeeController = new EmployeeController();
	private static EventController eventController = new EventController();
	private static RequestController requestController = new RequestController();
	
	private static final String EMPLOYEE_PATH = "/employee";
	private static final String EVENT_PATH = "/event";
	private static final String REQUEST_PATH = "/request";
	
	
	
	public static void main(String[] args) {
		log.info("Program has started in Server Driver");
		Javalin app = Javalin.create().start(9096); //sets up and starts our server
		app.get("/hello", ctx -> ctx.html("Hello World"));
		
		app.get(EMPLOYEE_PATH, ctx -> employeeController.readEmployee(ctx));
		app.post(EMPLOYEE_PATH, ctx -> employeeController.createEmployee(ctx));
		app.patch(EMPLOYEE_PATH, ctx -> employeeController.updateEmployee(ctx));
		app.delete(EMPLOYEE_PATH, ctx -> employeeController.deleteEmployee(ctx));
		
		app.get(EVENT_PATH, ctx -> eventController.readEvent(ctx));
		app.post(EVENT_PATH, ctx -> eventController.createEvent(ctx));
		app.patch(EVENT_PATH, ctx -> eventController.updateEvent(ctx));
		app.delete(EVENT_PATH, ctx -> eventController.deleteEvent(ctx));
		
		app.get(REQUEST_PATH, ctx -> requestController.readRequest(ctx));
		app.post(REQUEST_PATH, ctx -> requestController.createRequest(ctx));
		app.patch(REQUEST_PATH, ctx -> requestController.updateRequest(ctx));
		app.delete(REQUEST_PATH, ctx -> requestController.deleteRequest(ctx));
	}
	
}
