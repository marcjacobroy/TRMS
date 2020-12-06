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
		
		int value = Integer.parseInt(ctx.formParam("value"));
		String justification = ctx.formParam("justification");
		boolean awarded = Boolean.parseBoolean(ctx.formParam("awarded"));
		boolean exceeding = Boolean.parseBoolean(ctx.formParam("exceeding"));
		int requestId = Integer.parseInt(ctx.formParam("requestId"));
		
		try {
			awardService.createAward(new Award(value, justification, awarded, exceeding, requestId));
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
		
		log.trace("Entering updateAward in AwardController");
		
		int value = Integer.parseInt(ctx.formParam("value"));
		String justification = ctx.formParam("justification");
		boolean awarded = Boolean.parseBoolean(ctx.formParam("awarded"));
		boolean exceeding = Boolean.parseBoolean(ctx.formParam("exceeding"));
		int requestId = Integer.parseInt(ctx.formParam("requestId"));
		int awardId = Integer.parseInt(ctx.formParam("awardId"));
		
		try {
			awardService.updateAward(awardId, new Award(value, justification, awarded, exceeding, requestId));
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
	
}

