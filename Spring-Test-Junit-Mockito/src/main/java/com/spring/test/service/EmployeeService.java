package com.spring.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.entity.Employee;
import com.spring.test.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee add(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}
	
	public Employee getByID(Long id) {
		return employeeRepository.findById(id).get();
	}	
	
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}		
}
