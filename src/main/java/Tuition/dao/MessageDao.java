package Tuition.dao;

import java.util.List;

import Tuition.pojos.Message;

public interface MessageDao {
	
	public void createMessage(Message m);
	
	public List<Message> readMessagesByEmployee(String employeeId);

}
