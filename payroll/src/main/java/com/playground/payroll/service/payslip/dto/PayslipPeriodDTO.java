package com.playground.payroll.service.payslip.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO containing a payslip period.
 * 
 * @author Craig Vosloo
 * @version %I%, %G%
 */
public class PayslipPeriodDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date payslipPeriodDate;
	private String displayValue;
		
	public Date getPayslipPeriodDate() {
		return payslipPeriodDate;
	}
	public void setPayslipPeriodDate(Date payslipPeriodDate) {
		this.payslipPeriodDate = payslipPeriodDate;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

}
