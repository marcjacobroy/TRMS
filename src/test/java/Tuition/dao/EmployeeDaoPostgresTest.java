package Tuition.dao;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import Tuition.pojos.Employee;
import Tuition.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDaoPostgresTest {
	
	public EmployeeDaoPostgres employeeDao = new EmployeeDaoPostgres();
	
	@Mock
	private ConnectionUtil connUtil;
	
	@Mock 
	private Connection connection;
	
	private PreparedStatement stmt;
	private PreparedStatement spy;
	private PreparedStatement utilStmt;
	
	private Connection realConnection;
	
	private int TEST_EMPLOYEE_ID;
	private String TEST_EMPLOYEE_FIRST_NAME = "Marc";
	private String TEST_EMPLOYEE_LAST_NAME = "Roy";
	private String TEST_EMPLOYEE_EMAIL = "mroy@college.harvard.edu";
	private int TEST_EMPLOYEE_TYPE = 1;
	private int TEST_EMPLOYEE_REPORTS_TO = 13;
	private int TEST_EMPLOYEE_AWARD_AMOUNT = 0;
	private int TEST_EMPLOYEE_PENDING_AMOUNT = 0;
	private int TEST_EMPLOYEE_DEPARTMENT = 1;
	private int TEST_EMPLOYEE_BEN_CO = 13;
	
	@Before
	public void setUp() throws Exception {
		
		realConnection = new ConnectionUtil().createConnection();
		
		employeeDao.setConnUtil(connUtil);
		
		utilStmt = realConnection.prepareStatement("insert into employee (type, reports_to, first_name, last_name, email, award_amount, pending_amount, department, ben_co) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		utilStmt.setInt(1, TEST_EMPLOYEE_TYPE);
		utilStmt.setInt(2, TEST_EMPLOYEE_REPORTS_TO);
		utilStmt.setString(3, TEST_EMPLOYEE_FIRST_NAME);
		utilStmt.setString(4, TEST_EMPLOYEE_LAST_NAME);
		utilStmt.setString(5,  TEST_EMPLOYEE_EMAIL);
		utilStmt.setInt(6, TEST_EMPLOYEE_AWARD_AMOUNT);
		utilStmt.setInt(7, TEST_EMPLOYEE_PENDING_AMOUNT);
		utilStmt.setInt(8, TEST_EMPLOYEE_DEPARTMENT);
		utilStmt.setInt(9, TEST_EMPLOYEE_BEN_CO);
		utilStmt.executeUpdate();
		
		utilStmt = realConnection.prepareStatement("select employee_id from employee where first_name = ? and last_name = ? and email = ?");
		utilStmt.setString(1, TEST_EMPLOYEE_FIRST_NAME);
		utilStmt.setString(2, TEST_EMPLOYEE_LAST_NAME);
		utilStmt.setString(3,  TEST_EMPLOYEE_EMAIL);
		ResultSet rs = utilStmt.executeQuery();
		rs.next();
		TEST_EMPLOYEE_ID = rs.getInt("employee_id");
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		
		utilStmt = realConnection.prepareStatement("delete from employee where employee_id = ?");
		utilStmt.setInt(1, TEST_EMPLOYEE_ID);
		utilStmt.executeUpdate();
		
		if(stmt != null) {
			stmt.close();
		}
		
		realConnection.close();
	}
	
	private void initStmtHelper(String sql) throws SQLException{
		
		//creating a real stmt from a connection
		stmt = realConnection.prepareStatement(sql);
		
		//spying on that real stmt
		spy = Mockito.spy(stmt);
		
		//mock our connection and util, so we will only use the stmt we are spying on
		when(connUtil.createConnection()).thenReturn(connection);
		when(connection.prepareStatement(sql)).thenReturn(spy);
	}
	
	@Test
	public void createEmployeeTest() throws SQLException {
		
		Employee employee = new Employee(TEST_EMPLOYEE_TYPE, TEST_EMPLOYEE_REPORTS_TO, "MarcJ", "RoyJ", "mroy@college.harvard.edu", 0, 0, TEST_EMPLOYEE_DEPARTMENT, TEST_EMPLOYEE_BEN_CO);
		
		try {
			 String sql = "insert into employee (type, reports_to, first_name, last_name, email, award_amount, pending_amount, department, ben_co) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		try {
			employeeDao.createEmployee(employee);
			verify(spy).setInt(1, employee.getType());
			verify(spy).setInt(2, employee.getReportsTo());
			verify(spy).setString(3, employee.getFirstName());
			verify(spy).setString(4, employee.getLastName());
			verify(spy).setString(5,  employee.getEmail());
			verify(spy).setInt(6, employee.getAwardAmount());
			verify(spy).setInt(7, employee.getPendingAmount());
			verify(spy).setInt(8, employee.getDepartment());
			verify(spy).setInt(9, employee.getBenCo());
			verify(spy).executeUpdate();
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		
		utilStmt = realConnection.prepareStatement("delete from employee where first_name = ? and last_name = ? and email = ?");
		utilStmt.setString(1, "Marc");
		utilStmt.setString(2, "Roy");
		utilStmt.setString(3,  "mroy@college.harvard.edu");
		utilStmt.executeUpdate();
	}

	@Test
	public void updateEmployeeTest() throws SQLException {
		
		Employee newInfo = new Employee(TEST_EMPLOYEE_TYPE, TEST_EMPLOYEE_REPORTS_TO, "MarcJ", "RoyJ", "marcjacobroy@gmail.com", 500, 40, TEST_EMPLOYEE_DEPARTMENT, TEST_EMPLOYEE_BEN_CO);
		
		try {
			String sql = "update employee set type = ?, reports_to = ?, first_name = ?, last_name = ?, email = ?, award_amount = ?, pending_amount = ?, department = ?, ben_co = ? where employee_id = ?";
			initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		
		int employeeId = TEST_EMPLOYEE_ID;
		
		employeeDao.updateEmployee(employeeId, newInfo);
		
		
		verify(spy).setInt(1, newInfo.getType());
		verify(spy).setInt(2, newInfo.getReportsTo());
		verify(spy).setString(3, newInfo.getFirstName());
		verify(spy).setString(4, newInfo.getLastName());
		verify(spy).setString(5, newInfo.getEmail());
		verify(spy).setInt(6, newInfo.getAwardAmount());
		verify(spy).setInt(7, newInfo.getPendingAmount());
		verify(spy).setInt(8, newInfo.getDepartment());
		verify(spy).setInt(9, newInfo.getBenCo());
		verify(spy).setInt(10,  employeeId);
		verify(spy).executeUpdate();
		
		utilStmt = realConnection.prepareStatement("delete from employee where first_name = ? and last_name = ? and email = ?");
		utilStmt.setString(1, newInfo.getFirstName());
		utilStmt.setString(2, newInfo.getLastName());
		utilStmt.setString(3, newInfo.getEmail());
		utilStmt.executeUpdate();
	
	}
	
	
	@Test
	public void readEmployeeTest() throws SQLException {
		
		try {
			String sql = "select * from employee where employee_id = ?";
			initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQLException " + e);
		}
		
		utilStmt = realConnection.prepareStatement("select * from employee where employee_id = ?");
		utilStmt.setInt(1, TEST_EMPLOYEE_ID);
		
		ResultSet rs = utilStmt.executeQuery();
		assertTrue(rs.next());
	}


	@Test
	public void deleteEmployeeTest() throws SQLException {
		
		try {
			String sql = "delete from employee where employee_id = ?";;
			initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		
		employeeDao.deleteEmployee(TEST_EMPLOYEE_ID);
		
		
		verify(spy).setInt(1, TEST_EMPLOYEE_ID);
		verify(spy).executeUpdate();
		
		utilStmt = realConnection.prepareStatement("select * from employee where employee_id = ?");
		utilStmt.setInt(1, TEST_EMPLOYEE_ID);
		ResultSet rs = utilStmt.executeQuery();
		
		assertFalse(rs.next());
	}
	
}
