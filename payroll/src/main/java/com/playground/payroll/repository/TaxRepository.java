package com.playground.payroll.repository;

import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;

import com.playground.payroll.domain.Employee;
import com.playground.payroll.domain.Tax;

/**
 * JPA Repository for the Tax domain entity.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see Employee
 */
public interface TaxRepository extends CrudRepository<Tax, Long> {

	public Tax findFirstBySalaryFromLessThanEqualOrderBySalaryFromDesc(BigDecimal salary);	
}
