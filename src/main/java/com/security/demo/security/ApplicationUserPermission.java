package com.security.demo.security;

public enum ApplicationUserPermission {
	EMPLOYEE_READ("emplyee:read"),
	EMPLOYEE_WRITE("employee:write"),
	SHIFT_READ("shift:read"),
	SHIFT_WRITE("shift:write");
	
	private final String permission;
	
	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
}
