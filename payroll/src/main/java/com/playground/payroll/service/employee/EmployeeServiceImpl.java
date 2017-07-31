package com.playground.payroll.service.employee;

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
import com.playground.payroll.repository.EmployeeRepository;
import com.playground.payroll.service.employee.dto.EmployeeDTO;
import com.playground.payroll.util.exception.NotFoundException;

/**
 * Service used for all methods regarding employees
 * 
 * @author	Craig Vosloo
 * @version	%I%, %G%
 * @see EmployeeService
 * @since 1.0
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepositiory;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private MessageSource messageSource;
	
	private Locale currentLocale = LocaleContextHolder.getLocale();

	/**
	 * Method to retrieve a list of all employees
	 * @return the list of employees
	 * @see EmployeeDTO
	 */
	@Override
	public List<EmployeeDTO> findAll() {
		List<Employee> employeeList = (List<Employee>) employeeRepositiory.findAll();
		List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
		
		Iterator<Employee> employeeIterator = employeeList.iterator();
		
		while (employeeIterator.hasNext()) {
			EmployeeDTO employeeDTO = mapper.map(employeeIterator.next(),EmployeeDTO.class);
			employeeDTOList.add(employeeDTO);
		}
		
		return employeeDTOList;
	}

	/**
	 * Method to retrieve an employee
	 * @param Long id the employee id
	 * @return EmployeeDTO employeeDTO the employee
	 * @see EmployeeDTO
	 */
	@Override
	public EmployeeDTO findOne(Long id) {
		Employee employee = employeeRepositiory.findOne(id);
		
		if (employee == null) {
			String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", id }, currentLocale);
			throw new NotFoundException(message);
		}
		
		return mapper.map(employee, EmployeeDTO.class);
	}
	
	/**
	 * Method to save an employee
	 * @param EmployeeDTO the employee to save
	 * @return EmployeeDTO the saved employee
	 * @see EmployeeDTO
	 */
	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee = null;
		if (employeeDTO.getId() != null && employeeDTO.getId() != 0l) {
			employee = employeeRepositiory.findOne(employeeDTO.getId());
			if (employee == null) {
				String message = messageSource.getMessage("error.id.notfound", new Object[] { "Employee", employeeDTO.getId() }, currentLocale);
				throw new NotFoundException(message);
			}
			mapper.map(employeeDTO, employee);
		} else {
			employee = mapper.map(employeeDTO, Employee.class);
		}
		return mapper.map(employeeRepositiory.save(employee), EmployeeDTO.class);
	}

}
