package Tuition.pojos;

public class Request {
	
	private int employeeId;
	private int eventId;
	private String justification;
	private String date;
	private boolean dsApproved;
	private boolean dhApproved;
	private boolean benCoApproved;
	private int currentWorker;
	private boolean complete;
	private boolean urgent;
	private String attachment;
	private int hoursMissed;
	private String dsApprovalProof;
	private String dhApprovalProof;
	private int requestId;
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public Request(int employeeId, int eventId, String justification, String date, boolean dsApproved,
			boolean dhApproved, boolean benCoApproved, int currentWorker, boolean complete, boolean urgent,
			String attachment, int hoursMissed, String dsApprovalProof, String dhApprovalProof) {
		super();
		this.employeeId = employeeId;
		this.eventId = eventId;
		this.justification = justification;
		this.date = date;
		this.dsApproved = dsApproved;
		this.dhApproved = dhApproved;
		this.benCoApproved = benCoApproved;
		this.currentWorker = currentWorker;
		this.complete = complete;
		this.urgent = urgent;
		this.attachment = attachment;
		this.hoursMissed = hoursMissed;
		this.dsApprovalProof = dsApprovalProof;
		this.dhApprovalProof = dhApprovalProof;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isDsApproved() {
		return dsApproved;
	}

	public void setDsApproved(boolean dsApproved) {
		this.dsApproved = dsApproved;
	}

	public boolean isDhApproved() {
		return dhApproved;
	}

	public void setDhApproved(boolean dhApproved) {
		this.dhApproved = dhApproved;
	}

	public boolean isBenCoApproved() {
		return benCoApproved;
	}

	public void setBenCoApproved(boolean benCoApproved) {
		this.benCoApproved = benCoApproved;
	}

	public int getCurrentWorker() {
		return currentWorker;
	}

	public void setCurrentWorker(int currentWorker) {
		this.currentWorker = currentWorker;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public int getHoursMissed() {
		return hoursMissed;
	}

	public void setHoursMissed(int hoursMissed) {
		this.hoursMissed = hoursMissed;
	}

	public String getDsApprovalProof() {
		return dsApprovalProof;
	}

	public void setDsApprovalProof(String dsApprovalProof) {
		this.dsApprovalProof = dsApprovalProof;
	}

	public String getDhApprovalProof() {
		return dhApprovalProof;
	}

	public void setDhApprovalProof(String dhApprovalProof) {
		this.dhApprovalProof = dhApprovalProof;
	}

	@Override
	public String toString() {
		return "Request [employeeId=" + employeeId + ", eventId=" + eventId + ", justification=" + justification
				+ ", date=" + date + ", dsApproved=" + dsApproved + ", dhApproved=" + dhApproved + ", benCoApproved="
				+ benCoApproved + ", currentWorker=" + currentWorker + ", complete=" + complete + ", urgent=" + urgent
				+ ", attachment=" + attachment + ", hoursMissed=" + hoursMissed + ", dsApprovalProof=" + dsApprovalProof
				+ ", dhApprovalProof=" + dhApprovalProof + "]";
	}
	
	
	
	

}
