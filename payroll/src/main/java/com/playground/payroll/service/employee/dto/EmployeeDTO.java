package com.playground.payroll.service.employee.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * DTO containing an employee.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
public class EmployeeDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message = "error.employee.firstname.notnull")
	private String firstName;
	
	@NotNull(message = "error.employee.lastname.notnull")
	private String lastName;
	
	@NotNull(message = "error.employee.annualsalary.notnull")
	private BigDecimal annualSalary;
	
	@NotNull(message = "error.employee.pensioncontribution.notnull")
	private BigDecimal pensionContribution;
	
	@NotNull(message = "error.employee.startdate.notnull")
	private Date startDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}
	public BigDecimal getPensionContribution() {
		return pensionContribution;
	}
	public void setPensionContribution(BigDecimal pensionContribution) {
		this.pensionContribution = pensionContribution;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
