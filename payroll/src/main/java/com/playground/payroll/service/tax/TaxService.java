package com.playground.payroll.service.tax;

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
	public Integer calculateMonthlyTax(Integer salary);

}
