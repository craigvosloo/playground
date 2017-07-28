package com.playground.payroll.domain;

import java.util.Date;
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
	private Integer annualSalary;
	
	@Column(nullable = false)
	private Integer pensionContribution;
	
	@Column(nullable = false)
	private Date paymentStartDate;
	
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

	public List<Payslip> getPayslips() {
		return payslips;
	}

	public void setPayslips(List<Payslip> payslips) {
		this.payslips = payslips;
	}	
}