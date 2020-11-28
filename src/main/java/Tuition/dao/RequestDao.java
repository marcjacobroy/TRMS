package Tuition.dao;

import java.text.ParseException;

import Tuition.pojos.Request;

public interface RequestDao {
	
	public void createRequest(Request r);
	
	public Request readRequest(int requestId);
	
	public void updateRequest(int requestId, Request r);
	
	public void deleteRequest(int requestId);

}
