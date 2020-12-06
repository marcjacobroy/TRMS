package Tuition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.log4j.Logger;

import Tuition.pojos.Event;
import Tuition.util.ConnectionUtil;
import Tuition.util.HelperFunctions;

public class EventDaoPostgres implements EventDao {

	
	private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement stmt;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}
	
	@Override
	public Event createEvent(Event e) {
		
		log.debug("Entering createGuest in EmployeeDaoPostgres on " + e);
		
		String sql = "insert into event (date, time, location, description, cost, grading_format, type) values(?, ?, ?, ?, ?, ?, ?)"; 
		
		try {
			Connection connection = connUtil.createConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, e.getDate().toString());
			stmt.setString(2, e.getTime().toString());
			stmt.setString(3, e.getLocation());
			stmt.setString(4, e.getDescription());
			stmt.setInt(5, e.getCost());
			stmt.setInt(6, e.getGradingFormat());
			stmt.setInt(7,  e.getType());
			stmt.executeUpdate();
			
			sql = "select * from event where date = ? and time = ? and location =  ? and description = ? and cost = ? and grading_format = ? and type = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, e.getDate().toString());
			stmt.setString(2, e.getTime().toString());
			stmt.setString(3, e.getLocation());
			stmt.setString(4, e.getDescription());
			stmt.setInt(5, e.getCost());
			stmt.setInt(6, e.getGradingFormat());
			stmt.setInt(7,  e.getType());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return makeEvent(rs);
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}

	}

	@Override
	public Event readEvent(int eventId) throws ParseException {
		log.debug("Calling readEvent in EventDaoPostgres on " + eventId);
		
		String sql = "select * from event where event_id = ?";
			
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, eventId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String date = rs.getString("date");
				String time = rs.getString("time");
				String location = rs.getString("location");
				String description = rs.getString("description");
				int cost = rs.getInt("cost");
				int gradingFormat = rs.getInt("grading_format");
				int type = rs.getInt("type");
				return new Event(date, time, location, description, cost, gradingFormat, type);
			} else {
				log.warn("Called on non existant event");
				throw new IllegalArgumentException("Event with id " + eventId + " does not exist");
			}
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateEvent(int eventId, Event e) {
		log.debug("Calling updateEvent in EventDaoPostgres on " + eventId + " " + e);
		
		String sql = "update event set date = ?, time = ?, location = ?, description = ?, cost = ?, grading_format = ?, type = ? where event_id = ?";
		
		if (HelperFunctions.eventExists(eventId)) {
			try (Connection conn = connUtil.createConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, e.getDate().toString());
				stmt.setString(2, e.getTime().toString());
				stmt.setString(3, e.getLocation());
				stmt.setString(4, e.getDescription());
				stmt.setInt(5, e.getCost());
				stmt.setInt(6, e.getGradingFormat());
				stmt.setInt(7, e.getType());
				stmt.setInt(8,  eventId);
				stmt.executeUpdate();
			} catch (SQLException exc) {
				log.warn("Exception thrown " + String.valueOf(exc));
				exc.printStackTrace();
			}
		} else {
			log.warn("Called on non existant event");
			throw new IllegalArgumentException("Event with id " + eventId + " does not exist");
		}
	}

	@Override
	public void deleteEvent(int eventId) {
		log.debug("Calling deleteEvent in EventDaoPostgres on " + eventId);
		
		String sql = "delete from event where event_id = ?";
		
		if (HelperFunctions.eventExists(eventId)) {
			try (Connection conn = connUtil.createConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, eventId);
				stmt.executeUpdate();
			} catch (SQLException exc) {
				log.warn("Exception thrown " + String.valueOf(exc));
				exc.printStackTrace();
			}
		} else {
			log.warn("Called on non existant event");
			throw new IllegalArgumentException("Event with id " + eventId + " does not exist");
		}

	}
	
	@Override
	public Event readEventOfRequest(int requestId) {
		
		log.debug("Calling readEventOfRequest in EventDaoPostgres on " + requestId);
		
		String sql = "select e.* from event e, request r where e.event_id = r.event_id and r.request_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, requestId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return makeEvent(rs);
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
		
	}
	
	public Event makeEvent(ResultSet rs) throws SQLException {
		
		String date = rs.getString("date");
		String time = rs.getString("time");
		String location = rs.getString("location");
		String description = rs.getString("description");
		int cost = rs.getInt("cost");
		int gradingFormat = rs.getInt("grading_format");
		int type = rs.getInt("type");
		try {
			Event e = new Event(date, time, location, description, cost, gradingFormat, type);
			e.setEventId(rs.getInt("event_id"));
			return e;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	

}
