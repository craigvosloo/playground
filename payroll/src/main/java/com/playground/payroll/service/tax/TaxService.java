package com.playground.payroll.service.tax;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

/**
 * Interface for the Tax Service.
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see TaxServiceImpl
 * @since 1.0
 */
@Validated
public interface TaxService {
	
	/**@see TaxServiceImpl#calculateMonthlyTax(Integer) */
	public BigDecimal calculateMonthlyTax(BigDecimal salary);

}
