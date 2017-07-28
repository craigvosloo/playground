package com.playground.payroll.service.payslip.dto;

import java.util.Date;

/**
 * DTO containing a payslip record.
 * 
 * @author Craig Vosloo
 * @version %I%, %G%
 */
public class PayslipDTO {

	private Long id;
	private Date payslipDate;
	private Integer grossIncome;
	private Integer incomeTax;
	private Integer netIncome;
	private Integer pensionContribution;
	private Long employeeId;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getPayslipDate() {
		return payslipDate;
	}
	public void setPayslipDate(Date payslipDate) {
		this.payslipDate = payslipDate;
	}
	public Integer getGrossIncome() {
		return grossIncome;
	}
	public void setGrossIncome(Integer grossIncome) {
		this.grossIncome = grossIncome;
	}
	public Integer getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(Integer incomeTax) {
		this.incomeTax = incomeTax;
	}
	public Integer getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(Integer netIncome) {
		this.netIncome = netIncome;
	}
	public Integer getPensionContribution() {
		return pensionContribution;
	}
	public void setPensionContribution(Integer pensionContribution) {
		this.pensionContribution = pensionContribution;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
