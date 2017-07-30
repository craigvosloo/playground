package com.playground.payroll.service.payslip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playground.payroll.domain.Employee;
import com.playground.payroll.domain.Payslip;
import com.playground.payroll.repository.EmployeeRepository;
import com.playground.payroll.repository.PayslipRepository;
import com.playground.payroll.service.payslip.dto.PayslipDTO;
import com.playground.payroll.service.tax.TaxService;

/**
 * Service used for all methods regarding payslips
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see PayslipService
 * @since 1.0
 */
@Service("payslipService")
public class PayslipServiceImpl implements PayslipService {
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PayslipRepository payslipRepository;
	
	@Autowired
	private TaxService taxService;

	@Override
	public PayslipDTO generatePayslip(Long employeeId, Date payslipDate) {
		Payslip payslip = new Payslip();
		
		Employee employee = employeeRepository.findOne(employeeId);
		payslip.setEmployee(employee);
		
		//Determine if payslip is for Employee's start month
		Calendar startDateCalendar = Calendar.getInstance();
	    startDateCalendar.setTime(employee.getStartDate());
	    
	    Calendar payslipDateCalendar = Calendar.getInstance();
	    payslipDateCalendar.setTime(payslipDate);
	    
	    BigDecimal proRatedPercentage = new BigDecimal(1);
	    Boolean prorated = false;
	    
		if (startDateCalendar.get(Calendar.YEAR) == payslipDateCalendar.get(Calendar.YEAR) && startDateCalendar.get(Calendar.MONTH) == payslipDateCalendar.get(Calendar.MONTH)) {
			BigDecimal numDaysInMonth = new BigDecimal(startDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			BigDecimal numDaysWorking = numDaysInMonth.subtract(new BigDecimal(startDateCalendar.get(Calendar.DAY_OF_MONTH))).add(new BigDecimal(1));
			
			proRatedPercentage = numDaysWorking.divide(numDaysInMonth, 2, RoundingMode.HALF_UP);
			
			if (proRatedPercentage.compareTo(new BigDecimal(1)) != 0) {
				prorated = true;
			}
			
		}
		
		payslip.setProrated(prorated);
		
		BigDecimal annualSalary = employee.getAnnualSalary();
		BigDecimal grossSalary = annualSalary.divide(new BigDecimal(12), 0, RoundingMode.HALF_UP).multiply(proRatedPercentage);
		payslip.setGrossIncome(grossSalary);
		
		BigDecimal incomeTax = taxService.calculateMonthlyTax(employee.getAnnualSalary()).multiply(proRatedPercentage);		
		payslip.setIncomeTax(incomeTax);		
		payslip.setNetIncome(grossSalary.subtract(incomeTax));
		
		BigDecimal pensionContribution = grossSalary.multiply(employee.getPensionContribution().divide(new BigDecimal(100)));
		payslip.setPensionContribution(pensionContribution);
		
		payslip.setPayslipDate(payslipDate);
		
		return mapper.map(payslipRepository.save(payslip), PayslipDTO.class);
	}

}
