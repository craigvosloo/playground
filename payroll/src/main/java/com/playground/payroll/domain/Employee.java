package com.playground.payroll.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OrderBy;

/**
 * JPA Entity for the Employee
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
@Entity
public class Employee {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private BigDecimal annualSalary;
	
	@Column(nullable = false)
	private BigDecimal pensionContribution;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
    @OrderBy(clause = "payslipDate")
	private List<Payslip> payslips;
	
	protected Employee() {}

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public List<Payslip> getPayslips() {
		return payslips;
	}

	public void setPayslips(List<Payslip> payslips) {
		this.payslips = payslips;
	}	
}