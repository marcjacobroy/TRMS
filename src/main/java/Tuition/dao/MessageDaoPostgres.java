package Tuition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import java.util.List;

import Tuition.pojos.Message;
import Tuition.util.ConnectionUtil;

public class MessageDaoPostgres implements MessageDao {

	
	private static Logger log = Logger.getRootLogger();
	
	private PreparedStatement stmt;
	
	private ConnectionUtil connUtil = new ConnectionUtil();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}
	
	@Override
	public void createMessage(Message m) {
		
		log.debug("Entering createMessage in MessageDaoPostgres on " + m);
		
		String sql = "insert into message (sender, recipient_id, contents, subject) values(?, ?, ?, ?)"; 
		
		try {
			Connection connection = connUtil.createConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, m.getSender());
			stmt.setString(2,  m.getRecipientId());;
			stmt.setString(3,  m.getContents());
			stmt.setString(4,  m.getSubject());
			stmt.executeUpdate();
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
		}
	}

	@Override
	public List<Message> readMessagesByEmployee(String employeeId) {
		
		log.debug("Entering readMessage in MessageDaoPostgres on " + employeeId);
		
		String sql = "select * from message where recipient_id = ?"; 
		
		try {
			Connection connection = connUtil.createConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			List<Message> messageList = new ArrayList<>();
			while(rs.next()) {
				messageList.add(makeMessage(rs));
			};
			return messageList;
			
			
		} catch (SQLException exc) {
			log.warn("Exception thrown " + String.valueOf(exc));
			exc.printStackTrace();
			return null;
		}
	}
	
	public Message makeMessage(ResultSet rs) throws SQLException {
		
		String sender = rs.getString("sender");
		String recipientId = rs.getString("recipient_id");
		String contents = rs.getString("contents");
		String subject = rs.getString("subject");
		Message m = new Message(contents, sender, recipientId, subject);
		return m;
	}

}
