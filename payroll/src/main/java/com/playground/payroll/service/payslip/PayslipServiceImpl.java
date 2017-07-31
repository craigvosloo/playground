package com.playground.payroll.service.payslip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.playground.payroll.domain.Employee;
import com.playground.payroll.domain.Payslip;
import com.playground.payroll.repository.EmployeeRepository;
import com.playground.payroll.repository.PayslipRepository;
import com.playground.payroll.service.employee.dto.EmployeeDTO;
import com.playground.payroll.service.payslip.dto.PayslipDTO;
import com.playground.payroll.service.payslip.dto.PayslipPeriodDTO;
import com.playground.payroll.service.payslip.dto.PayslipRequestDTO;
import com.playground.payroll.service.tax.TaxService;
import com.playground.payroll.util.exception.NotFoundException;

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
	
	@Autowired
	private MessageSource messageSource;
	
	private Locale currentLocale = LocaleContextHolder.getLocale();
	private SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");

	/**
	 * Method to generate a payslip for an employee for a payment period
	 * @param PayslipRequestDTO payslipRequestDTO the request DTO
	 * @return PayslipDTO the generated payslip
	 * @see EmployeeDTO
	 */
	@Override
	public PayslipDTO generatePayslip(PayslipRequestDTO payslipRequestDTO) {
		PayslipDTO payslip = new PayslipDTO();
		
		Employee employee = employeeRepository.findOne(payslipRequestDTO.getEmployeeId());
		if (employee == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", payslipRequestDTO.getEmployeeId() }, currentLocale);
			throw new NotFoundException(message);
		}
		
		//Determine if payslip is for Employee's start month
		Calendar startDateCalendar = Calendar.getInstance();
	    startDateCalendar.setTime(employee.getStartDate());
	    
	    Calendar payslipDateCalendar = Calendar.getInstance();
	    payslipDateCalendar.setTime(payslipRequestDTO.getPayslipDate());
	    
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
		
		payslip.setPayslipDate(payslipRequestDTO.getPayslipDate());
		payslip.setPayslipDisplayDate(sdf.format(payslipRequestDTO.getPayslipDate()));		
		
		return payslip;
	}
	
	@Override
	public PayslipDTO savePayslip(Long employeeId, PayslipDTO payslipDTO) {
		Payslip payslip = mapper.map(payslipDTO, Payslip.class);
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", employeeId }, currentLocale);
			throw new NotFoundException(message);
		}
		payslip.setEmployee(employee);
		return mapper.map(payslipRepository.save(payslip), PayslipDTO.class);
	}
	
	/**
	 * Method to retrieve all payslips for an employee
	 * @param Long employeeId the Id of the employee to retrieve the payslips for
	 * @return List<PayslipDTO> the list of payslips
	 * @see EmployeeDTO
	 */
	@Override
	public List<PayslipDTO> getPayslipsByEmployee(Long employeeId) {
		List<PayslipDTO> payslipDTOList = new ArrayList<PayslipDTO>();
		
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", employeeId }, currentLocale);
			throw new NotFoundException(message);
		}
		
		List<Payslip> payslipList = payslipRepository.findByEmployeeIdOrderByPayslipDateAsc(employeeId);
		
		Iterator<Payslip> payslipIterator = payslipList.iterator();
		
		while (payslipIterator.hasNext()) {
			PayslipDTO payslipDTO = mapper.map(payslipIterator.next(), PayslipDTO.class);
			payslipDTO.setPayslipDisplayDate(sdf.format(payslipDTO.getPayslipDate()));
			payslipDTOList.add(payslipDTO);
		}
		
		return payslipDTOList;
	}

	/**
	 * Method to retrieve all payslips for an employee
	 * @param Long employeeId the Id of the employee to retrieve the payslips for
	 * @return List<PayslipDTO> the list of payslips
	 * @see EmployeeDTO
	 */
	@Override
	public List<PayslipPeriodDTO> getPayslipPeriodsForEmployee(Long employeeId) {
		List<PayslipPeriodDTO> payslipPeriodDTOList = new ArrayList<PayslipPeriodDTO>();
		
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", employeeId }, currentLocale);
			throw new NotFoundException(message);
		}
		
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(employee.getStartDate());
		startDate.set(Calendar.DAY_OF_MONTH,1);
		if (employee.getPayslips() != null && employee.getPayslips().size() > 0) {
			Iterator<Payslip> payslipIterator = employee.getPayslips().iterator();
			while (payslipIterator.hasNext()) {
				Payslip payslip = payslipIterator.next();
				if (payslip.getPayslipDate().after(startDate.getTime())) {
					startDate.setTime(payslip.getPayslipDate());
				}
			}
			startDate.add(Calendar.MONTH, 1);
		}
		
		Calendar endDate = Calendar.getInstance();
		endDate.set(2017, 2, 1, 0, 0, 0);
		
		int m1 = startDate.get(Calendar.YEAR) * 12 + startDate.get(Calendar.MONTH);
	    int m2 = endDate.get(Calendar.YEAR) * 12 + endDate.get(Calendar.MONTH);
	    
	    Calendar payslipPeriodDate = startDate;

	    for (int i = 1; i < (m2 - m1 + 1); i++) {
	    	PayslipPeriodDTO payslipPeriodDTO = new PayslipPeriodDTO();
	    	payslipPeriodDTO.setPayslipPeriodDate(payslipPeriodDate.getTime());
	    	payslipPeriodDTO.setDisplayValue(sdf.format(payslipPeriodDate.getTime()));
	    	payslipPeriodDTOList.add(payslipPeriodDTO);
	    	payslipPeriodDate.add(Calendar.MONTH, 1);	    	
	    }		
		return payslipPeriodDTOList;
	}

}
