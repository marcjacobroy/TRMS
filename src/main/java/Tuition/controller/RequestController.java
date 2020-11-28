package Tuition.controller;

import org.apache.log4j.Logger;

import Tuition.pojos.Request;
import Tuition.service.RequestService;
import Tuition.service.RequestServiceFullStack;
import io.javalin.http.Context;

public class RequestController {
	
	private static Logger log = Logger.getRootLogger();
	
	RequestService requestService = new RequestServiceFullStack();
	
	public void createRequest(Context ctx) {
		
		log.trace("Entering createRequest in RequestController");
		
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		int eventId = Integer.parseInt(ctx.formParam("eventId"));
		String justification = ctx.formParam("justification");
		String date = ctx.formParam("date");
		boolean dsApproved = Boolean.parseBoolean(ctx.formParam("dsApproved"));
		boolean dhApproved = Boolean.parseBoolean(ctx.formParam("dhApproved"));
		boolean benCoApproved = Boolean.parseBoolean(ctx.formParam("benCoApproved"));
		int currentWorker = Integer.parseInt(ctx.formParam("currentWorker"));
		boolean complete = Boolean.parseBoolean(ctx.formParam("complete"));
		boolean urgent = Boolean.parseBoolean(ctx.formParam("urgent"));
		String attachment = ctx.formParam("attachment");
		int hoursMissed = Integer.parseInt(ctx.formParam("hoursMissed"));
		String dsApprovalProof = ctx.formParam("dsApprovalProof");
		String dhApprovalProof = ctx.formParam("dhApprovalProof");

		try {
			requestService.createRequest(new Request(employeeId, eventId, justification, date, dsApproved, dhApproved, benCoApproved, currentWorker, complete, urgent, attachment, hoursMissed, dsApprovalProof, dhApprovalProof));
			ctx.html("Created event");
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
			log.warn("Exception was thrown " + String.valueOf(e));
		}

	}
	
	public void readRequest(Context ctx) {
		
		log.trace("Entering readRequest in RequestController");
		
		int requestId = Integer.parseInt(ctx.formParam("requestId"));
		try {
			ctx.html(requestService.readRequest(requestId).toString());
		} catch(Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}
	
	public void updateRequest(Context ctx) {
		
		log.trace("Entering updateRequest in RequestController");
		
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		int eventId = Integer.parseInt(ctx.formParam("eventId"));
		String justification = ctx.formParam("justification");
		String date = ctx.formParam("date");
		boolean dsApproved = Boolean.parseBoolean(ctx.formParam("dsApproved"));
		boolean dhApproved = Boolean.parseBoolean(ctx.formParam("dhApproved"));
		boolean benCoApproved = Boolean.parseBoolean(ctx.formParam("benCoApproved"));
		int currentWorker = Integer.parseInt(ctx.formParam("currentWorker"));
		boolean complete = Boolean.parseBoolean(ctx.formParam("complete"));
		boolean urgent = Boolean.parseBoolean(ctx.formParam("urgent"));
		String attachment = ctx.formParam("attachment");
		int hoursMissed = Integer.parseInt(ctx.formParam("hoursMissed"));
		String dsApprovalProof = ctx.formParam("dsApprovalProof");
		String dhApprovalProof = ctx.formParam("dhApprovalProof");
		int requestId = Integer.parseInt(ctx.formParam("requestId"));

		try {
			requestService.updateRequest(requestId, new Request(employeeId, eventId, justification, date, dsApproved, dhApproved, benCoApproved, currentWorker, complete, urgent, attachment, hoursMissed, dsApprovalProof, dhApprovalProof));
			ctx.html("Created event");
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
			log.warn("Exception was thrown " + String.valueOf(e));
		}
		
		

	}
	
	// delete a request given id (cascading)
		public void deleteRequest(Context ctx) {
			
			log.trace("Entering deleteRequest in RequestController");
			
			int requestId = Integer.valueOf(ctx.formParam("requestId"));
		
			try {
				requestService.deleteRequest(requestId);
				ctx.html("Deleted requset " + requestId);
			} catch (Exception e) {
				log.warn("Exception was thrown " + String.valueOf(e));
				ctx.html(String.valueOf(e));
			}
		}

}
