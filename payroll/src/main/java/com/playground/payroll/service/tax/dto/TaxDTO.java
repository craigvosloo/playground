package com.playground.payroll.service.tax.dto;

import java.io.Serializable;

/**
 * DTO containing a tax table record.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
public class TaxDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private Integer salaryFrom;	
	private Integer baseSalary;	
	private Integer baseSalaryTax;	
	private Double additionalRate;
	
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
