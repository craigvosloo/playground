package com.playground.payroll.service.payslip.dto;

import java.math.BigDecimal;
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
	private BigDecimal grossIncome;
	private BigDecimal incomeTax;
	private BigDecimal netIncome;
	private BigDecimal pensionContribution;
	private Boolean prorated;
	private String payslipDisplayDate;

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
	public BigDecimal getGrossIncome() {
		return grossIncome;
	}
	public void setGrossIncome(BigDecimal grossIncome) {
		this.grossIncome = grossIncome;
	}
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}
	public BigDecimal getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}
	public BigDecimal getPensionContribution() {
		return pensionContribution;
	}
	public void setPensionContribution(BigDecimal pensionContribution) {
		this.pensionContribution = pensionContribution;
	}
	public Boolean getProrated() {
		return prorated;
	}
	public void setProrated(Boolean prorated) {
		this.prorated = prorated;
	}
	public String getPayslipDisplayDate() {
		return payslipDisplayDate;
	}
	public void setPayslipDisplayDate(String payslipDisplayDate) {
		this.payslipDisplayDate = payslipDisplayDate;
	}
}
