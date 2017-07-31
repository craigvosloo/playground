package com.playground.payroll.service.employee.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * DTO containing an employee.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 */
public class EmployeeDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private Long id;
	
	@NotNull(message = "error.employee.firstname.notnull")
	private String firstName;
	
	@NotNull(message = "error.employee.lastname.notnull")
	private String lastName;
	
	@NotNull(message = "error.employee.annualsalary.notnull")
	private BigDecimal annualSalary;
	
	@NotNull(message = "error.employee.pensioncontribution.notnull")
	private BigDecimal pensionContribution;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull(message = "error.employee.startdate.notnull")
	private LocalDate startDate;
	
	private String displayDate;
	
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
		this.displayDate = startDate.format(dtf);
	}
	public String getDisplayDate() {
		return displayDate;
	}
}
