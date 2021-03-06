package Tuition.dao;

import java.text.ParseException;

import Tuition.pojos.Event;

public interface EventDao {
	
	public Event createEvent(Event e);
	
	public Event readEvent(int eventId) throws ParseException;
	
	public void updateEvent(int eventId, Event e);
	
	public void deleteEvent(int eventId);

	public Event readEventOfRequest(int requestId);

}
