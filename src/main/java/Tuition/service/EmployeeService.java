package Tuition.service;

import java.util.List;


import Tuition.pojos.Employee;

public interface EmployeeService {
	
	public void createEmployee(Employee e);
	
	public Employee readEmployee(int employeeId);
	
	public void updateEmployee(int employeeId, Employee e);
	
	public void deleteEmployee(int employeeId);
	
	public List<Employee> readAllEmployees();

	public Employee readDirectSupervisor(int employeeId);

	public Employee readDepartmentHead(int employeeId);

	public Employee readBenCo(int employeeId);

	public Employee readEmployeeOfRequest(int requestId);
}
