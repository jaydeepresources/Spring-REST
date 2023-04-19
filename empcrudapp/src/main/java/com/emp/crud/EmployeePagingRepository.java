package com.emp.crud;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeePagingRepository extends PagingAndSortingRepository<Employee, Integer> {

}
