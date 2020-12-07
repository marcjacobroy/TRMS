package Tuition.pojos;

public class Grade {
	
	private int requestId;
	private String grade;
	private int gradeId;
	
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Grade(int requestId, String grade) {
		super();
		this.requestId = requestId;
		this.grade = grade;
	}
	
	
	
}
