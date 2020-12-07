package Tuition.dao;

import Tuition.pojos.Employee;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Tuition.util.ConnectionUtil;
import Tuition.util.HelperFunctions;

public class EmployeeDaoPostgres implements EmployeeDao {

	private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement stmt;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}
	
	@Override
	public void createEmployee(Employee e) {
		
		log.debug("Entering createGuest in EmployeeDaoPostgres on " + e);
		
		String sql = "insert into employee (type, reports_to, first_name, last_name, email, award_amount, pending_amount, department, ben_co) values(?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		
		try {
			Connection connection = connUtil.createConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, e.getType());
			stmt.setInt(2,  e.getReportsTo());
			stmt.setString(3,  e.getFirstName());
			stmt.setString(4,  e.getLastName());
			stmt.setString(5, e.getEmail());
			stmt.setInt(6,  e.getAwardAmount());
			stmt.setInt(7,  e.getPendingAmount());
			stmt.setInt(8,  e.getDepartment());
			stmt.setInt(9,  e.getBenCo());
			stmt.executeUpdate();
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
		}

	}

	@Override
	public Employee readEmployee(int employeeId) {
		
		log.debug("Calling readEmployee in EmployeeDaoPostgres on " + employeeId);
		
		String sql = "select * from employee where employee_id = ?";
			
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int type = rs.getInt("type");
				int reportsTo = rs.getInt("reports_to");
				int awardAmount = rs.getInt("award_amount");
				int pendingAmount = rs.getInt("pending_amount");
				int department = rs.getInt("department");
				int benCo = rs.getInt("ben_co");
				Employee e = new Employee(type, reportsTo, firstName, lastName, email, awardAmount, pendingAmount, department, benCo);
				e.setEmployeeId(employeeId);
				return e;
			} else {
				log.warn("Called on non existant employee");
				throw new IllegalArgumentException("Employee with id " + employeeId + " does not exist");
			}
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateEmployee(int employeeId, Employee e) {
		
		
		log.debug("Calling updateEmployee in EmployeeDaoPostgres on " + employeeId + " " + e);
		
		String sql = "update employee set type = ?, reports_to = ?, first_name = ?, last_name = ?, email = ?, award_amount = ?, pending_amount = ?, department = ?, ben_co = ? where employee_id = ?";
		
		if (HelperFunctions.employeeExists(employeeId)) {
			try (Connection conn = connUtil.createConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, e.getType());
				stmt.setInt(2, e.getReportsTo());
				stmt.setString(3, e.getFirstName());
				stmt.setString(4, e.getLastName());
				stmt.setString(5, e.getEmail());
				stmt.setInt(6,  e.getAwardAmount());
				stmt.setInt(7,  e.getPendingAmount());
				stmt.setInt(8,  e.getDepartment());
				stmt.setInt(9,  e.getBenCo());
				stmt.setInt(10, employeeId);
				stmt.executeUpdate();
			} catch (SQLException exc) {
				log.warn("Exception thrown " + String.valueOf(exc));
				exc.printStackTrace();
			}
		} else {
			log.warn("Called on non existant employee");
			throw new IllegalArgumentException("Employee with id " + employeeId + " does not exist");
		}
	}

	@Override
	public void deleteEmployee(int employeeId) {
		
		log.debug("Calling deleteEmployee in EmployeeDaoPostgres on " + employeeId);
		
		String sql = "delete from employee where employee_id = ?";
		
		if (HelperFunctions.employeeExists(employeeId)) {
			try (Connection conn = connUtil.createConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, employeeId);
				stmt.executeUpdate();
			} catch (SQLException exc) {
				log.warn("Exception thrown " + String.valueOf(exc));
				exc.printStackTrace();
			}
		} else {
			log.warn("Called on non existant employee");
			throw new IllegalArgumentException("Employee with id " + employeeId + " does not exist");
		}

	}

	@Override
	public List<Employee> readAllEmployees() {
		
		log.debug("Entering readAllEmployees in EmployeeDaoPostgres");
		
		String sql = "select * from employee";
		
		List<Employee> employeeList = new ArrayList<>();
		
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				employeeList.add(makeEmployee(rs));
			}
			return employeeList;
		} catch (SQLException e) {
			log.warn("Exception thrown " + String.valueOf(e));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee readDirectSupervisor(int employeeId) {
		
		log.debug("Calling readDirectSupervisor in EmployeeDaoPostgres on " + employeeId);
		
		String sql = "select e2.* from employee e1, employee e2 where e1.employee_id = ? and e1.reports_to = e2.employee_id";
			
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int type = rs.getInt("type");
				int reportsTo = rs.getInt("reports_to");
				int awardAmount = rs.getInt("award_amount");
				int pendingAmount = rs.getInt("pending_amount");
				int department = rs.getInt("department");
				int benCo = rs.getInt("ben_co");
				int dsId = rs.getInt("employee_id");
				Employee e = new Employee(type, reportsTo, firstName, lastName, email, awardAmount, pendingAmount, department, benCo);
				e.setEmployeeId(dsId);
				return e;
			} else {
				log.warn("Called on employee with no direct supervisor");
				throw new IllegalArgumentException("Employee with id " + employeeId + " has no ds");
			}
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee readDepartmentHead(int employeeId) {
		
		log.debug("Calling readDepartmentHead in EmployeeDaoPostgres on " + employeeId);
		
		String sql = "select e2.* from employee e1, employee e2 where e1.employee_id = ? and e1.department = e2.department and e2.type = 2";
			
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int type = rs.getInt("type");
				int reportsTo = rs.getInt("reports_to");
				int awardAmount = rs.getInt("award_amount");
				int pendingAmount = rs.getInt("pending_amount");
				int department = rs.getInt("department");
				int benCo = rs.getInt("ben_co");
				int dhId = rs.getInt("employee_id");
				Employee e = new Employee(type, reportsTo, firstName, lastName, email, awardAmount, pendingAmount, department, benCo);
				e.setEmployeeId(dhId);
				return e;
			} else {
				log.warn("Called on employee with no dh");
				throw new IllegalArgumentException("Employee with id " + employeeId + " has no dh");
			}
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee readBenCo(int employeeId) {
		
		log.debug("Calling readBenCo in EmployeeDaoPostgres on " + employeeId);
		
		String sql = "select e2.* from employee e1, employee e2 where e1.employee_id = ? and e1.ben_co = e2.employee_id";
			
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int type = rs.getInt("type");
				int reportsTo = rs.getInt("reports_to");
				int awardAmount = rs.getInt("award_amount");
				int pendingAmount = rs.getInt("pending_amount");
				int department = rs.getInt("department");
				int benCo = rs.getInt("ben_co");
				int bcId = rs.getInt("employee_id");
				Employee e = new Employee(type, reportsTo, firstName, lastName, email, awardAmount, pendingAmount, department, benCo);
				e.setEmployeeId(bcId);
				return e;
			} else {
				log.warn("Called on employee with no bh");
				throw new IllegalArgumentException("Employee with id " + employeeId + " has no bh");
			}
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}
	
	
	public Employee makeEmployee(ResultSet rs) throws SQLException {
		
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String email = rs.getString("email");
		int type = rs.getInt("type");
		int reportsTo = rs.getInt("reports_to");
		int awardAmount = rs.getInt("award_amount");
		int pendingAmount = rs.getInt("pending_amount");
		int department = rs.getInt("department");
		int benCo = rs.getInt("ben_co");
		return new Employee(type, reportsTo, firstName, lastName, email, awardAmount, pendingAmount, department, benCo);
	}

	@Override
	public Employee readEmployeeOfRequest(int requestId) {
		
		String sql = "select e.* from employee e, request r where e.employee_id = r.employee_id and r.request_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, requestId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int type = rs.getInt("type");
				int reportsTo = rs.getInt("reports_to");
				int awardAmount = rs.getInt("award_amount");
				int pendingAmount = rs.getInt("pending_amount");
				int department = rs.getInt("department");
				int benCo = rs.getInt("ben_co");
				int employeeId = rs.getInt("employee_id");
				Employee e = new Employee(type, reportsTo, firstName, lastName, email, awardAmount, pendingAmount, department, benCo);
				e.setEmployeeId(employeeId);
				return e;
			} else {
				log.warn("Called on non existant employee");
				throw new IllegalArgumentException("Employee of request with id " + requestId + " does not exist");
			}
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee getEmployeeOfEmail(String email) {
		
		String sql = "select * from employee where email = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String emailE = rs.getString("email");
				int type = rs.getInt("type");
				int reportsTo = rs.getInt("reports_to");
				int awardAmount = rs.getInt("award_amount");
				int pendingAmount = rs.getInt("pending_amount");
				int department = rs.getInt("department");
				int benCo = rs.getInt("ben_co");
				Employee e = new Employee(type, reportsTo, firstName, lastName, emailE, awardAmount, pendingAmount, department, benCo);
				e.setEmployeeId(rs.getInt("employee_id"));
				return e;
			} else {
				log.warn("Called on non existant employee");
				throw new IllegalArgumentException("Employee with email " + email + " does not exist");
			}
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
		
	}

}
