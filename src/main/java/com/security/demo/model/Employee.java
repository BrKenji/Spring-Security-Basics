package com.security.demo.model;

public class Employee {
	private final Integer employeeId;
	private final String employeeName;
	
	public Employee(Integer studentId, String studentName) {
		this.employeeId = studentId;
		this.employeeName = studentName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	
}
