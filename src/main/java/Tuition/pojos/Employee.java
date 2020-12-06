package Tuition.pojos;

import Tuition.util.HelperFunctions;

public class Employee {
  
	private int type; 
	private int reportsTo;
	private String firstName;
	private String lastName;
	private String email;
	private int awardAmount;
	private int pendingAmount;
	private int department;
	private int benCo;
	private int employeeId;
	
	public int getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}



	public Employee(int type, int reportsTo, String firstName, String lastName, String email, int awardAmount,
			int pendingAmount, int department, int benCo) {
		super();
		this.type = type;
		this.reportsTo = reportsTo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.awardAmount = awardAmount;
		this.pendingAmount = pendingAmount;
		this.department = department;
		this.benCo = benCo;
	}
	
	

	public Employee(int type, int reportsTo, String firstName, String lastName, String email, int department,
			int benCo) {
		super();
		this.type = type;
		this.reportsTo = reportsTo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.benCo = benCo;
		this.awardAmount = 0;
		this.pendingAmount = 0;
	}



	public int getAwardAmount() {
		return awardAmount;
	}

	public void setAwardAmount(int awardAmount) {
		this.awardAmount = awardAmount;
	}

	public int getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(int pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getBenCo() {
		return benCo;
	}

	public void setBenCo(int benCo) {
		this.benCo = benCo;
	}

	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (HelperFunctions.isValidEmailAddress(email)) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Invalid email address");
		}
		
	}

	@Override
	public String toString() {
		return "Employee [type=" + type + ", reportsTo=" + reportsTo + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + "]";
	}
	
	
}
