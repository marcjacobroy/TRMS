package Tuition.dao;

import Tuition.pojos.Employee;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
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
		
		String sql = "insert into employee (type, reports_to, first_name, last_name, email) values(?, ?, ?, ?, ?)"; 
		
		try {
			Connection connection = connUtil.createConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, e.getType());
			stmt.setInt(2,  e.getReportsTo());
			stmt.setString(3,  e.getFirstName());
			stmt.setString(4,  e.getLastName());
			stmt.setString(5, e.getEmail());
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
				return new Employee(type, reportsTo, firstName, lastName, email);
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
		
		String sql = "update employee set type = ?, reports_to = ?, first_name = ?, last_name = ?, email = ? where employee_id = ?";
		
		if (HelperFunctions.employeeExists(employeeId)) {
			try (Connection conn = connUtil.createConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, e.getType());
				stmt.setInt(2, e.getReportsTo());
				stmt.setString(3, e.getFirstName());
				stmt.setString(4, e.getLastName());
				stmt.setString(5, e.getEmail());
				stmt.setInt(6, employeeId);
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

}
