
package com.playground.payroll.service.payslip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

	/**
	 * Method to generate a payslip for an employee for a payment period
	 * @param payslipRequestDTO the request DTO
	 * @return PayslipDTO the generated payslip
	 * @see PayslipRequestDTO
	 */
	@Override
	public PayslipDTO generatePayslip(PayslipRequestDTO payslipRequestDTO) {
		PayslipDTO payslip = new PayslipDTO();
		
		Employee employee = employeeRepository.findOne(payslipRequestDTO.getEmployeeId());
		if (employee == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", payslipRequestDTO.getEmployeeId() }, currentLocale);
			throw new NotFoundException(message);
		}
	    
	    BigDecimal proRatedPercentage = new BigDecimal(1);
	    Boolean prorated = false;
	    
		if (employee.getStartDate().getYear() == payslipRequestDTO.getPayslipDate().getYear() && employee.getStartDate().getMonth() == payslipRequestDTO.getPayslipDate().getMonth()) {
			
			BigDecimal daysWorking = new BigDecimal(ChronoUnit.DAYS.between(employee.getStartDate(), payslipRequestDTO.getPayslipDate().withDayOfMonth(payslipRequestDTO.getPayslipDate().lengthOfMonth())) + 1);
			
			proRatedPercentage = daysWorking.divide(new BigDecimal(employee.getStartDate().lengthOfMonth()), 2, RoundingMode.HALF_UP);
			
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
		
		return payslip;
	}

	/**
	 * Method to save a payslip for an employee
	 * @param employeeId the Id of the employee to save the payslip for
	 * @param payslip the payslip to save
	 * @return PayslipDTO the saved payslip
	 * @see PayslipDTO
	 */
	@Override
	public PayslipDTO savePayslip(Long employeeId, PayslipDTO payslipDTO) {
		Payslip payslip = mapper.map(payslipDTO, Payslip.class);
		payslip.setPayslipDate(payslipDTO.getPayslipDate());
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", employeeId }, currentLocale);
			throw new NotFoundException(message);
		}
		payslip.setEmployee(employee);
		payslip = payslipRepository.save(payslip);
		payslipDTO =  mapper.map(payslip, PayslipDTO.class);
		payslipDTO.setPayslipDate(payslip.getPayslipDate());
		return payslipDTO;
	}
	
	/**
	 * Method to retrieve all payslips for an employee
	 * @param Long employeeId the Id of the employee to retrieve the payslips for
	 * @return List<PayslipDTO> the list of payslips
	 * @see PayslipDTO
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
			Payslip payslip = payslipIterator.next();
			PayslipDTO payslipDTO = mapper.map(payslip, PayslipDTO.class);
			payslipDTO.setPayslipDate(payslip.getPayslipDate());
			payslipDTOList.add(payslipDTO);
		}
		
		return payslipDTOList;
	}
	
	/**
	 * Method to retrieve a payslip
	 * @param Long payslipId the Id of the employee to retrieve the payslips for
	 * @return List<PayslipDTO> the list of payslips
	 * @see EmployeeDTO
	 */
	@Override
	public PayslipDTO findOne(Long payslipId) {
		Payslip payslip = payslipRepository.findOne(payslipId);
		if (payslip == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Payslip", payslipId }, currentLocale);
			throw new NotFoundException(message);
		}
		PayslipDTO payslipDTO = mapper.map(payslipRepository.save(payslip), PayslipDTO.class);
		payslipDTO.setPayslipDate(payslip.getPayslipDate());
		return payslipDTO;
	}

	/**
	 * Method to retrieve all payslips periods for an employee
	 * @param Long employeeId the Id of the employee to retrieve the payslips periods for
	 * @return List<PayslipPeriodDTO> the list of payslip periods
	 * @see PayslipPeriodDTO
	 */
	@Override
	public List<PayslipPeriodDTO> getPayslipPeriodsForEmployee(Long employeeId) {
		List<PayslipPeriodDTO> payslipPeriodDTOList = new ArrayList<PayslipPeriodDTO>();
		
		Employee employee = employeeRepository.findOne(employeeId);
		if (employee == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", employeeId }, currentLocale);
			throw new NotFoundException(message);
		}
		
		LocalDate startDate = employee.getStartDate();
		startDate = startDate.withDayOfMonth(1);
		LocalDate endDate = LocalDate.of(2017, 2, 1);
		
		Long monthsBetween = ChronoUnit.MONTHS.between(startDate, endDate);
	    
	    LocalDate payslipPeriodDate = startDate;

	    for (int i = 1; i <= (monthsBetween + 1); i++) {
	    	PayslipPeriodDTO payslipPeriodDTO = new PayslipPeriodDTO();
	    	payslipPeriodDTO.setPayslipPeriodDate(payslipPeriodDate);
	    	payslipPeriodDTOList.add(payslipPeriodDTO);
	    	payslipPeriodDate = payslipPeriodDate.plusMonths(1);
	    }
	    
		return payslipPeriodDTOList;
	}

}
