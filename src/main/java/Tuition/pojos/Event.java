package Tuition.pojos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	
	private Date date;
	private Date time;
	private String location;
	private String description;
	private int cost; 
	private int gradingFormat;
	private int type;
	
	public Event(String date, String time, String location, String description, int cost, int gradingFormat, int type) throws ParseException {
		super();
		DateFormat formatDate = new SimpleDateFormat("yyyy-mm-dd");
		this.date = formatDate.parse(date);
		DateFormat formatTime = new SimpleDateFormat("H:mm");
		this.time = formatTime.parse(time);
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
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
