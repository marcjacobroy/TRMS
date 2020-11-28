package Tuition.dao;

import Tuition.pojos.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import Tuition.util.ConnectionUtil;
import Tuition.util.HelperFunctions;

public class RequestDaoPostgres implements RequestDao {

	private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement stmt;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}
	
	@Override
	public void createRequest(Request r) {
		
		log.debug("Entering createRequest in RequestDaoPostgres on " + r);
		
		String sql = "insert into request (employee_id, event_id, justification, date, ds_approved, "
				+ "dh_approved, ben_co_approved, current_worker, complete, urgent, attachment, hours_missed, "
				+ "ds_approval_proof, dh_approval_proof) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			Connection connection = connUtil.createConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, r.getEmployeeId());
			stmt.setInt(2, r.getEventId());
			stmt.setString(3, r.getJustification());
			stmt.setString(4, r.getDate());
			stmt.setBoolean(5, r.isDsApproved());
			stmt.setBoolean(6, r.isDhApproved());
			stmt.setBoolean(7,  r.isBenCoApproved());
			stmt.setInt(8,  r.getCurrentWorker());
			stmt.setBoolean(9 , r.isComplete());
			stmt.setBoolean(10,  r.isUrgent());
			stmt.setString(11, r.getAttachment());
			stmt.setInt(12,  r.getHoursMissed());
			stmt.setString(13, r.getDsApprovalProof());
			stmt.setString(14,  r.getDhApprovalProof());
			stmt.executeUpdate();
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
		}
		

	}

	@Override
	public Request readRequest(int requestId) {
		
		log.debug("Entering readRequest in RequestDaoPostgres on " + requestId);
		
		String sql = "select * from request where request_id = ?";
		
		try (Connection connection = connUtil.createConnection()){
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, requestId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				int eventId = rs.getInt("event_id");
				String justification = rs.getString("justification");
				String date = rs.getString("date");
				boolean dsApproved = rs.getBoolean("ds_approved");
				boolean dhApproved = rs.getBoolean("dh_approved");
				boolean benCoApproved = rs.getBoolean("ben_co_approved");
				int currentWorker = rs.getInt("current_worker");
				boolean complete = rs.getBoolean("complete");
				boolean urgent = rs.getBoolean("urgent");
				String attachment = rs.getString("attachment");
				int hoursMissed = rs.getInt("hours_missed");
				String dsApprovalProof = rs.getString("ds_approval_proof");
				String dhApprovalProof = rs.getString("dh_approval_proof");
				return new Request(employeeId, eventId, justification, date, dsApproved, dhApproved, benCoApproved, currentWorker, complete, urgent, attachment, hoursMissed, dsApprovalProof, dhApprovalProof);
			} else {
				log.warn("Called on non existant request");
				throw new IllegalArgumentException("Request with id " + requestId + " does not exist");
			}
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateRequest(int requestId, Request r) {
		
		log.debug("Calling updateRequest in RequestDaoPostgres on " + requestId + " " + r);
		
		String sql = "update request set employee_id = ?, event_id = ?, justification = ?, date = ?, "
				+ "ds_approved = ?, dh_approved = ?, ben_co_approved = ?, current_worker = ?, complete = ?, "
				+ "urgent = ?, attachment = ?, hours_missed = ?, ds_approval_proof = ?, dh_approval_proof = ? "
				+ "where request_id = ?";
		
		if (HelperFunctions.requestExists(requestId)) {
			try (Connection conn = connUtil.createConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, r.getEmployeeId());
				stmt.setInt(2, r.getEventId());
				stmt.setString(3, r.getJustification());
				stmt.setString(4, r.getDate());
				stmt.setBoolean(5, r.isDsApproved());
				stmt.setBoolean(6, r.isDhApproved());
				stmt.setBoolean(7,  r.isBenCoApproved());
				stmt.setInt(8,  r.getCurrentWorker());
				stmt.setBoolean(9 , r.isComplete());
				stmt.setBoolean(10,  r.isUrgent());
				stmt.setString(11, r.getAttachment());
				stmt.setInt(12,  r.getHoursMissed());
				stmt.setString(13, r.getDsApprovalProof());
				stmt.setString(14,  r.getDhApprovalProof());
				stmt.setInt(15,  requestId);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				log.warn("Exception thrown " + String.valueOf(e));
				e.printStackTrace();
			}
		} else {
			log.warn("Called on non existant request");
			throw new IllegalArgumentException("Request with id " + requestId + " does not exist");
			
		}

	}

	@Override
	public void deleteRequest(int requestId) {
		
		log.debug("Calling deleteRequest in RequestDaoPostgres on " + requestId);
		
		String sql = "delete from request where request_id = ?";
		
		if (HelperFunctions.requestExists(requestId)) {
			try (Connection conn = connUtil.createConnection()) {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, requestId);
				stmt.executeUpdate();
			} catch (SQLException exc) {
				log.warn("Exception thrown " + String.valueOf(exc));
				exc.printStackTrace();
			}
		} else {
			log.warn("Called on non existant request");
			throw new IllegalArgumentException("Request with id " + requestId + " does not exist");
		}

	}

}
