package com.playground.payroll.service.payslip;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.playground.payroll.service.payslip.dto.PayslipDTO;
import com.playground.payroll.service.payslip.dto.PayslipPeriodDTO;
import com.playground.payroll.service.payslip.dto.PayslipRequestDTO;

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
	
	/**@see PayslipServiceImpl#generatePayslip(PayslipRequestDTO) */
	public PayslipDTO generatePayslip(PayslipRequestDTO payslipRequestDTO);
	
	/**@see PayslipServiceImpl#savePayslip(Long, PayslipDTO) */
	public PayslipDTO savePayslip(Long employeeId, PayslipDTO payslipDTO);
	
	/**@see PayslipServiceImpl#getPayslipByEmployee(Long) */
	public List<PayslipDTO> getPayslipsByEmployee(Long employeeId);
	
	/**@see PayslipServiceImpl#getPayslipPeriodsForEmployee(Long) */
	public List<PayslipPeriodDTO> getPayslipPeriodsForEmployee(Long employeeId);

}
