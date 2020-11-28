package Tuition.controller;

import Tuition.pojos.Employee;
import Tuition.service.EmployeeService;
import Tuition.service.EmployeeServiceFullStack;

import io.javalin.http.Context;
import org.apache.log4j.Logger;

// Control interactions between parameters passed in by user and calls to FullStackService
public class EmployeeController {
	
	private static Logger log = Logger.getRootLogger();
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	
	public void createEmployee(Context ctx) {
		
		log.trace("Entering createEmployee in EmployeeController");
		
		String firstName = ctx.formParam("firstName");
		
		String lastName = ctx.formParam("lastName");
		
		String email = ctx.formParam("email");
		
		int reportsTo = Integer.parseInt(ctx.formParam("reportsTo"));
		
		int type = Integer.parseInt(ctx.formParam("type"));
		
		int department = Integer.parseInt(ctx.formParam("department"));
		
		int benCo = Integer.parseInt(ctx.formParam("benCo"));
		
		try {
			employeeService.createEmployee((new Employee(type, reportsTo, firstName, lastName, email, department, benCo)));
			ctx.html("Created employee");
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
			log.warn("Exception was thrown " + String.valueOf(e));
		}
	}
	 
	public void readEmployee(Context ctx) {
		
		log.trace("Entering readEmployee in EmployeeController");
		
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		try {
			ctx.html(employeeService.readEmployee(employeeId).toString());
		} catch(Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}

	
	public void updateEmployee(Context ctx) {
		
		log.trace("Entering updateEmployee in EmployeeController");
		
		String firstName = ctx.formParam("firstName");
		
		String lastName = ctx.formParam("lastName");
		
		String email = ctx.formParam("email");
		
		int reportsTo = Integer.parseInt(ctx.formParam("reportsTo"));
		
		int type = Integer.parseInt(ctx.formParam("type"));
		
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		
		int awardAmount = Integer.parseInt(ctx.formParam("awardAmount"));
		
		int pendingAmount = Integer.parseInt(ctx.formParam("pendingAmount"));
		
		int department = Integer.parseInt(ctx.formParam("department"));
		
		int benCo = Integer.parseInt(ctx.formParam("benCo"));
		
		try {
			employeeService.updateEmployee(employeeId, (new Employee(type, reportsTo, firstName, lastName, email, awardAmount, pendingAmount, department, benCo)));
			ctx.html("Updated employee");
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
			log.warn("Exception was thrown " + String.valueOf(e));
		}
	}
	
	
	// delete a card given id (cascading)
	public void deleteEmployee(Context ctx) {
		
		log.trace("Entering deleteEmployee in EmployeedController");
		
		int employeeId = Integer.valueOf(ctx.formParam("employeeId"));
	
		try {
			employeeService.deleteEmployee(employeeId);
			ctx.html("Deleted employee " + employeeId);
		} catch (Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}
}
