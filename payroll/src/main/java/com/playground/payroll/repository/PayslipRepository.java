package com.playground.payroll.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.playground.payroll.domain.Payslip;

/**
 * JPA Repository for the Payslip domain entity.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see Payslip
 */
public interface PayslipRepository extends CrudRepository<Payslip, Long> {
	
	public List<Payslip> findByEmployeeIdOrderByPayslipDateAsc(Long employeeId);

}
