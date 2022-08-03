package com.security.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.demo.model.Employee;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
	
	private static final List<Employee> EMPLOYEES = Arrays.asList(
			new Employee(1, "Jona"),
			new Employee(2, "Mateo"),
			new Employee(3, "Garret"),
			new Employee(4, "Sandra"),
			new Employee(5, "Kelly"),
			new Employee(6, "Gleen"));
	
	@GetMapping(path = "{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") Integer employeeId) {
		return EMPLOYEES.stream()
				.filter(student -> employeeId.equals(student.getEmployeeId()))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Employee " + employeeId + "does not exist"));
				
	}
	
}
