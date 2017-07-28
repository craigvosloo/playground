package com.playground.payroll.service.tax;

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
	private Double calculateAnnualTax(Integer salary) {
		Tax tax = taxRepository.findFirstBySalaryFromLessThanEqualOrderBySalaryFromDesc(salary);
		Double annualTax = new Double(tax.getBaseSalaryTax());
		annualTax = annualTax + ((salary - tax.getBaseSalary()) * (new Double(tax.getAdditionalRate()) / 100));
		return annualTax;		
	}
	
	/**
	 * Method to calculate monthly tax for a salary
	 * @param Integer the salary value
	 * @return Integer the monthly tax amount
	 */
	@Override
	public Integer calculateMonthlyTax(Integer salary) {
		return new Long(Math.round(calculateAnnualTax(salary) / 12)).intValue();
	}
}
