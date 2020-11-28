package Tuition.service;

import Tuition.pojos.Employee;

public interface EmployeeService {
	
	public void createEmployee(Employee e);
	
	public Employee readEmployee(int employeeId);
	
	public void updateEmployee(int employeeId, Employee e);
	
	public void deleteEmployee(int employeeId);
}
