package Tuition.service;

import Tuition.pojos.Employee;

import org.apache.log4j.Logger;

import Tuition.dao.EmployeeDao;
import Tuition.dao.EmployeeDaoPostgres;


public class EmployeeServiceFullStack implements EmployeeService {
	
	private static Logger log = Logger.getRootLogger();
	
	EmployeeDao employeeDao = new EmployeeDaoPostgres();

	@Override
	public void createEmployee(Employee e) {
		log.trace("Calling createEmployee in EmployeeServiceFullStack on " + e);
		employeeDao.createEmployee(e);

	}

	@Override
	public Employee readEmployee(int employeeId) {
		log.trace("Calling readEmployee in EmployeeServiceFullStack on " + employeeId);
		return employeeDao.readEmployee(employeeId);
	}

	@Override
	public void updateEmployee(int employeeId, Employee e) {
		log.trace("Calling updateEmployee in EmployeeServiceFullStack on " + employeeId + " and " + e);
		employeeDao.updateEmployee(employeeId, e);

	}

	@Override
	public void deleteEmployee(int employeeId) {
		log.trace("Calling deleteEmployee in EmployeeServiceFullStack on " + employeeId);
		employeeDao.deleteEmployee(employeeId);

	}

}