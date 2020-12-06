package Tuition.pojos;

public class Award {
	
	private int value;
	private String justification;
	private boolean awarded;
	private boolean exceeding;
	private int requestId;
	private boolean accepted;
	private int awardId;
	
	public int getAwardId() {
		return awardId;
	}
	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public boolean isAwarded() {
		return awarded;
	}
	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}
	public boolean isExceeding() {
		return exceeding;
	}
	public void setExceeding(boolean exceeding) {
		this.exceeding = exceeding;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public Award(int value, String justification, boolean awarded, boolean exceeding, int requestId, boolean accepted) {
		super();
		this.value = value;
		this.justification = justification;
		this.awarded = awarded;
		this.exceeding = exceeding;
		this.requestId = requestId;
		this.accepted = accepted;
	}
	

}
