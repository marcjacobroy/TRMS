package Tuition.controller;

import io.javalin.http.Context;
import org.apache.log4j.Logger;

import Tuition.pojos.Message;
import Tuition.service.MessageService;
import Tuition.service.MessageServiceFullStack;

import java.util.List;

public class MessageController {
	
	MessageService messageService = new MessageServiceFullStack();
	
	public void createMessage(Context ctx) {
		
		String sender = ctx.formParam("sender");
		int recipientId = Integer.parseInt(ctx.formParam("recipientId"));
		String contents = ctx.formParam("contents");
	
		messageService.createMessage(new Message(contents, sender, recipientId));
	}
	
	public void readMessagesByEmployee(Context ctx) {
		
		int employeeId = Integer.parseInt(ctx.formParam(("employeeId")));
		
		List<Message> messageList = messageService.readMessagesByEmployee(employeeId);
		ctx.json(messageList);
	}

}
