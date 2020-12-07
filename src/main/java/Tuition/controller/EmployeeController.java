package Tuition.controller;

import Tuition.pojos.Employee;
import Tuition.service.EmployeeService;
import Tuition.service.EmployeeServiceFullStack;

import io.javalin.http.Context;
import org.apache.log4j.Logger;
import java.util.List;

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
			ctx.json(employeeService.readEmployee(employeeId));
		} catch(Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}
	
	public void readEmployeeOfRequest(Context ctx) {
		int requestId = Integer.parseInt(ctx.formParam("requestId"));
		try {
			ctx.json(employeeService.readEmployeeOfRequest(requestId));
		} catch(Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}
	
	public void readAllEmployees(Context ctx) {
		
		log.trace("Entering readAllEmployees in EmployeeController");
		
		List<Employee> allEmployees = employeeService.readAllEmployees();
		
		ctx.json(allEmployees);
	}
	
	public void readDirectSupervisor(Context ctx) {
		
		log.trace("Entering readDirectSupervisor in EmployeeController");
		
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		
		Employee directSupervisor = employeeService.readDirectSupervisor(employeeId);
		
		ctx.json(directSupervisor);
	}
	
	public void readDepartmentHead(Context ctx) {
		
		log.trace("Entering readDepartment in EmployeeController");
		System.out.println("readDepartmentHead");
		
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		
		Employee departmentHead = employeeService.readDepartmentHead(employeeId);
		
		ctx.json(departmentHead);
	}
	
	public void readBenCo(Context ctx) {
		
		log.trace("Entering readBenCo in EmployeeController");
		
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		
		Employee benCo = employeeService.readBenCo(employeeId);
		
		ctx.json(benCo);
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
