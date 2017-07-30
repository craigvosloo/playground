package com.playground.payroll.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * JPA Entity for the Tax Table
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
@Entity
public class Tax {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private BigDecimal salaryFrom;
	
	@Column(nullable = false)
	private BigDecimal baseSalary;
	
	@Column(nullable = false)
	private BigDecimal baseSalaryTax;
	
	@Column(nullable = false)
	private BigDecimal additionalRate;
	
	protected Tax() {}

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
