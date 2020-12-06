package Tuition.controller;

import org.apache.log4j.Logger;

import Tuition.pojos.Award;
import Tuition.service.AwardService;
import Tuition.service.AwardServiceFullStack;
import io.javalin.http.Context;

public class AwardController {

	private static Logger log = Logger.getRootLogger();
	
	AwardService awardService = new AwardServiceFullStack();
	
	public void createAward(Context ctx) {
		
		log.trace("Entering createAward in AwardController");
		System.out.println("createAwardController");
		
		int value = Integer.parseInt(ctx.formParam("value"));
		String justification = ctx.formParam("justification");
		boolean awarded = Boolean.parseBoolean(ctx.formParam("awarded"));
		boolean exceeding = Boolean.parseBoolean(ctx.formParam("exceeding"));
		int requestId = Integer.parseInt(ctx.formParam("requestId"));
		
		try {
			awardService.createAward(new Award(value, justification, awarded, exceeding, requestId, false));
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
			log.warn("Exception was thrown " + String.valueOf(e));
		}
		
		
	}
	
	public void readAward(Context ctx) {
		
		log.trace("Entering readAward in AwardController");
		
		int awardId = Integer.parseInt(ctx.formParam("awardId"));
		try {
			ctx.json(awardService.readAward(awardId));
		} catch(Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}

	public void updateAward(Context ctx) {
		
		System.out.println("updateAwardController");
		
		log.trace("Entering updateAward in AwardController");
		
		int value = Integer.parseInt(ctx.formParam("value"));
		String justification = ctx.formParam("justification");
		boolean awarded = Boolean.parseBoolean(ctx.formParam("awarded"));
		boolean exceeding = Boolean.parseBoolean(ctx.formParam("exceeding"));
		int requestId = Integer.parseInt(ctx.formParam("requestId"));
		int awardId = Integer.parseInt(ctx.formParam("awardId"));
		boolean accepted = Boolean.parseBoolean(ctx.formParam("accepted"));
		
		try {
			awardService.updateAward(awardId, new Award(value, justification, awarded, exceeding, requestId, accepted));
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
			log.warn("Exception was thrown " + String.valueOf(e));
		}
	}
	
	public void deleteAward(Context ctx) {
		
		log.trace("Entering deleteAward in AwardController");
		
		
		int awardId = Integer.parseInt(ctx.formParam("awardId"));
		try {
			awardService.deleteAward(awardId);
			ctx.html("Deleted award " + awardId);
		} catch(Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}
	
	public void readAwardsByEmployeeId(Context ctx) {
		
		log.trace("Entering readAwardsByEmployeeId in AwardController");
		
		int employeeId = Integer.parseInt(ctx.formParam("employeeId"));
		
		try {
			ctx.json(awardService.readAwardsByEmployeeId(employeeId));
		} catch(Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}
	
}

