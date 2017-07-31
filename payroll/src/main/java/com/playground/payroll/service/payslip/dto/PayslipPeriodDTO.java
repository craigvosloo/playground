package com.playground.payroll.service.payslip.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * DTO containing a payslip period.
 * 
 * @author Craig Vosloo
 * @version %I%, %G%
 */
public class PayslipPeriodDTO implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM yyyy");
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate payslipPeriodDate;
	
	private String displayValue;
		
	public LocalDate getPayslipPeriodDate() {
		return payslipPeriodDate;
	}
	public void setPayslipPeriodDate(LocalDate payslipPeriodDate) {
		this.payslipPeriodDate = payslipPeriodDate;
		this.displayValue = payslipPeriodDate.format(dtf);
	}
	public String getDisplayValue() {
		return displayValue;
	}

}
