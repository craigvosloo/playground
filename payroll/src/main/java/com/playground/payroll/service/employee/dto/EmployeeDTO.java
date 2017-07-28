package com.playground.payroll.service.employee.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	private String firstName;
	private String lastName;
	private Integer annualSalary;
	private Integer pensionContribution;	
	private Date paymentStartDate;
	
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
	public Integer getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(Integer annualSalary) {
		this.annualSalary = annualSalary;
	}
	public Integer getPensionContribution() {
		return pensionContribution;
	}
	public void setPensionContribution(Integer pensionContribution) {
		this.pensionContribution = pensionContribution;
	}
	public Date getPaymentStartDate() {
		return paymentStartDate;
	}
	public void setPaymentStartDate(Date paymentStartDate) {
		this.paymentStartDate = paymentStartDate;
	}
	public List<PayslipDTO> getPayslips() {
		return payslips;
	}
	public void setPayslips(List<PayslipDTO> payslips) {
		this.payslips = payslips;
	}
}
