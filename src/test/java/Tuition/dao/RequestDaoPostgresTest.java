package Tuition.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import Tuition.pojos.Request;
import Tuition.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class RequestDaoPostgresTest {
	
	public RequestDaoPostgres requestDao = new RequestDaoPostgres();
	
	@Mock
	private ConnectionUtil connUtil;
	
	@Mock 
	private Connection connection;
	
	private PreparedStatement stmt;
	private PreparedStatement spy;
	private PreparedStatement utilStmt;
	
	private Connection realConnection;
	
	private int TEST_REQUEST_ID;
	private int TEST_REQUEST_EMPLOYEE_ID = 13;
	private int TEST_REQUEST_EVENT_ID = 16;
	private String TEST_REQUEST_JUSTIFICATION = "just";
	private String TEST_REQUEST_DATE = "2020-10-01";
	private boolean TEST_REQUEST_DS_APPROVED = true;
	private boolean TEST_REQUEST_DH_APPROVED = false;
	private boolean TEST_REQUEST_BEN_CO_APPROVED = false;
	private int TEST_REQUEST_CURRENT_WORKER = 2;
	private boolean TEST_REQUEST_COMPLETE = false;
	private boolean TEST_REQUEST_URGENT = false;
	private String TEST_REQUEST_ATTACHMENT = "attach";
	private int TEST_REQUEST_HOURS_MISSED = 3;
	private String TEST_REQUEST_DS_APPROVAL_PROOF = "none";
	private String TEST_REQUEST_DH_APPROVAL_PROOF = "none";
	
	
	@Before
	public void setUp() throws Exception {
		
		realConnection = new ConnectionUtil().createConnection();
		
		requestDao.setConnUtil(connUtil);
		
		utilStmt = realConnection.prepareStatement("insert into request (employee_id, event_id, justification, date, ds_approved, "
				+ "dh_approved, ben_co_approved, current_worker, complete, urgent, attachment, hours_missed, "
				+ "ds_approval_proof, dh_approval_proof) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		utilStmt.setInt(1, TEST_REQUEST_EMPLOYEE_ID);
		utilStmt.setInt(2, TEST_REQUEST_EVENT_ID);
		utilStmt.setString(3, TEST_REQUEST_JUSTIFICATION);
		utilStmt.setString(4, TEST_REQUEST_DATE);
		utilStmt.setBoolean(5, TEST_REQUEST_DS_APPROVED);
		utilStmt.setBoolean(6, TEST_REQUEST_DH_APPROVED);
		utilStmt.setBoolean(7,  TEST_REQUEST_BEN_CO_APPROVED);
		utilStmt.setInt(8,  TEST_REQUEST_CURRENT_WORKER);
		utilStmt.setBoolean(9 , TEST_REQUEST_COMPLETE);
		utilStmt.setBoolean(10,  TEST_REQUEST_URGENT);
		utilStmt.setString(11, TEST_REQUEST_ATTACHMENT);
		utilStmt.setInt(12,  TEST_REQUEST_HOURS_MISSED);
		utilStmt.setString(13, TEST_REQUEST_DS_APPROVAL_PROOF);
		utilStmt.setString(14,  TEST_REQUEST_DH_APPROVAL_PROOF);
		utilStmt.executeUpdate();
		
		utilStmt = realConnection.prepareStatement("select request_id from request where employee_id = ? and event_id = ?");
		utilStmt.setInt(1,  TEST_REQUEST_EMPLOYEE_ID);
		utilStmt.setInt(2,  TEST_REQUEST_EVENT_ID);
		ResultSet rs = utilStmt.executeQuery();
		rs.next();
		TEST_REQUEST_ID = rs.getInt("request_id");
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		
		utilStmt = realConnection.prepareStatement("delete from request where request_id = ?");
		utilStmt.setInt(1, TEST_REQUEST_ID);
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
	public void createRequestTest() throws SQLException {
		
		Request request = new Request(TEST_REQUEST_EMPLOYEE_ID, TEST_REQUEST_EVENT_ID, "hi", "yo", true, true, false, 3, false, false, "attach", 4, "none", "none1");
		
		try {
			 String sql = "insert into request (employee_id, event_id, justification, date, ds_approved, "
						+ "dh_approved, ben_co_approved, current_worker, complete, urgent, attachment, hours_missed, "
						+ "ds_approval_proof, dh_approval_proof) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		try {
			requestDao.createRequest(request);
			verify(spy).setInt(1, request.getEmployeeId());
			verify(spy).setInt(2, request.getEventId());
			verify(spy).setString(3, request.getJustification());
			verify(spy).setString(4, request.getDate());
			verify(spy).setBoolean(5, request.isDsApproved());
			verify(spy).setBoolean(6, request.isDhApproved());
			verify(spy).setBoolean(7,  request.isBenCoApproved());
			verify(spy).setInt(8,  request.getCurrentWorker());
			verify(spy).setBoolean(9 , request.isComplete());
			verify(spy).setBoolean(10,  request.isUrgent());
			verify(spy).setString(11, request.getAttachment());
			verify(spy).setInt(12,  request.getHoursMissed());
			verify(spy).setString(13, request.getDsApprovalProof());
			verify(spy).setString(14,  request.getDhApprovalProof());
			verify(spy).executeUpdate();
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		
		utilStmt = realConnection.prepareStatement("delete from request where employee_id = ? and event_id = ? and date = ?");
		utilStmt.setInt(1, request.getEmployeeId());
		utilStmt.setInt(2, request.getEventId());
		utilStmt.setString(3, request.getDate());
		utilStmt.executeUpdate();
		
	}

	@Test
	public void updateEventTest() throws SQLException, ParseException {
		
		Request request = new Request(TEST_REQUEST_EMPLOYEE_ID, TEST_REQUEST_EVENT_ID, "hi", "yo", true, true, false, 3, false, false, "attach", 4, "none", "none1");
		
		try {
			String sql = "update request set employee_id = ?, event_id = ?, justification = ?, date = ?, "
					+ "ds_approved = ?, dh_approved = ?, ben_co_approved = ?, current_worker = ?, "
					+ "complete = ?, urgent = ?, attachment = ?, hours_missed = ?, ds_approval_proof = ?, "
					+ "dh_approval_proof = ? where request_id = ?";
			initStmtHelper(sql);
		} catch(SQLException e) { 
			fail("SQL exception thrown: " + e);
		}
		
		requestDao.updateRequest(TEST_REQUEST_ID, request); 
		
		verify(spy).setInt(1, request.getEmployeeId());
		verify(spy).setInt(2, request.getEventId());
		verify(spy).setString(3, request.getJustification());
		verify(spy).setString(4, request.getDate());
		verify(spy).setBoolean(5, request.isDsApproved());
		verify(spy).setBoolean(6, request.isDhApproved());
		verify(spy).setBoolean(7,  request.isBenCoApproved());
		verify(spy).setInt(8,  request.getCurrentWorker());
		verify(spy).setBoolean(9 , request.isComplete());
		verify(spy).setBoolean(10,  request.isUrgent());
		verify(spy).setString(11, request.getAttachment());
		verify(spy).setInt(12,  request.getHoursMissed());
		verify(spy).setString(13, request.getDsApprovalProof());
		verify(spy).setString(14,  request.getDhApprovalProof());
		verify(spy).setInt(15,  TEST_REQUEST_ID);
		verify(spy).executeUpdate();
	}
	
	
	@Test
	public void readRequestTest() throws SQLException {
		
		String sql = "select * from request where request_id = ?"; 
		try {
			initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQLException " + e);
		}
		
		requestDao.readRequest(TEST_REQUEST_ID);
		
		verify(spy).setInt(1, TEST_REQUEST_ID);
		verify(spy).executeQuery();
		
		utilStmt = realConnection.prepareStatement(sql);
		utilStmt.setInt(1, TEST_REQUEST_ID);
		
		ResultSet rs = utilStmt.executeQuery();
		assertTrue(rs.next());
	}


	@Test
	public void deleteEventTest() throws SQLException {
		
		try {
			String sql = "delete from request where request_id = ?";;
			initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		
		requestDao.deleteRequest(TEST_REQUEST_ID);
		
		
		verify(spy).setInt(1, TEST_REQUEST_ID);
		verify(spy).executeUpdate();
		
		utilStmt = realConnection.prepareStatement("select * from request where request_id = ?");
		utilStmt.setInt(1, TEST_REQUEST_ID);
		ResultSet rs = utilStmt.executeQuery();
		
		assertFalse(rs.next());
	}
	

}
