package com.playground.payroll.service.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playground.payroll.domain.Tax;
import com.playground.payroll.repository.TaxRepository;

/**
 * Service used for all methods regarding Tax
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see TaxService
 * @since 1.0
 */
@Service("taxService")
public class TaxServiceImpl implements TaxService {

	@Autowired
	private TaxRepository taxRepository;
	
	/**
	 * Method to calculate annual tax for a salary
	 * @param Integer the salary value
	 * @return Integer the annual tax amount
	 */
	private BigDecimal calculateAnnualTax(BigDecimal salary) {
		Tax tax = taxRepository.findFirstBySalaryFromLessThanEqualOrderBySalaryFromDesc(salary);
		BigDecimal annualTax = tax.getBaseSalaryTax().add(salary.subtract(tax.getBaseSalary()).multiply(tax.getAdditionalRate().divide(new BigDecimal(100))));
		return annualTax;		
	}
	
	/**
	 * Method to calculate monthly tax for a salary
	 * @param Integer the salary value
	 * @return Integer the monthly tax amount
	 */
	@Override
	public BigDecimal calculateMonthlyTax(BigDecimal salary) {
		return calculateAnnualTax(salary).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP);
	}
}
