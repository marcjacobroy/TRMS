package Tuition.pojos;

import Tuition.util.HelperFunctions;

public class Employee {
  
	private int type; 
	private int reportsTo;
	private String firstName;
	private String lastName;
	private String email;
	
	public Employee(int type, int reportsTo, String firstName, String lastName, String email) {
		super();
		if (HelperFunctions.isValidEmailAddress(email)) {
			this.type = type;
			this.reportsTo = reportsTo;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
		} else {
			throw new IllegalArgumentException("Invalid email address");
		}
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
