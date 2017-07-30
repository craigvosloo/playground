package com.playground.payroll.service.tax.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO containing a tax table record.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
public class TaxDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private BigDecimal salaryFrom;	
	private BigDecimal baseSalary;	
	private BigDecimal baseSalaryTax;	
	private BigDecimal additionalRate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getSalaryFrom() {
		return salaryFrom;
	}
	public void setSalaryFrom(BigDecimal salaryFrom) {
		this.salaryFrom = salaryFrom;
	}
	public BigDecimal getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(BigDecimal baseSalary) {
		this.baseSalary = baseSalary;
	}
	public BigDecimal getBaseSalaryTax() {
		return baseSalaryTax;
	}
	public void setBaseSalaryTax(BigDecimal baseSalaryTax) {
		this.baseSalaryTax = baseSalaryTax;
	}
	public BigDecimal getAdditionalRate() {
		return additionalRate;
	}
	public void setAdditionalRate(BigDecimal additionalRate) {
		this.additionalRate = additionalRate;
	}

}
