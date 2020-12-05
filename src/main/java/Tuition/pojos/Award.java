package Tuition.pojos;

public class Award {
	
	private int value;
	private String justification;
	private boolean awarded;
	private boolean exceeding;
	private int requestId;
	
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
	public Award(int value, String justification, boolean awarded, boolean exceeding, int requestId) {
		super();
		this.value = value;
		this.justification = justification;
		this.awarded = awarded;
		this.exceeding = exceeding;
		this.requestId = requestId;
	}
	

}
