package com.playground.payroll.service.payslip.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull
	private LocalDate payslipDate;
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public LocalDate getPayslipDate() {
		return payslipDate;
	}
	public void setPayslipDate(LocalDate payslipDate) {
		this.payslipDate = payslipDate;
	}   	

}
