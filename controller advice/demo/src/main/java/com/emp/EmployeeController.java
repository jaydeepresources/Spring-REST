package com.emp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	// Local list of employees - not connected to db
	ArrayList<Employee> employees = new ArrayList<>();

	// returns all employees
	@GetMapping("employees/all")
	public List<Employee> getEmployees() {
		return employees;
	}

	// add a new employee
	@PostMapping("employees/add")
	public Employee addEmployee(@RequestBody Employee employee) {
		if (employees.stream().anyMatch(e->e.getEmpId() == employee.getEmpId()))
			throw new DuplicateEmployeeException();
		
		employees.add(employee);
		return employee;
	}

	// update an existing employee
	@PutMapping("employees/update")
	public Employee updateEmployee(@RequestBody Employee employee) {

		for (Employee emp : employees) {
			if (emp.getEmpId().equals(employee.getEmpId())) {
				emp.setEmpName(employee.getEmpName());
				emp.setEmpSalary(employee.getEmpSalary());
				return employee;
			}
		}
		
		throw new EmployeeNotFoundException();
	}

	// delete an existing employee
	@DeleteMapping("employees/delete/{empId}")
	public String deleteEmployee(@PathVariable("empId") int empId) {

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getEmpId().equals(empId)) {
				employees.remove(i);
				return "true";
			}
		}

		throw new EmployeeNotFoundException();
	}

}