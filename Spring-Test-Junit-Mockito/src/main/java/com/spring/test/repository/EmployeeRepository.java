package com.spring.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.test.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
