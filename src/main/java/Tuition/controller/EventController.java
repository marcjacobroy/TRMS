package Tuition.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import Tuition.pojos.Event;
import Tuition.service.EventService;
import Tuition.service.EventServiceFullStack;
import io.javalin.http.Context;

public class EventController {

	private static Logger log = Logger.getRootLogger();
	
	EventService eventService = new EventServiceFullStack();
	
	public void createEvent(Context ctx) {
		
		log.trace("Entering createEvent in EventController");
		
		String date = ctx.formParam("date");
		
		String time = ctx.formParam("time");
		
		String location = ctx.formParam("location");
		
		String description = ctx.formParam("description");
		
		int cost = Integer.parseInt(ctx.formParam("cost"));
		
		int gradingFormat = Integer.parseInt(ctx.formParam("gradingFormat"));
		
		int type = Integer.parseInt(ctx.formParam("type"));
		
		try {
			List<Event> eventList = new ArrayList<>();
			eventList.add(eventService.createEvent(new Event(date, time, location, description, cost, gradingFormat, type)));
			ctx.json(eventList);
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
			log.warn("Exception was thrown " + String.valueOf(e));
		}
	}
	 
	public void readEvent(Context ctx) {
		
		log.trace("Entering readEvent in EventController");
		
		int eventId = Integer.parseInt(ctx.formParam("eventId"));
		try {
			ctx.json(eventService.readEvent(eventId));
		} catch(Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}

	
	public void updateEvent(Context ctx) {
		
		log.trace("Entering updateEvent in EventController");
		
		String date = ctx.formParam("date");
		
		String time = ctx.formParam("time");
		
		String location = ctx.formParam("location");
		
		String description = ctx.formParam("description");
		
		int cost = Integer.parseInt(ctx.formParam("cost"));
		
		int gradingFormat = Integer.parseInt(ctx.formParam("gradingFormat"));
		
		int type = Integer.parseInt(ctx.formParam("type"));
		
		int eventId = Integer.parseInt(ctx.formParam("eventId"));
		
		try {
			eventService.updateEvent(eventId, (new Event(date, time, location, description, cost, gradingFormat, type)));
			ctx.html("Updated event");
		} catch (Exception e) {
			ctx.html(String.valueOf(e));
			log.warn("Exception was thrown " + String.valueOf(e));
		}
	}
	
	
	// delete a card given id (cascading)
	public void deleteEvent(Context ctx) {
		
		log.trace("Entering deleteEvent in EventdController");
		
		int eventId = Integer.valueOf(ctx.formParam("eventId"));
	
		try {
			eventService.deleteEvent(eventId);
			ctx.html("Deleted event " + eventId);
		} catch (Exception e) {
			log.warn("Exception was thrown " + String.valueOf(e));
			ctx.html(String.valueOf(e));
		}
	}
}
