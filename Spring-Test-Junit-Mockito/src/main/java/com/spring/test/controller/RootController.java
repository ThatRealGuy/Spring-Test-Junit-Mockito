package com.spring.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.test.entity.Employee;
import com.spring.test.service.EmployeeService;

@RestController
public class RootController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/user/add")
	public Employee add(@RequestBody Employee employee) {
		return employeeService.add(employee);
	}
	
	@PostMapping("/user/delete/{id}")
	public void delete(@PathVariable Long id) {
		employeeService.delete(id);
	}
	
	@GetMapping("/user/getAll")
	public List<Employee> getAll() {
		return employeeService.getAll();
	}
	
	@GetMapping("/user/getAll/{id}")
	public Employee getById(@PathVariable Long id) {
		return employeeService.getByID(id);
	}	
}
