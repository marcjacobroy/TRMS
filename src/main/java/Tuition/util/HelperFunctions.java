package Tuition.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

public class HelperFunctions {
	
	private static Logger log = Logger.getRootLogger();
	
	public static boolean isValidEmailAddress(String email) {
//		   boolean result = true;
//		   try {
//		      InternetAddress emailAddr = new InternetAddress(email);
//		      emailAddr.validate();
//		   } catch (AddressException ex) {
//		      result = false;
//		   }
//		   return result;
		return true;
	}
	
	
	
	// check if employee with employeeId exists in our database 
	public static boolean employeeExists(int employeeId) {
		
		log.debug("Entering employeeExists in util on " + employeeId);
		
		PreparedStatement stmt; 
		ConnectionUtil connUtil = new ConnectionUtil();
		
		String verify = "select * from employee where employee_id = ?";
		
		try (Connection conn = connUtil.createConnection()){
			stmt = conn.prepareStatement(verify);
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				log.warn("Called on non existant employee");
				return false;
			}
		} catch(SQLException e) {
			log.warn("Exception thrown " + String.valueOf(e));
			e.printStackTrace();
		}
		return true;
	}
	
	// check if event with eventId exists in our database 
		public static boolean eventExists(int eventId) {
			
			log.debug("Entering employeeExists in util on " + eventId);
			
			PreparedStatement stmt; 
			ConnectionUtil connUtil = new ConnectionUtil();
			
			String verify = "select * from event where event_id = ?";
			
			try (Connection conn = connUtil.createConnection()){
				stmt = conn.prepareStatement(verify);
				stmt.setInt(1, eventId);
				ResultSet rs = stmt.executeQuery();
				if (!rs.next()) {
					log.warn("Called on non existant event");
					return false;
				}
			} catch(SQLException e) {
				log.warn("Exception thrown " + String.valueOf(e));
				e.printStackTrace();
			}
			return true;
		}
		
		// check if request with requestId exists in our database 
				public static boolean requestExists(int requestId) {
					
					log.debug("Entering requestExists in util on " + requestId);
					
					PreparedStatement stmt; 
					ConnectionUtil connUtil = new ConnectionUtil();
					
					String verify = "select * from request where request_id = ?";
					
					try (Connection conn = connUtil.createConnection()){
						stmt = conn.prepareStatement(verify);
						stmt.setInt(1, requestId);
						ResultSet rs = stmt.executeQuery();
						if (!rs.next()) {
							log.warn("Called on non existant request");
							return false;
						}
					} catch(SQLException e) {
						log.warn("Exception thrown " + String.valueOf(e));
						e.printStackTrace();
					}
					return true;
				}


}
