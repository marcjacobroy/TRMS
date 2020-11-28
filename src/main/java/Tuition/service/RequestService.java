package Tuition.service;


import Tuition.pojos.Request;

public interface RequestService {

	public void createRequest(Request r);
	
	public Request readRequest(int requestId);
	
	public void updateRequest(int requestId, Request r);
	
	public void deleteRequest(int requestId);
}
