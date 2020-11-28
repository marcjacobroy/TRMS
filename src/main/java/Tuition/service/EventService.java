package Tuition.service;

import java.text.ParseException;

import Tuition.pojos.Event;

public interface EventService {
	
	public void createEvent(Event e);
	
	public Event readEvent(int eventId) throws ParseException;
	
	public void updateEvent(int eventId, Event e);
	
	public void deleteEvent(int eventId);

}
