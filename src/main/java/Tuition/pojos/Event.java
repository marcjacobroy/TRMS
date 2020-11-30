package Tuition.pojos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	
	private String date;
	private String time;
	private String location;
	private String description;
	private int cost; 
	private int gradingFormat;
	private int type;
	private int eventId;
	
	
	
	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Event(String date, String time, String location, String description, int cost, int gradingFormat, int type) throws ParseException {
		super();
		this.date = date;
		this.time = time; 
		this.location = location;
		this.description = description;
		this.cost = cost; 
		this.gradingFormat = gradingFormat;
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(int gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Event [date=" + date + ", time=" + time + ", location=" + location + ", description=" + description
				+ ", cost=" + cost + ", gradingFormat=" + gradingFormat + ", type=" + type + "]";
	}
	
	
	
	
	
	

}
