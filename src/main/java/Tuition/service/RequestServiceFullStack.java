package Tuition.service;

import java.util.List;

import org.apache.log4j.Logger;

import Tuition.dao.RequestDao;
import Tuition.dao.RequestDaoPostgres;
import Tuition.pojos.Request;

public class RequestServiceFullStack implements RequestService {
	
	private static Logger log = Logger.getRootLogger();
	
	RequestDao requestDao = new RequestDaoPostgres();

	@Override
	public void createRequest(Request r) {
		log.trace("Calling createRequest in RequestServiceFullStack on " + r);
		requestDao.createRequest(r);

	}

	@Override
	public Request readRequest(int requestId) {
		log.trace("Calling readRequest in RequestServiceFullStack on " + requestId);
		return requestDao.readRequest(requestId);
		
	}

	@Override
	public void updateRequest(int requestId, Request r) {
		log.trace("Calling updateRequest in RequestServiceFullStack on " + requestId + " and " + r);
		requestDao.updateRequest(requestId, r);

	}

	@Override
	public void deleteRequest(int requestId) {
		log.trace("Calling deleteRequest in RequestServiceFullStack on " + requestId);
		requestDao.deleteRequest(requestId);

	}

	@Override
	public List<Request> readRequestsByEmployee(int employeeId) {
		
		log.trace("Calling readRequestsByEmployee in RequestServiceFullStack on " + employeeId);
		return requestDao.readRequestsByEmployee(employeeId);
	}

	@Override
	public List<Request> readRequestsByDirectSupervisor(int employeeId) {
		log.trace("Calling readRequestsByDirectSupervisor in RequestServiceFullStack on " + employeeId);
		return requestDao.readRequestsByDirectSupervisor(employeeId);
	}

	@Override
	public List<Request> readRequestsByDepartmentHead(int employeeId) {
		log.trace("Calling readRequestsByDepartmentHead in RequestServiceFullStack on " + employeeId);
		return requestDao.readRequestsByDepartmentHead(employeeId);
	}

	@Override
	public List<Request> readRequestsByBenCo(int employeeId) {
		log.trace("Calling readRequestsByBenCo in RequestServiceFullStack on " + employeeId);
		return requestDao.readRequestsByBenCo(employeeId);
	}

}
