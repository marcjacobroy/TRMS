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

import Tuition.pojos.Event;
import Tuition.util.ConnectionUtil;

@RunWith(MockitoJUnitRunner.class)
public class EventDaoPostgresTest {
	
	public EventDaoPostgres eventDao = new EventDaoPostgres();
	
	@Mock
	private ConnectionUtil connUtil;
	
	@Mock 
	private Connection connection;
	
	private PreparedStatement stmt;
	private PreparedStatement spy;
	private PreparedStatement utilStmt;
	
	private Connection realConnection;
	
	private int TEST_EVENT_ID;
	private String TEST_EVENT_DATE = "2020-12-12";
	private String TEST_EVENT_TIME = "03:22";
	private String TEST_EVENT_LOCATION = "Concord NH";
	private String TEST_EVENT_DESCRIPTION = "Course";
	private int TEST_EVENT_COST = 0;
	private int TEST_EVENT_GRADING_FORMAT = 0;
	private int TEST_EVENT_TYPE = 0;
	
	@Before
	public void setUp() throws Exception {
		
		realConnection = new ConnectionUtil().createConnection();
		
		eventDao.setConnUtil(connUtil);
		
		utilStmt = realConnection.prepareStatement("insert into event (date, time, location, description, cost, grading_format, type) values(?, ?, ?, ?, ?, ?, ?)");
		utilStmt.setString(1, TEST_EVENT_DATE);
		utilStmt.setString(2, TEST_EVENT_TIME);
		utilStmt.setString(3, TEST_EVENT_LOCATION);
		utilStmt.setString(4, TEST_EVENT_DESCRIPTION);
		utilStmt.setInt(5,  TEST_EVENT_COST);
		utilStmt.setInt(6,  TEST_EVENT_GRADING_FORMAT);
		utilStmt.setInt(7,  TEST_EVENT_TYPE);
		utilStmt.executeUpdate();
		
		utilStmt = realConnection.prepareStatement("select event_id from event where date = ? and time = ? and location = ? and description = ? and cost = ? and grading_format = ? and type = ?");
		utilStmt.setString(1, TEST_EVENT_DATE);
		utilStmt.setString(2, TEST_EVENT_TIME);
		utilStmt.setString(3, TEST_EVENT_LOCATION);
		utilStmt.setString(4, TEST_EVENT_DESCRIPTION);
		utilStmt.setInt(5,  TEST_EVENT_COST);
		utilStmt.setInt(6,  TEST_EVENT_GRADING_FORMAT);
		utilStmt.setInt(7,  TEST_EVENT_TYPE);
		ResultSet rs = utilStmt.executeQuery();
		rs.next();
		TEST_EVENT_ID = rs.getInt("Event_id");
		
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		
		utilStmt = realConnection.prepareStatement("delete from Event where event_id = ?");
		utilStmt.setInt(1, TEST_EVENT_ID);
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
	public void createEventTest() throws SQLException, ParseException {
		
		Event event = new Event("2020-12-04", "04:23", "loc", "des", 2, 2, 2);
		
		try {
			 String sql = "insert into event (date, time, location, description, cost, grading_format, type) values(?, ?, ?, ?, ?, ?, ?)";
			 initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		try {
			eventDao.createEvent(event);
			verify(spy).setString(1, event.getDate().toString());
			verify(spy).setString(2, event.getTime().toString());
			verify(spy).setString(3, event.getLocation());
			verify(spy).setString(4, event.getDescription());
			verify(spy).setInt(5, event.getCost());
			verify(spy).setInt(6, event.getGradingFormat());
			verify(spy).setInt(7, event.getType());
			verify(spy).executeUpdate();
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
	}

	@Test
	public void updateEventTest() throws SQLException, ParseException {
		
		Event newInfo = new Event("2020-12-04", "04:23", "loc", "des", 2, 2, 2);
		
		try {
			String sql = "update event set date = ?, time = ?, location = ?, description = ?, cost = ?, grading_format = ?, type = ? where event_id = ?";
			initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		
		eventDao.updateEvent(TEST_EVENT_ID, newInfo); 
		
		verify(spy).setString(1, newInfo.getDate().toString());
		verify(spy).setString(2, newInfo.getTime().toString());
		verify(spy).setString(3, newInfo.getLocation());
		verify(spy).setString(4, newInfo.getDescription());
		verify(spy).setInt(5, newInfo.getCost());
		verify(spy).setInt(6, newInfo.getGradingFormat());
		verify(spy).setInt(7, newInfo.getType());
		verify(spy).setInt(8,  TEST_EVENT_ID);
		verify(spy).executeUpdate();
		
		utilStmt = realConnection.prepareStatement("delete from event where date = ? and time = ? and location = ?");
		utilStmt.setString(1, newInfo.getDate().toString());
		utilStmt.setString(2, newInfo.getTime().toString());
		utilStmt.setString(3, newInfo.getLocation());
		utilStmt.executeUpdate();
	
	}
	
	
	@Test
	public void readEventTest() throws SQLException, ParseException {
		
		String sql = "select * from event where event_id = ?"; 
		try {
			initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQLException " + e);
		}
		
		eventDao.readEvent(TEST_EVENT_ID);
		
		verify(spy).setInt(1, TEST_EVENT_ID);
		verify(spy).executeQuery();
		
		utilStmt = realConnection.prepareStatement(sql);
		utilStmt.setInt(1, TEST_EVENT_ID);
		
		ResultSet rs = utilStmt.executeQuery();
		assertTrue(rs.next());
	}


	@Test
	public void deleteEventTest() throws SQLException {
		
		try {
			String sql = "delete from event where event_id = ?";;
			initStmtHelper(sql);
		} catch(SQLException e) {
			fail("SQL exception thrown: " + e);
		}
		
		eventDao.deleteEvent(TEST_EVENT_ID);
		
		
		verify(spy).setInt(1, TEST_EVENT_ID);
		verify(spy).executeUpdate();
		
		utilStmt = realConnection.prepareStatement("select * from event where event_id = ?");
		utilStmt.setInt(1, TEST_EVENT_ID);
		ResultSet rs = utilStmt.executeQuery();
		
		assertFalse(rs.next());
	}
	
}

