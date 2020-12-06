package Tuition.service;

import java.util.List;

import Tuition.pojos.Message;

public interface MessageService {

	public void createMessage(Message m);
	
	public List<Message> readMessagesByEmployee(String employeeId);
}
