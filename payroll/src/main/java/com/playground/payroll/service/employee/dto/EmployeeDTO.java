package com.playground.payroll.service.employee.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.playground.payroll.service.payslip.dto.PayslipDTO;

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
	@DecimalMin("1.00")
	private BigDecimal annualSalary;
	
	@NotNull(message = "error.employee.pensioncontribution.notnull")
	@DecimalMin("1.00")
	private BigDecimal pensionContribution;
	
	@NotNull(message = "error.employee.startdate.notnull")
	private Date startDate;
	
	private List<PayslipDTO> payslips;
	
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
	public List<PayslipDTO> getPayslips() {
		return payslips;
	}
	public void setPayslips(List<PayslipDTO> payslips) {
		this.payslips = payslips;
	}
}
