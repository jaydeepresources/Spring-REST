package com.emp.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeePagingRepository employeePagingRepository;

	// get all employees
	@GetMapping("/employees/all")
	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	// get all employees by pages
	@GetMapping("/employees/results/{pageNo}/{pageSize}")
	public List<Employee> getEmployeesByPaging(@PathVariable("pageNo") int pageNo,
			@PathVariable("pageSize") int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Employee> pageResult = employeePagingRepository.findAll(pageable);
		return pageResult.getContent();
	}

	// sort employees by empName
	@GetMapping("/employees/sort/name")
	public List<Employee> sortEmployeesByName() {
		Sort nameSort = Sort.by("empName");
		return (List<Employee>) employeePagingRepository.findAll(nameSort);
	}

	// sort and page employees
	@GetMapping("/employees/results/{pageNo}/{pageSize}/{sortBy}")
	public List<Employee> getSortedEmployeePage(@PathVariable("pageNo") int pageNo,
			@PathVariable("pageSize") int pageSize, @PathVariable("sortBy") String sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		// Sort.by(sortBy).descending()
		Page<Employee> pagedResult = employeePagingRepository.findAll(paging);
		return pagedResult.getContent();

	}

	// find employee by Id
	@GetMapping("/employees/find/id/{empId}")
	public Employee findById(@PathVariable("empId") int empId) {
		Optional<Employee> optional = null;
		try {
			optional = employeeRepository.findById(empId);
			return optional.get();
		} catch (Exception e) {
			throw new EmployeeNotFoundException();
		}
	}

	// find by employee name
	@GetMapping("/employees/find/name/{empName}")
	public List<Employee> findByName(@PathVariable("empName") String empName) {
		List<Employee> list = null;

		try {
			list = employeeRepository.findByEmpName(empName);
		} catch (Exception e) {
			throw new EmployeeNotFoundException();
		}

		return list;

	}

	// add a new employee
	@PostMapping("/employees/save")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// update a new employee
	@PutMapping("/employees/save")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	// delete a new employee
	@DeleteMapping("/employees/delete/{empId}")
	public String deleteEmployee(@PathVariable("empId") int empId) {
		try {
			employeeRepository.deleteById(empId);
			return "true";
		} catch (Exception e) {
			throw new EmployeeNotFoundException();
		}
	}
}
