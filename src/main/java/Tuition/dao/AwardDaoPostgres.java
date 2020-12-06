package Tuition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import Tuition.pojos.Award;
import Tuition.util.ConnectionUtil;

public class AwardDaoPostgres implements AwardDao {
	
	private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement stmt;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}
	
	@Override
	public void createAward(Award award) {
		log.debug("Entering createAward in AwardDaoPostgres on " + award);
		
		String sql = "insert into award (request_id, value, justification, awarded, exceeding) values(?, ?, ?, ?, ?)"; 
		
		try {
			Connection connection = connUtil.createConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, award.getRequestId());
			stmt.setInt(2,  award.getValue());
			stmt.setString(3, award.getJustification());
			stmt.setBoolean(4,  award.isAwarded());
			stmt.setBoolean(5,  award.isExceeding());
			stmt.executeUpdate();
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
		}

	}

	@Override
	public Award readAward(int awardId) {
		log.debug("Calling readAward in AwardDaoPostgres on " + awardId);
		
		String sql = "select * from award where award_id = ?";
			
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, awardId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int requestId = rs.getInt("request_id");
				int value = rs.getInt("value");
				String justification = rs.getString("justification");
				boolean awarded = rs.getBoolean("awarded");
				boolean exceeding = rs.getBoolean("exceeding");
				return new Award(value, justification, awarded, exceeding, requestId);
			} else {
				log.warn("Called on non existant award");
				throw new IllegalArgumentException("Award with id " + awardId + " does not exist");
			}
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateAward(int awardId, Award award) {
		log.debug("Entering updateAward in AwardDaoPostgres on " + award);
		
		String sql = "update award set request_id = ?, value = ?, justification = ?, awarded = ?, exceeding = ? where award_id = ?";
		
		try {
			Connection connection = connUtil.createConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, award.getRequestId());
			stmt.setInt(2,  award.getValue());
			stmt.setString(3, award.getJustification());
			stmt.setBoolean(4,  award.isAwarded());
			stmt.setBoolean(5,  award.isExceeding());
			stmt.setInt(6,  awardId);
			stmt.executeUpdate();
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
		}

	}

	@Override
	public void deleteAward(int awardId) {
		log.debug("Calling deleteAward in AwardDaoPostgres on " + awardId);
		
		String sql = "delete from award where award_id = ?";
			
		try (Connection conn = connUtil.createConnection()) {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, awardId);
		} catch (SQLException exc) {
			log.warn("Threw exception" + String.valueOf(exc));
		}
	}
}