package com.playground.payroll.domain;

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
	private Integer salaryFrom;
	
	@Column(nullable = false)
	private Integer baseSalary;
	
	@Column(nullable = false)
	private Integer baseSalaryTax;
	
	@Column(nullable = false)
	private Double additionalRate;
	
	protected Tax() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSalaryFrom() {
		return salaryFrom;
	}

	public void setSalaryFrom(Integer salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public Integer getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Integer baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Integer getBaseSalaryTax() {
		return baseSalaryTax;
	}

	public void setBaseSalaryTax(Integer baseSalaryTax) {
		this.baseSalaryTax = baseSalaryTax;
	}

	public Double getAdditionalRate() {
		return additionalRate;
	}

	public void setAdditionalRate(Double additionalRate) {
		this.additionalRate = additionalRate;
	}

}
