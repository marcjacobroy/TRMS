package Tuition.main;

import io.javalin.Javalin;

import org.apache.log4j.Logger;

import Tuition.controller.AuthController;
import Tuition.controller.EmployeeController;
import Tuition.controller.RequestController;
import Tuition.controller.EventController;
import Tuition.controller.MessageController;


// create a connection with javalin to process http requests on server with postman 
// specify paths 
public class ServerDriver {
	
	private static Logger log = Logger.getRootLogger();
	
	private static EmployeeController employeeController = new EmployeeController();
	private static EventController eventController = new EventController();
	private static RequestController requestController = new RequestController();
	private static AuthController authController = new AuthController();
	private static MessageController messageController = new MessageController();
	
	private static final String EMPLOYEE_PATH = "/employee";
	private static final String EVENT_PATH = "/event";
	private static final String REQUEST_PATH = "/request";
	private static final String LOGIN_PATH = "/login";
	private static final String MESSAGE_PATH = "/message";
	
	
	
	public static void main(String[] args) {
		log.info("Program has started in Server Driver");
		Javalin app = Javalin.create().start(9098); //sets up and starts our server
		app.get("/hello", ctx -> ctx.html("Hello World"));
		
		app.post(EMPLOYEE_PATH +"/id", ctx -> employeeController.readEmployee(ctx));
		app.get(EMPLOYEE_PATH + "s", ctx -> employeeController.readAllEmployees(ctx));
		app.post(EMPLOYEE_PATH + "/ds", ctx -> employeeController.readDirectSupervisor(ctx));
		app.get(EMPLOYEE_PATH + "/dh", ctx -> employeeController.readDepartmentHead(ctx));
		app.post(EMPLOYEE_PATH, ctx -> employeeController.createEmployee(ctx));
		app.patch(EMPLOYEE_PATH, ctx -> employeeController.updateEmployee(ctx));
		app.delete(EMPLOYEE_PATH, ctx -> employeeController.deleteEmployee(ctx));
		
		app.post(EVENT_PATH + "/id", ctx -> eventController.readEvent(ctx));
		app.post(EVENT_PATH, ctx -> eventController.createEvent(ctx));
		app.patch(EVENT_PATH, ctx -> eventController.updateEvent(ctx));
		app.delete(EVENT_PATH, ctx -> eventController.deleteEvent(ctx));
		
		app.get(REQUEST_PATH, ctx -> requestController.readRequest(ctx));
		app.post(REQUEST_PATH + "/employeeId",  ctx -> requestController.readRequestsByEmployee(ctx));
		app.post(REQUEST_PATH + "/ds",  ctx -> requestController.readRequestsByDirectSupervisor(ctx));
		app.post(REQUEST_PATH + "/dh",  ctx -> requestController.readRequestsByDepartmentHead(ctx));
		app.post(REQUEST_PATH + "/bc",  ctx -> requestController.readRequestsByBenCo(ctx));
		app.post(REQUEST_PATH, ctx -> requestController.createRequest(ctx));
		app.post(REQUEST_PATH + "/update", ctx -> requestController.updateRequest(ctx));
		app.post(REQUEST_PATH + "/delete", ctx -> requestController.deleteRequest(ctx));
		
		app.post(LOGIN_PATH, ctx -> authController.login(ctx));
		app.get(LOGIN_PATH, ctx -> authController.checkUser(ctx));
		
		app.post(MESSAGE_PATH + "s/id", ctx -> messageController.readMessagesByEmployee(ctx));
		app.post(MESSAGE_PATH, ctx -> messageController.createMessage(ctx));
	}
	
}
