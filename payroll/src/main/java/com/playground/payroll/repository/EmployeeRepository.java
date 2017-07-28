package com.playground.payroll.repository;

import org.springframework.data.repository.CrudRepository;

import com.playground.payroll.domain.Employee;

/**
 * JPA Repository for the Employee domain entity.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see Employee
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
