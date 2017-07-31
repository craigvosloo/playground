package com.playground.payroll.service.payslip.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * DTO used to request the generation of a pyalips for an employee.
 * 
 * @author Craig Vosloo
 * @version %I%, %G%
 */
public class PayslipRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long employeeId;
	@NotNull
	private Date payslipDate;
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Date getPayslipDate() {
		return payslipDate;
	}
	public void setPayslipDate(Date payslipDate) {
		this.payslipDate = payslipDate;
	}   	

}
