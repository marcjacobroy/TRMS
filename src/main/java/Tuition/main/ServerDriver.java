package Tuition.main;

import io.javalin.Javalin;

import org.apache.log4j.Logger;

import Tuition.controller.AuthController;
import Tuition.controller.AwardController;
import Tuition.controller.EmployeeController;
import Tuition.controller.RequestController;
import Tuition.controller.EventController;
import Tuition.controller.GradeController;
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
	private static AwardController awardController = new AwardController();
	private static GradeController gradeController = new GradeController();
	
	private static final String EMPLOYEE_PATH = "/employee";
	private static final String EVENT_PATH = "/event";
	private static final String REQUEST_PATH = "/request";
	private static final String LOGIN_PATH = "/login";
	private static final String MESSAGE_PATH = "/message";
	private static final String AWARD_PATH = "/award";
	private static final String GRADE_PATH = "/grade";
	
	
	
	public static void main(String[] args) {
		log.info("Program has started in Server Driver");
		Javalin app = Javalin.create().start(9098); //sets up and starts our server
		app.get("/hello", ctx -> ctx.html("Hello World"));
		
		app.post(EMPLOYEE_PATH +"/id", ctx -> employeeController.readEmployee(ctx));
		app.get(EMPLOYEE_PATH + "s", ctx -> employeeController.readAllEmployees(ctx));
		app.post(EMPLOYEE_PATH + "/ds", ctx -> employeeController.readDirectSupervisor(ctx));
		app.post(EMPLOYEE_PATH + "/dh", ctx -> employeeController.readDepartmentHead(ctx));
		app.post(EMPLOYEE_PATH, ctx -> employeeController.createEmployee(ctx));
		app.post(EMPLOYEE_PATH + "/update", ctx -> employeeController.updateEmployee(ctx));
		app.delete(EMPLOYEE_PATH, ctx -> employeeController.deleteEmployee(ctx));
		app.post(EMPLOYEE_PATH + "/requestId", ctx -> employeeController.readEmployeeOfRequest(ctx));
		
		app.post(EVENT_PATH + "/id", ctx -> eventController.readEvent(ctx));
		app.post(EVENT_PATH, ctx -> eventController.createEvent(ctx));
		app.patch(EVENT_PATH, ctx -> eventController.updateEvent(ctx));
		app.delete(EVENT_PATH, ctx -> eventController.deleteEvent(ctx));
		app.post(EVENT_PATH + "/requestId", ctx -> eventController.readEventOfRequest(ctx));
		
		app.post(REQUEST_PATH + "/id", ctx -> requestController.readRequest(ctx));
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
		
		app.post(AWARD_PATH, ctx -> awardController.createAward(ctx));
		app.post(AWARD_PATH + "/delete", ctx -> awardController.deleteAward(ctx));
		app.post(AWARD_PATH + "/update", ctx -> awardController.updateAward(ctx));
		app.post(AWARD_PATH + "/id", ctx -> awardController.readAward(ctx));
		app.post(AWARD_PATH + "/employeeId", ctx -> awardController.readAwardsByEmployeeId(ctx));
		app.post(AWARD_PATH + "/requestId", ctx -> awardController.readAwardOfRequest(ctx));
		
		app.post(GRADE_PATH + "/create", ctx -> gradeController.createGrade(ctx));
		app.post(GRADE_PATH + "/read", ctx -> gradeController.readGrade(ctx));
		app.post(GRADE_PATH + "/update", ctx -> gradeController.updateGrade(ctx));
		app.post(GRADE_PATH + "/delete", ctx -> gradeController.deleteGrade(ctx));
		app.post(GRADE_PATH + "/reademp", ctx -> gradeController.readGradesFromEmployee(ctx));
		app.post(GRADE_PATH + "/readds", ctx -> gradeController.readGradesFromDs(ctx));
		app.post(GRADE_PATH + "/readbc", ctx -> gradeController.readGradesFromBenCo(ctx));

	}
	
}
