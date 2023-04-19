package com.emp.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	List<Employee> findByEmpName(String empName);
	
	List<Employee> findByEmpNameContaining(String empName);
	
	
}