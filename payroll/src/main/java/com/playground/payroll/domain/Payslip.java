package com.playground.payroll.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * JPA Entity for the Payslip
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
@Entity
public class Payslip {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private Date payslipDate;
	
	@Column(nullable = false)
	private BigDecimal grossIncome;
	
	@Column(nullable = false)
	private BigDecimal incomeTax;
	
	@Column(nullable = false)
	private BigDecimal netIncome;
	
	@Column(nullable = false)
	private BigDecimal pensionContribution;
	
	@Column(nullable = false)
	private Boolean prorated;
	
	@ManyToOne
	@JoinColumn(name="employee_id", nullable = false)
	private Employee employee;	
	
	public Payslip() {}

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}