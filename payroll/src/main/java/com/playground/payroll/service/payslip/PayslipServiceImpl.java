package com.playground.payroll.service.payslip;

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
		
		Double annualSalary = new Double(employee.getAnnualSalary());
		Double grossSalary = new Double(Math.round(annualSalary / 12));
		payslip.setGrossIncome(grossSalary.intValue());
		
		Integer incomeTax = taxService.calculateMonthlyTax(employee.getAnnualSalary());		
		payslip.setIncomeTax(incomeTax);		
		payslip.setNetIncome(grossSalary.intValue() - incomeTax);
		
		Double pensionContribution = grossSalary * (new Double(employee.getPensionContribution()) / 100);		
		payslip.setPensionContribution(pensionContribution.intValue());
		
		payslip.setPayslipDate(payslipDate);
		
		return mapper.map(payslipRepository.save(payslip), PayslipDTO.class);
	}

}
