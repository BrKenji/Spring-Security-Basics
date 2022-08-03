package com.security.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.demo.model.Employee;

@RestController
@RequestMapping("/management/api/v1/employees")
public class EmployeeManagementController {
	
	private static final List<Employee> EMPLOYEES = Arrays.asList(
			new Employee(1, "Jona"),
			new Employee(2, "Mateo"),
			new Employee(3, "Garret"),
			new Employee(4, "Sandra"),
			new Employee(5, "Kelly"),
			new Employee(6, "Gleen"));

	// hasRole('ROLE_') hasAnyRole(ROLE_) hasAuthority('permission') hasAnyAuthority('permission')
	@GetMapping
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUB_ADMIN')")
	public List<Employee> getAllEmployees(){
		return EMPLOYEES;
	}
	
	@PostMapping
	//@PreAuthorize("hasAuthority('employee:write')")
	public void registerNewEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
	}
	
	@DeleteMapping(path = "{employeeId}")
	//@PreAuthorize("hasAuthority('employee:write')")
	public void deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
		System.out.println(employeeId);
	}
	
	@PutMapping(path = "{employeeId}")
	//@PreAuthorize("hasAuthority('employee:write')")
	public void updateEmployee(@PathVariable("employeeId") Integer employeeId, @RequestBody Employee employee) {
		System.out.println(String.format("%s %s", employeeId, employee));
	}
}
