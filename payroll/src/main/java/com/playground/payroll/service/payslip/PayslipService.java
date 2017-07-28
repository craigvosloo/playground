package com.playground.payroll.service.payslip;

import java.util.Date;

import org.springframework.validation.annotation.Validated;

import com.playground.payroll.service.payslip.dto.PayslipDTO;

/**
 * Interface for the Payslip Service.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see PayslipServiceImpl
 * @since 1.0
 */
@Validated
public interface PayslipService {
	
	/**@see PayslipServiceImpl#generatePayslip(Long,Date) */
	public PayslipDTO generatePayslip(Long employeeId, Date paymentDate);

}
