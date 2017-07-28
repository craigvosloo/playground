package com.playground.payroll.domain;

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
	private Integer grossIncome;
	
	@Column(nullable = false)
	private Integer incomeTax;
	
	@Column(nullable = false)
	private Integer netIncome;
	
	@Column(nullable = false)
	private Integer pensionContribution;
	
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}