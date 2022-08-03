package com.security.demo.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	// to create a role with no permissions use empty newHashSet()
	EMPLOYEE(Sets.newHashSet(ApplicationUserPermission.SHIFT_READ)), 
	ADMIN(Sets.newHashSet(ApplicationUserPermission.EMPLOYEE_READ,
			ApplicationUserPermission.EMPLOYEE_WRITE,
			ApplicationUserPermission.SHIFT_READ,
			ApplicationUserPermission.SHIFT_WRITE)),
	SUB_ADMIN(Sets.newHashSet(ApplicationUserPermission.EMPLOYEE_READ,
			ApplicationUserPermission.SHIFT_READ));
	
	private final Set<ApplicationUserPermission> permissions;
	
	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthority(){
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissions;
	}
	
}
