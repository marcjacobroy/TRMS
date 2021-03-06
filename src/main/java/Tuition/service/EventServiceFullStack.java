package Tuition.service;

import java.text.ParseException;

import org.apache.log4j.Logger;

import Tuition.dao.EventDao;
import Tuition.dao.EventDaoPostgres;
import Tuition.pojos.Event;

public class EventServiceFullStack implements EventService {

	private static Logger log = Logger.getRootLogger();
	
	EventDao eventDao = new EventDaoPostgres();
	
	@Override
	public Event createEvent(Event e) {
		log.trace("Calling createEvent in EventServiceFullStack on " + e);
		return eventDao.createEvent(e);

	}

	@Override
	public Event readEvent(int eventId) throws ParseException {
		log.trace("Calling readEvent in EventServiceFullStack on " + eventId);
		return eventDao.readEvent(eventId);
	}

	@Override
	public void updateEvent(int eventId, Event e) {
		log.trace("Calling updateEvent in EventServiceFullStack on " + eventId + " and " + e);
		eventDao.updateEvent(eventId, e);
	}

	@Override
	public void deleteEvent(int eventId) {
		log.trace("Calling deleteEvent in EventServiceFullStack on " + eventId);
		eventDao.deleteEvent(eventId);

	}

	@Override
	public Event readEventOfRequest(int requestId) {
		log.trace("Calling readEventOfRequest in EventServiceFulStack");
		return eventDao.readEventOfRequest(requestId);
		
	}

}
