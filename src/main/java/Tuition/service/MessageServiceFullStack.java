package Tuition.service;

import java.util.List;

import Tuition.dao.MessageDao;
import Tuition.dao.MessageDaoPostgres;
import Tuition.pojos.Message;

public class MessageServiceFullStack implements MessageService {

	MessageDao messageDao = new MessageDaoPostgres();
	
	@Override
	public void createMessage(Message m) {
		messageDao.createMessage(m);

	}

	@Override
	public List<Message> readMessagesByEmployee(String employeeId) {
		return messageDao.readMessagesByEmployee(employeeId);
	}

}
