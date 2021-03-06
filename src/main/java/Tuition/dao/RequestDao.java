package Tuition.dao;

import java.text.ParseException;
import java.util.List;

import Tuition.pojos.Request;

public interface RequestDao {
	
	public void createRequest(Request r);
	
	public Request readRequest(int requestId);
	
	public void updateRequest(int requestId, Request r);
	
	public void deleteRequest(int requestId);

	public List<Request> readRequestsByEmployee(int employeeId);

	public List<Request> readRequestsByDirectSupervisor(int employeeId);

	public List<Request> readRequestsByDepartmentHead(int employeeId);

	public List<Request> readRequestsByBenCo(int employeeId);

}
